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
    private String timestamp;
    /**
     * The message type
     */
    private MessageType messageType;
    /**
     * The password used for message of AUTHENTICATION_TYPE
     */
    private String password;

    /**
     * The content used into message
     */
    private byte[] content;
    
    /**
     * The content-type used into message
     */
    private ContentType contentType;
    
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
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestap to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
    
    /**
     * @return the content
     */
    public byte[] getContent(){
        return this.content;
    }
    
    /**
     * @param content the content to set
     */
    public void setContent(byte[] content){
        this.content = content;
    }
    
    /**
     * @return content-type  of message
    */
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * @param contenttype the content-type to set
    */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
