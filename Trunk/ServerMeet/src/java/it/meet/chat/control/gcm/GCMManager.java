package it.meet.chat.control.gcm;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import it.meet.service.common.util.DateUtils;
import it.meet.service.messaging.NotificationKey;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
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
                .addData(NotificationKey.SENDER_USER.getKey(), message.getSender())
                .addData(NotificationKey.TIMESTAMP.getKey(), message.getTimestamp()).build();
        try {
            Result result = sender.send(gcmMessage, registrationId, 5);
            System.out.println(result.getErrorCodeName());
            System.out.println(result.getMessageId());
            System.out.println(result.getCanonicalRegistrationId());
        } catch (IOException ex) {
            Logger.getLogger(GCMManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public static void main(String[] args) throws ParseException{
//        GCMManager gCMManager = new GCMManager();
//        String regId = "APA91bHXuIvPvDmOPER_6eC5ruAr9N5tWN5DKTpTiLWjkcEx1ChXnqBDJ91kFB-yR6SGiZANxRS3OtGAWCyLFC3vnbbbx7OK9_FOAYnTOh-MTdmtv1NBpTT-S_5cNvLA38Szy7YxA9BJ1zYcG-DyswtL5uC2TLTKgw";
//        
//        it.meet.service.messaging.Message message = new it.meet.service.messaging.Message();
//        message.setMessage("Messaggio di prova");
//        message.setSender("luigi");
//        message.setReceiver("tommy");
//        message.setTimestap(DateUtils.getDateString(new Date()));
//        
//        gCMManager.sendNotificationToClient(regId, message);
//    }
}
