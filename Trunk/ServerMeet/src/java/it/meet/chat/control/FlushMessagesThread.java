package it.meet.chat.control;

import it.meet.administrator.message.MessageAdminisrator;
import it.meet.chat.control.util.MessageWrapper;
import it.meet.common.database.DatabaseAdministrator;
import it.meet.service.common.util.MeetException;
import it.meet.service.messaging.Message;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 * This thread flush the message registered on database when a new client become
 * online
 *
 * @author Luigi Vorraro
 */
public class FlushMessagesThread extends Thread {

    /**
     * The queue message to save on database. This message will be sent when the
     * client will be online.
     */
    private LinkedBlockingQueue<String> newOnlineClientsQueue = new LinkedBlockingQueue<String>();
    /**
     * The message administrator
     */
    private MessageAdminisrator messageAdminisrator;
    /**
     * Indicates if the termination event has been dispatched to this action.
     */
    private boolean terminated = false;
    /**
     * The special string used to terminate the wait on queue
     */
    private static final String TERMINATOR_STRING_COMMAND = "TERMINATOR_STRING_COMMAND";
    /*
     * The dispatcher of messages
     */
    private ServerDispatcher serverDispatcher;

    /**
     * The default constructor
     */
    public FlushMessagesThread(ServerDispatcher serverDispatcher) {
        this.messageAdminisrator = new MessageAdminisrator();
        this.serverDispatcher = serverDispatcher;
    }
    
    /**
     * Add a new user online
     * 
     * @param username of user to add
     * 
     */
    public void addNewUserOnline(String username){
        this.newOnlineClientsQueue.offer(username);
    }

    /**
     * Get the next user become online
     *
     * @return the username of user
     *
     * @throws InterruptedException
     */
    private String getNextUserFromQueue() throws InterruptedException {
        return newOnlineClientsQueue.take();
    }

    /**
     * Until interrupted, get the new user from queue e retrieve the messages
     * from db.
     *
     */
    @Override
    public void run() {
        while (!terminated) {
            try {
                String username = getNextUserFromQueue();
                List<MessageWrapper> messagesToSend = getMessageToSend(username);

                ClientControlThread clientControlThread = serverDispatcher.getClientControl(username);
                if (clientControlThread != null) {
                    //USER IS ONLINE
                    for (Message message : messagesToSend) {
                        clientControlThread.sendMessage(message);
                    }
                }

            } catch (InterruptedException ex) {
                // Thread interrupted. Stop its execution
                break;
            }
        }
        Logger.getLogger(FlushMessagesThread.class.getName()).log(Level.INFO, "FlushMessageThred closed.");
    }

    /**
     * Retrive the message to send to receiver
     *
     * @param receiver the user reveicer
     *
     * @return the list of messages to send
     */
    private List<MessageWrapper> getMessageToSend(String receiver) {
        List<MessageWrapper> messagesToSend = null;
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();
            messagesToSend = messageAdminisrator.getMessageToSend(receiver, session);
        } catch (MeetException ex) {
            Logger.getLogger(FlushMessagesThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
        return messagesToSend;
    }

    /**
     * Terminate the current thread
     */
    public final void terminate() {
        newOnlineClientsQueue.offer(TERMINATOR_STRING_COMMAND);
        terminated = true;
    }
}
