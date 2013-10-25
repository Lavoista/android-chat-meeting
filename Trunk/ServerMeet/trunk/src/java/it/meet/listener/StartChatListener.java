package it.meet.listener;

import it.meet.chat.control.ChatServerListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * This listener is used to
 *
 * @author Luigi Vorraro
 */
public class StartChatListener implements ServletContextListener {

    private ChatServerListener chatServerListener;

    /**
     * Called when the application is deployed
     *
     * @param sce
     */
    public void contextInitialized(ServletContextEvent sce) {
        if ((chatServerListener == null) || (!chatServerListener.isAlive())) {
            chatServerListener = new ChatServerListener();
            chatServerListener.start();
        }
    }

    /**
     * Called when the application is undeployed
     *
     * @param sce
     */
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            chatServerListener.terminate();
        } catch (Exception ex) {
            Logger.getLogger(StartChatListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
