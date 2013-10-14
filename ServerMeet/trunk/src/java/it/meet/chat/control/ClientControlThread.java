package it.meet.chat.control;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.meet.administrator.message.MessageAdminisrator;
import it.meet.administrator.user.UserAdministrator;
import it.meet.chat.control.util.MessageWrapper;
import it.meet.common.database.DatabaseAdministrator;
import it.meet.service.common.entity.ResponseDTO;
import it.meet.service.common.util.ErrorCodeEnumeration;
import it.meet.service.common.util.MeetException;
import it.meet.service.common.util.StringUtils;
import it.meet.service.messaging.Message;
import it.meet.service.messaging.MessageState;
import it.meet.service.messaging.MessageType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 * This class manage the communication with client
 *
 * @author Luigi Vorraro
 */
public class ClientControlThread extends Thread {

    /**
     * The socket client
     */
    private Socket socket;
    /**
     * The username of client
     */
    private String username;
    /**
     * Indicate if the client is authenticated
     */
    private boolean authenticated;
    /**
     * Mapper to serialize and de-serialize the messages
     */
    private ObjectMapper objectMapper;
    /**
     * The dispatcher of messages
     */
    private ServerDispatcher serverDispatcher;
    /**
     * Buffer reader to read data from socket
     */
    private BufferedReader bufferedReader;
    /**
     * The print writer to send message to client
     */
    private PrintWriter printWriter;
    /**
     * The queue message to save on database. This message will be sent when the
     * client will be online.
     */
    private LinkedBlockingQueue<Message> messageQueeueToSend = new LinkedBlockingQueue<Message>();
    /**
     * Indicates if the termination event has been dispatched to this action.
     */
    private boolean terminated = false;
    /**
     * The thread to send the message to client
     */
    private ClientSenderMessages clientSenderMessages;
    /**
     * The administrator for user entity
     */
    private UserAdministrator userAdministrator;
    /**
     * The message administrator
     */
    private MessageAdminisrator messageAdminisrator;

    /**
     * Create a new object to manage the client communication
     *
     * @param aClientInfo
     * @param aServerDispatcher
     */
    public ClientControlThread(Socket socket, ServerDispatcher aServerDispatcher) throws IOException {
        this.socket = socket;
        this.serverDispatcher = aServerDispatcher;
        this.objectMapper = new ObjectMapper();
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.authenticated = false;
        this.clientSenderMessages = new ClientSenderMessages();
        this.userAdministrator = new UserAdministrator();
        this.messageAdminisrator = new MessageAdminisrator();
    }

    /**
     * Until interrupted, reads messages from the client socket, forwards them
     * to the server dispatcher's.
     */
    @Override
    public void run() {
        try {
            clientSenderMessages.start();
            while (!terminated) {
                String jsonMessage = bufferedReader.readLine();
                if (jsonMessage == null) {
                    break;
                }

                try {
                    Message message = objectMapper.readValue(jsonMessage, Message.class);

                    //USET NOT ALREDY AUTHENTICATED
                    if (!authenticated) {

                        if (message.getMessageType() == null || !message.getMessageType().equals(MessageType.AUTHENTICATION_TYPE)) {
                            // ERROR PARSE MESSAGE
                            sendErrorMessage(ErrorCodeEnumeration.MEET0015.getErrorCode(), ErrorCodeEnumeration.MEET0015.getErrorDescription());
                        } else {

                            String passwordMess = message.getPassword();
                            String usernameMess = message.getSender();
                            authenticated = authenticateUser(usernameMess, passwordMess);
                            if (authenticated) {
                                username = message.getSender().toLowerCase();

                                // OK MESSAGE
                                sendErrorMessage(ErrorCodeEnumeration.MEET0000.getErrorCode(), ErrorCodeEnumeration.MEET0000.getErrorDescription());
                                serverDispatcher.addClient(username, this);
                            } else {
                                sendErrorMessage(ErrorCodeEnumeration.MEET0022.getErrorCode(), ErrorCodeEnumeration.MEET0022.getErrorDescription());
                                break;
                            }
                        }
                    } else {
                        MessageAdminisrator.checkMessageValidity(message);
                        serverDispatcher.dispatchMessage(message);
                    }
                } catch (MeetException meetException) {
                    Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, meetException);
                    sendErrorMessage(meetException.getErrorCode(), meetException.getErrorDescription());
                    break;
                } catch (JsonParseException jpe) {
                    Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, jpe);
                    // ERROR PARSE MESSAGE
                    sendErrorMessage(ErrorCodeEnumeration.MEET0014.getErrorCode(), ErrorCodeEnumeration.MEET0014.getErrorDescription());
                    break;
                } catch (JsonMappingException jme) {
                    Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, jme);
                    // ERROR PARSE MESSAGE
                    sendErrorMessage(ErrorCodeEnumeration.MEET0014.getErrorCode(), ErrorCodeEnumeration.MEET0014.getErrorDescription());
                    break;
                }

            }
        } catch (IOException ioex) {
            Logger.getLogger(ClientControlThread.class.getName()).log(Level.INFO, "COnnection with client closed", ioex);
        }
        // Communication is broken. Interrupt both listener and sender threads
        //mClientInfo.mClientSender.interrupt();
        if (StringUtils.isNotEmpty(username)) {
            serverDispatcher.deleteClient(username);
        }
        try {
            //MESSAGE SENT TO TERMINATE THE CHILDREN THREAD
            Message message = new Message();
            message.setMessageType(MessageType.TERMINATION_TYPE);
            sendMessage(message);

            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        Logger.getLogger(ClientControlThread.class.getName()).log(Level.INFO, "ClientControlThread closed.");
    }

    /**
     * Send the error message to client
     *
     * @param errorCode
     * @param errorMessage
     */
    private void sendErrorMessage(String errorCode, String errorMessage) {
        try {
            // ERROR PARS MESSAGE
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setErrorCode(errorCode);
            responseDTO.setErrorDescription(errorMessage);

            String jsonRespnse = objectMapper.writeValueAsString(responseDTO);
            sendMessage(jsonRespnse);
        } catch (Exception ex) {
            Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Terminate the current thread and all children threads
     */
    public void terminate() {
        terminated = true;
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Send the message string on the socket
     *
     * @param message the message to send
     */
    private synchronized boolean sendMessage(String message) {
        boolean result;
        try {
            printWriter.println(message);
            printWriter.flush();
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }

    /**
     * Put the messages in queue for sending
     * 
     * @return the messageQueeueToSend
     */
    public boolean sendMessage(Message message) {
        return messageQueeueToSend.offer(message);
    }

    /**
     * Call the authentication process to authenticate the user
     *
     * @param username the username of user
     * @param password the password of user
     *
     * @return true if the user is authenticate, false otherwise
     */
    private boolean authenticateUser(String username, String password) {
        boolean authenticate = false;
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();
            authenticate = userAdministrator.authenticate(username, password, session);
        } catch (MeetException ex) {
            Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
        return authenticate;
    }

    /**
     * The internal thread is used to send the message to client
     */
    private class ClientSenderMessages extends Thread {

        /**
         * Generate a new thread to send the message
         */
        public ClientSenderMessages() {
        }

        /**
         * Check if there are messages to send from client
         */
        @Override
        public void run() {
            while (!terminated) {
                try {
                    Message message = messageQueeueToSend.take();
                    if (!MessageType.TERMINATION_TYPE.equals(message.getMessageType())) {
                        String jsonRespnse = objectMapper.writeValueAsString(message);
                        boolean result = sendMessage(jsonRespnse);
                        if (message instanceof MessageWrapper) {
                            if (result) {
                                updateMessageState(message, MessageState.SENT);
                            }
                        } else if (!result) {
                            saveMessageOnDatabase(message);
                        }
                    } else {
                        break;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, ex);
                    terminated = true;
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, ex);
                    terminated = true;
                }
            }
            Logger.getLogger(ClientControlThread.class.getName()).log(Level.INFO, "Child terminated");
        }
    }
    
    /**
     * Save the message on database
     * 
     * @param message the message to save
     */
    private void saveMessageOnDatabase(Message message){
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
     * Update the message state
     *
     * @param message the message to update
     * @param messageState the new message state
     */
    private void updateMessageState(Message message, MessageState messageState) {
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();
            messageAdminisrator.updateMessageState(message, messageState, session);
        } catch (MeetException ex) {
            Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
    }
}
