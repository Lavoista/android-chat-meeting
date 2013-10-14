package it.meet.service.messaging;

import java.io.Serializable;

/**
 * The message represent the chat message
 *
 * @author Luigi
 */
public class Message implements Serializable {

    /**
     * The sender
     */
    private String sender;
    /**
     * The receiver
     */
    private String receiver;
    /**
     * The message
     */
    private String message;
    /**
     * The time of creation message.
     */
    private String timestap;
    /**
     * The message type
     */
    private MessageType messageType;
    /**
     * The password used for message of AUTHENTICATION_TYPE
     */
    private String password;

    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the timestap
     */
    public String getTimestap() {
        return timestap;
    }

    /**
     * @param timestap the timestap to set
     */
    public void setTimestap(String timestap) {
        this.timestap = timestap;
    }

    /**
     * @return the messageType
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
