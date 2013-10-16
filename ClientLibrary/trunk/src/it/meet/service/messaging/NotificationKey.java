package it.meet.service.messaging;

/**
 * Class used in offline notification
 * 
 * @author Luigi Vorraro
 */
public enum NotificationKey {

    MESSAGE("message"),
    RECEIVER_USER("receiver"),
    SENDER_USER("sender");
    
    /**
     * The key string
     */
    private String key;

    /**
     * Internal constructor
     * 
     * @param key the key of notification
     */
    private NotificationKey(String key) {
        this.key = key;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
}
