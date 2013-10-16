package it.meet.chat.control.gcm;

import it.meet.service.messaging.Message;

/**
 * The interface contains the method must be implemented to send the offiline
 * notification.
 *
 * @author Luigi Vorraro
 */
public abstract class NotificationManager {

    
    /**
     * Create a notification manager class
     */
    public NotificationManager() {
        applicationId =  "AIzaSyAPznAuek7kqQexm5Hvbf7BEqD8IQhEueY";
    }
    
    /**
     * The application id
     */
    private String applicationId;

    /**
     * @return the applicationId
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
    
    /**
     * Send an message to device
     * 
     * @param registrationId the registration id of device
     * @param message  the message to send
     * 
     */
    public abstract void sendNotificationToClient(String registrationId, Message message);
}
