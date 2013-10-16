package it.meet.chat.control.gcm;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import it.meet.service.messaging.NotificationKey;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The GCM administrator used to send GCM notification for android device.
 *
 * @author Luigi Vorraro
 */
public class GCMManager extends NotificationManager {

    /**
     * Create a new instance of object
     */
    public GCMManager() {
        super();
    }
    
    
    /**
     * Send a message to android device
     * 
     * @param registrationId the registration id of device
     * @param message the message to send
     * 
     */
    public void sendNotificationToClient(String registrationId, it.meet.service.messaging.Message message) {
        Sender sender = new Sender(getApplicationId());
        Message gcmMessage = new Message.Builder().addData(NotificationKey.MESSAGE.getKey(), message.getMessage())
                .addData(NotificationKey.RECEIVER_USER.getKey(), message.getReceiver())
                .addData(NotificationKey.SENDER_USER.getKey(), message.getSender()).build();
        try {
            Result result = sender.send(gcmMessage, registrationId, 5);
            System.out.println(result.getErrorCodeName());
            System.out.println(result.getMessageId());
            System.out.println(result.getCanonicalRegistrationId());
        } catch (IOException ex) {
            Logger.getLogger(GCMManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
