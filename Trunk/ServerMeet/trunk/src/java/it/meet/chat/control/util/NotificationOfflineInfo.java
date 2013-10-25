package it.meet.chat.control.util;

import it.meet.service.common.util.DeviceType;
import java.io.Serializable;

/**
 * Contains the information to send offline notification
 *
 * @author Luigi Vorraro
 */
public class NotificationOfflineInfo implements Serializable {

    /**
     * The device type
     */
    private DeviceType deviceType;
    /**
     * The registration id
     */
    private String registrationId;
    /**
     * The username of receiver
     */
    private String receiver;
    /**
     * The flag indicate if need to send the notification
     */
    private boolean sendNotification;

    /**
     * @return the deviceType
     */
    public DeviceType getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType the deviceType to set
     */
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the registrationId
     */
    public String getRegistrationId() {
        return registrationId;
    }

    /**
     * @param registrationId the registrationId to set
     */
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the sendNotification
     */
    public boolean isSendNotification() {
        return sendNotification;
    }

    /**
     * @param sendNotification the sendNotification to set
     */
    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }
}
