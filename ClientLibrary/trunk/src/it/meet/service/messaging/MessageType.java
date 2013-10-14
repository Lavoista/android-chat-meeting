package it.meet.service.messaging;

import java.io.Serializable;

/**
 * The message type
 *
 * @author Luigi
 */
public enum MessageType implements Serializable {

    /**
     * Message of authentication
     */
    AUTHENTICATION_TYPE("AUTHENTICATION_TYPE"),
    CHAT_TYPE("AUTHENTICATION_TYPE"),
    TERMINATION_TYPE("TERMINATION_TYPE");
    
    
    private String mesageType;
    
    
    private MessageType(String messageType){
        this.mesageType = messageType;
    }

    /**
     * @return the mesageType
     */
    public String getMesageType() {
        return mesageType;
    }

    /**
     * @param mesageType the mesageType to set
     */
    public void setMesageType(String mesageType) {
        this.mesageType = mesageType;
    }
}
