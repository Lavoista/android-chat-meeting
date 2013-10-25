package it.meet.chat.control.gcm;

import it.meet.service.common.util.DeviceType;
import java.util.HashMap;
import java.util.Map;

/**
 * The factory used to return the right notification manager for different device
 * 
 * @author Luigi Vorraro
 */
public class NotificationManagerFactory {
    /**
     * The singleton instance
     */
    private static NotificationManagerFactory notificationManagerFactory;

    /**
     * The map of several implementation for different device
     */
    private Map<String, NotificationManager> notificationManagerMap;
    
    
    /**
     * Create the map of possible implementation of Notification manager
     */
    private NotificationManagerFactory() {
        notificationManagerMap = new HashMap<String, NotificationManager>();
        GCMManager gCMAdministrator = new GCMManager();
        notificationManagerMap.put(DeviceType.ANDROID.name(), gCMAdministrator);
    }
    
    /**
     * Return the singleton instance of factory
     * 
     * @return the singleton instance of factory
     */
    public static NotificationManagerFactory getInstance(){
        if(notificationManagerFactory == null){
            notificationManagerFactory = new NotificationManagerFactory();
        }
        return notificationManagerFactory;
    }
    
    /**
     * Return the notification manager
     * 
     * @param notificationManagerType the type of notification to return
     * 
     * @return the notification manager
     */
    public NotificationManager getNotificationManager(DeviceType deviceType){
        if(notificationManagerMap.containsKey(deviceType.name())){
            return notificationManagerMap.get(deviceType.name());
        } else {
            return null;
        }
    }
}
