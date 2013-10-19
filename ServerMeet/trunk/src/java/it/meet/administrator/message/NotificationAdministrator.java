package it.meet.administrator.message;

import it.meet.administrator.user.UserAdministrator;
import it.meet.beans.NotificationOffline;
import it.meet.chat.control.util.NotificationOfflineInfo;
import it.meet.service.common.util.DeviceType;
import it.meet.service.common.util.ErrorCodeEnumeration;
import it.meet.service.common.util.MeetException;
import it.meet.service.common.util.StringUtils;
import it.meet.service.messaging.MessageState;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Manage the notification offline and registration id
 *
 * @author Luigi Vorraro
 */
public class NotificationAdministrator {

    /**
     * Check if the user have a registration id to send a offline notification
     * and if there isn't a notification in to send.
     *
     * @param username the user to check
     * @param session the session from database
     *
     * @return the registration id if need to send a notification.
     *
     * @throws MeetException if any error occurs
     */
    public NotificationOfflineInfo checkSendNotification(String username, Session session) throws MeetException {
        NotificationOfflineInfo notificationOfflineInfo = null;
        try {

            Criteria criteria = session.createCriteria(NotificationOffline.class);
            criteria.add(Restrictions.eq("username", username));
            
            List<NotificationOffline> result = criteria.list();
            if (result != null && !result.isEmpty()) {
                NotificationOffline notificationOffline = result.get(0);
                
                notificationOfflineInfo = new NotificationOfflineInfo();
                notificationOfflineInfo.setReceiver(username);
                notificationOfflineInfo.setRegistrationId(notificationOffline.getRegistrationId());
                notificationOfflineInfo.setDeviceType(DeviceType.valueOf(notificationOffline.getDeviceType()));
                
                if(StringUtils.isEmpty(notificationOffline.getStatus())){
                    notificationOfflineInfo.setSendNotification(true);
                } else if(!MessageState.TO_SEND.toString().equalsIgnoreCase(notificationOffline.getStatus())) {
                    notificationOfflineInfo.setSendNotification(true);
                }
            }

        } catch (Exception ex) {
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }
        return notificationOfflineInfo;
    }

    
    /**
     * Update the registration id on database
     * 
     * @param username the username of user
     * @param registationId the registration id
     * @param session the session on database
     * 
     * @throws MeetException if any error occurs
     */
    public void updateRegistrationId(String username, DeviceType deviceType, String registationId, Session session) throws MeetException {
        if (StringUtils.isEmpty(username)) {
            throw new MeetException(ErrorCodeEnumeration.MEET0004);
        }
        if (StringUtils.isEmpty(registationId)) {
            throw new MeetException(ErrorCodeEnumeration.MEET0026);
        }
        if (deviceType == null) {
            throw new MeetException(ErrorCodeEnumeration.MEET0027);
        }

        try {
            Criteria criteria = session.createCriteria(NotificationOffline.class);
            criteria.add(Restrictions.eq("username", username));

            NotificationOffline notificationOffline;

            List<NotificationOffline> result = criteria.list();
            if (result != null && !result.isEmpty()) {
                notificationOffline = result.get(0);
                notificationOffline.setRegistrationId(registationId);
            } else {
                notificationOffline = new NotificationOffline();
                notificationOffline.setUsername(username);
                notificationOffline.setRegistrationId(registationId);
                notificationOffline.setDeviceType(deviceType.name());
            }

            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.saveOrUpdate(notificationOffline);
                tx.commit();
            } catch (Exception e) {
                Logger.getLogger(UserAdministrator.class.getName()).log(Level.SEVERE, null, e);
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            }

        } catch (Exception ex) {
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }
    }

    
    public void saveNotification(Session session) {
        
    }
}
