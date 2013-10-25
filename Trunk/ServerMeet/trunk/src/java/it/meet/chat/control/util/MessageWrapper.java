package it.meet.chat.control.util;

import it.meet.service.messaging.Message;

/**
 * The wrapper for message
 *
 * @author Luigi Vorraro
 */
public class MessageWrapper extends Message {

    /**
     * Indicate if the message came from db
     */
    private boolean cameFromDB;

    /**
     * @return the cameFromDB
     */
    public boolean isCameFromDB() {
        return cameFromDB;
    }

    /**
     * @param cameFromDB the cameFromDB to set
     */
    public void setCameFromDB(boolean cameFromDB) {
        this.cameFromDB = cameFromDB;
    }
}
