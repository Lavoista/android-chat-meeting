package it.meet.service.common.util;

import java.io.Serializable;

/**
 * The enumeration of error code
 *
 * @author Luigi
 */
public enum ErrorCodeEnumeration implements Serializable {

    //OK MESSAGE
    MEET0000("MEET0000", "Operation executed."),
    
    
    ///USER
    MEET0001("MEET0001", "The user is null."),
    MEET0002("MEET0002", "The username {0} alredy used."),
    MEET0003("MEET0003", "The email is alredy used."),
    MEET0004("MEET0004", "The username is mandatory."),
    MEET0005("MEET0005", "The password is mandatory."),
    MEET0006("MEET0006", "The email address is not valid."),
    MEET0007("MEET0007", "Generic Error."),
    MEET0008("MEET0008", "The specified user with username {0} not exists."),
    MEET0009("MEET0009", "The username if empty."),
    MEET0010("MEET0010", "The username of friend if empty."),
    MEET0011("MEET0011", "It's not possible to add yourself to your friend."),
    MEET0012("MEET0012", "The telephone number is not valid."),
    MEET0013("MEET0013", "No active fried request found beetwen {0} and {1}."),
    MEET0022("MEET0022", "Username and/or password wrong."),
    
    //MESSAGE
    MEET0014("MEET0014", "Unknow message received."),
    MEET0015("MEET0015", "You are not authenticated to send message."),
    MEET0016("MEET0016", "Unknow message type."),
    MEET0017("MEET0017", "The sender message cannot be empty."),
    MEET0018("MEET0018", "The receiver message cannot be empty."),
    MEET0019("MEET0019", "Date of message is not valid."),
    MEET0020("MEET0020", "The body message is null."),
    MEET0021("MEET0021", "The body message is too long."),
    MEET0023("MEET0023", "Message type not allowed."),
    MEET0024("MEET0024", "No message {0} found to update."),
    MEET0025("MEET0025", "The message {0} is alredy to state {1} "),
    
    //NOTIFICATIONS
    MEET0026("MEET0026", "The registration id is mandatory."),
    MEET0027("MEET0027", "The device type is mandatory."),
    
    
    MEET0055("MEET0055","Connection Error"),
    //GENERIC ERROR
    MEET9999("MEET9999", "Error perform operation on database.");
    
    
    
    private String errorCode;
    private String errorDescription;

    ErrorCodeEnumeration(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorDescription
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * @param errorDescription the errorDescription to set
     */
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
