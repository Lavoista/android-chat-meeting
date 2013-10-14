package it.meet.chat.control;

import it.meet.administrator.message.MessageAdminisrator;
import it.meet.common.database.DatabaseAdministrator;
import it.meet.service.common.util.MeetException;
import it.meet.service.messaging.Message;
import it.meet.service.messaging.MessageType;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 * The class save the message on database for the client there are offline
 *
 * @author Luigi Vorraro
 */
public class MessageOfflineThread extends Thread {

    /**
     * The queue message to save on database. This message will be sent when the
     * client will be online.
     */
    private LinkedBlockingQueue<Message> messageQueeueToSave = new LinkedBlockingQueue<Message>();
    /**
     * Indicates if the termination event has been dispatched to this action.
     */
    private boolean terminated = false;
    /**
     * The message administrator
     */
    private MessageAdminisrator messageAdminisrator;

    /**
     * The default constructor
     */
    public MessageOfflineThread() {
        this.messageAdminisrator = new MessageAdminisrator();
    }

    /**
     * @return the messageQueeueToSave
     */
    public void saveMessage(Message message) {
        messageQueeueToSave.offer(message);
    }

    @Override
    public void run() {
        try {
            while (!terminated) {
                Message message = messageQueeueToSave.take();
                if (!MessageType.TERMINATION_TYPE.equals(message.getMessageType())) {
                    saveMessageOnDatabase(message);
                } else {
                    //TERMINATION MESSAGE RECEIVED
                    break;
                }
            }
        } catch (InterruptedException ie) {
            // Thread interrupted. Stop its execution
        }

        Logger.getLogger(ClientControlThread.class.getName()).log(Level.INFO, "MessageOfflineThread closed.");
    }

    /**
     * Save the message on database
     *
     * @param message the message to save
     */
    private void saveMessageOnDatabase(Message message) {
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();
            messageAdminisrator.saveMessage(message, session);
        } catch (MeetException ex) {
            Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
    }

    /**
     * Terminate the current thread
     */
    public final void terminate() {
        terminated = true;

        //MESSAGE SENT TO TERMINATE THE CHILDREN THREAD
        Message message = new Message();
        message.setMessageType(MessageType.TERMINATION_TYPE);
        saveMessage(message);
    }
}
