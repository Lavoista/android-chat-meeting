package it.meet.chat.control;

import it.meet.service.messaging.Message;
import it.meet.service.messaging.MessageType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The server dispatcher messages
 *
 * @author Luigi Vorraro
 */
public class ServerDispatcher extends Thread {

    /**
     * The message queue
     */
    private LinkedBlockingQueue<Message> messageQueeue = new LinkedBlockingQueue<Message>();
    /**
     * The client info map with username key
     */
    private Map<String, ClientControlThread> mClients = new HashMap<String, ClientControlThread>();
    /**
     * Indicates if the termination event has been dispatched to this action.
     */
    private boolean terminated = false;
    /**
     * The thered used to save the message on database for offline client
     */
    private MessageOfflineThread messageOfflineThread;
    
    private FlushMessagesThread flushMessagesThread;

    /**
     * The public constructor
     *
     */
    public ServerDispatcher() {
        //The controller of offline message
        this.messageOfflineThread = new MessageOfflineThread();
        this.flushMessagesThread = new FlushMessagesThread(this);
    }

    /**
     * Adds given client to the server's client list.
     */
    public synchronized void addClient(String username, ClientControlThread clientControlThread) {
        if (!terminated) {
            mClients.put(username, clientControlThread);
            this.flushMessagesThread.addNewUserOnline(username);
        }
    }

    /**
     * Delete the client from map of socket
     *
     * @param username
     */
    public synchronized void deleteClient(String username) {
        if (mClients.containsKey(username)) {
            mClients.remove(username);
        }
    }

    /**
     * Adds given message to the dispatcher's message queue and notifies this
     * thread to wake up the message queue reader (getNextMessageFromQueue
     * method). dispatchMessage method is called by other threads
     * (ClientListener) when a message is arrived.
     */
    public void dispatchMessage(Message aMessage) {
        if (!terminated) {
            messageQueeue.offer(aMessage);
        }
    }

    /**
     * @return and deletes the next message from the message queue. If there is
     * no messages in the queue, falls in sleep until notified by
     * dispatchMessage method.
     */
    private Message getNextMessageFromQueue() throws InterruptedException {
        Message message = messageQueeue.take();
        return message;
    }

    /**
     * Sends given message to all clients in the client list. Actually the
     * message is added to the client sender thread's message queue and this
     * client sender thread is notified.
     */
    private void sendMessageToClient(Message message) {
        if (!terminated) {
            String receiver = message.getReceiver();
            if (mClients.containsKey(receiver)) {
                ClientControlThread clientControlThread = mClients.get(receiver);
                clientControlThread.sendMessage(message);
            } else {
                messageOfflineThread.saveMessage(message);
            }
        }
    }

    
    /**
     * Retrive the control thread of client, if is online, null otherwise
     * 
     * @param username of client control thread
     * 
     * @return the control thread of client, if is online, null otherwise 
     */
    public ClientControlThread getClientControl(String username) {
        if (mClients.containsKey(username)) {
            return mClients.get(username);
        } else {
            return null;
        }
    }

    /**
     * Infinitely reads messages from the queue and dispatch them to all clients
     * connected to the server.
     */
    @Override
    public void run() {
        try {
            this.messageOfflineThread.start();
            this.flushMessagesThread.start();
            while (!terminated) {
                Message message = getNextMessageFromQueue();
                if (!MessageType.TERMINATION_TYPE.equals(message.getMessageType())) {
                    sendMessageToClient(message);
                } else {
                    break;
                }
            }
        } catch (InterruptedException ie) {
            // Thread interrupted. Stop its execution
        } finally {
            termintateChildren();
        }
        Logger.getLogger(ClientControlThread.class.getName()).log(Level.INFO, "ServerDispatcher closed.");
    }

    /**
     * Terminate all children thread
     */
    private void termintateChildren() {
        for (ClientControlThread clientControlThread : mClients.values()) {
            clientControlThread.terminate();
        }
    }

    /**
     * Terminate the current thread
     */
    public final void terminate() {
        this.messageOfflineThread.terminate();
        this.flushMessagesThread.terminate();
        Message message = new Message();
        message.setMessageType(MessageType.TERMINATION_TYPE);
        messageQueeue.offer(message);
        terminated = true;
    }
}