package it.meet.service.messaging;

/**
 * The possible state of message
 *
 * @author Luigi Vorraro
 */
public enum MessageState {

    TO_SEND("The message is not alredy sent"),
    SENT("The message has been sent"),
    CANCELLED("The message has not been sent, and never will be.");
    /**
     * The description of state
     */
    private String decription;
    /**
     * The prive contrunction
     * 
     * @param description 
     */
    private MessageState(String description) {
        this.decription = description;
    }

    /**
     * @return the decription
     */
    public String getDecription() {
        return decription;
    }

    /**
     * @param decription the decription to set
     */
    public void setDecription(String decription) {
        this.decription = decription;
    }
}
