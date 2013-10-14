package it.meet.chat.control.gcm;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author E-Dea Transport Technology
 */
public class GCMAdministrator {

    private final String APP_KEY = "AIzaSyAPznAuek7kqQexm5Hvbf7BEqD8IQhEueY";

    public void sendNotificationToClient(String registrationId, int numberOfAttempts) {
        Sender sender = new Sender(APP_KEY);
        Message message = new Message.Builder().addData("message", "Altra prova!!!").build();
        try {
            Result result = sender.send(message, registrationId, numberOfAttempts);
            System.out.println(result.getErrorCodeName());
            System.out.println(result.getMessageId());
            System.out.println(result.getCanonicalRegistrationId());
        } catch (IOException ex) {
            Logger.getLogger(GCMAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
