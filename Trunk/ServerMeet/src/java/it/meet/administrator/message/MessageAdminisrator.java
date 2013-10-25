package it.meet.administrator.message;

import it.meet.beans.Messages;
import it.meet.beans.MessagesId;
import it.meet.chat.control.util.MessageWrapper;
import it.meet.service.common.util.DateUtils;
import it.meet.service.common.util.ErrorCodeEnumeration;
import it.meet.service.common.util.MeetException;
import it.meet.service.common.util.StringUtils;
import it.meet.service.messaging.Message;
import it.meet.service.messaging.MessageState;
import it.meet.service.messaging.MessageType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * The administrator for chat messages
 *
 * @author Luigi Vorraro
 */
public class MessageAdminisrator {

    /**
     * The max lenght of message body.
     */
    public static final int MAX_MESSAGE_LEGTH = 500;

    /**
     * Save a chat message od database. This feature is mandatory when the
     * receiver of message is offline
     *
     * @param message the message to send
     * @param session the hibernate session on database
     *
     * @throws MeetException is eny error occurs
     *
     */
    public void saveMessage(Message message, Session session) throws MeetException {
        checkMessageValidity(message);

        if (message.getMessageType().equals(MessageType.CHAT_TYPE)) {

            Messages messages = new Messages();
            messages.setId(new MessagesId());
            messages.getId().setUsername(message.getSender());
            messages.getId().setUsernamereceiver(message.getReceiver());
            try {
                messages.getId().setDate(DateUtils.getDate(message.getTimestap()));
            } catch (ParseException ex) {
                Logger.getLogger(MessageAdminisrator.class.getName()).log(Level.SEVERE, null, ex);
                throw new MeetException(ErrorCodeEnumeration.MEET0019);
            }
            messages.setState(MessageState.TO_SEND.toString());
            messages.setMessage(message.getMessage());
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.saveOrUpdate(messages);
                tx.commit();
            } catch (Exception e) {
                Logger.getLogger(MessageAdminisrator.class.getName()).log(Level.SEVERE, null, e);
                if (tx != null) {
                    tx.rollback();
                }
                if (e instanceof MeetException) {
                    throw ((MeetException) e);
                } else {
                    throw new MeetException(ErrorCodeEnumeration.MEET9999, e);
                }
            }
        } else {
            throw new MeetException(ErrorCodeEnumeration.MEET0023);
        }
    }

    /**
     * Update the message on database with new state
     *
     * @param message the message to update
     * @param messageState the new message state
     *
     * @throws MeetException if any error occurs
     *
     */
    public void updateMessageState(Message message, MessageState messageState, Session session) throws MeetException {
        checkMessageValidity(message);

        try {
            String keyMessage = message.getSender() + "-" + message.getReceiver() + "-" + DateUtils.getDate(message.getTimestap());
            Criteria criteria = session.createCriteria(Messages.class);
            criteria.add(Restrictions.eq("id.username", message.getSender()));
            criteria.add(Restrictions.eq("id.usernamereceiver", message.getReceiver()));
            criteria.add(Restrictions.eq("id.date", DateUtils.getDate(message.getTimestap())));

            List<Messages> result = criteria.list();
            if (result != null && !result.isEmpty()) {
                Messages messages = result.get(0);
                if (messages.getState().equalsIgnoreCase(messageState.toString())) {
                } else {
                    messages.setState(messageState.toString());
                    Transaction tx = null;
                    try {
                        tx = session.beginTransaction();
                        session.saveOrUpdate(messages);
                        tx.commit();
                    } catch (Exception e) {
                        Logger.getLogger(MessageAdminisrator.class.getName()).log(Level.SEVERE, null, e);
                        if (tx != null) {
                            tx.rollback();
                        }
                        if (e instanceof MeetException) {
                            throw ((MeetException) e);
                        } else {
                            throw new MeetException(ErrorCodeEnumeration.MEET9999, e);
                        }
                    }
                }
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET0024, keyMessage);
            }
        } catch (Exception ex) {
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }

    }

    /**
     * Get and retrieve the messato in "TO_SEND" state
     *
     * @param receiver the username of user receiver
     * @param session the hibernate session
     *
     * @return the list of messages to send
     */
    public List<MessageWrapper> getMessageToSend(String receiver, Session session) throws MeetException {
        List<MessageWrapper> messagesToSend = new ArrayList<MessageWrapper>();

        try {

            Criteria criteria = session.createCriteria(Messages.class);
            criteria.add(Restrictions.eq("id.usernamereceiver", receiver));
            criteria.add(Restrictions.eq("state", MessageState.TO_SEND.toString()));
            criteria.addOrder(Order.asc("id.date"));

            List<Messages> result = criteria.list();
            if (result != null && !result.isEmpty()) {
                for (Messages messages : result) {
                    MessageWrapper message = new MessageWrapper();
                    message.setMessage(messages.getMessage());
                    message.setMessageType(MessageType.CHAT_TYPE);
                    message.setReceiver(receiver);
                    message.setSender(messages.getId().getUsername());
                    message.setTimestap(DateUtils.getString(messages.getId().getDate()));
                    
                    messagesToSend.add(message);
                }
            }
        } catch (Exception ex) {
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }

        return messagesToSend;
    }

    /**
     * Check if the message received is valid
     *
     * @param message the message received
     */
    public static void checkMessageValidity(Message message) throws MeetException {
        if (message == null) {
            throw new MeetException(ErrorCodeEnumeration.MEET0014);
        } else if (message.getMessageType() == null) {
            throw new MeetException(ErrorCodeEnumeration.MEET0016);
        } else if (StringUtils.isEmpty(message.getSender())) {
            throw new MeetException(ErrorCodeEnumeration.MEET0017);
        } else if (StringUtils.isEmpty(message.getReceiver())) {
            throw new MeetException(ErrorCodeEnumeration.MEET0018);
        } else if (StringUtils.isEmpty(message.getTimestap()) || !DateUtils.isValidDate(message.getTimestap(), "yyyyMMddHHmmss")) {
            throw new MeetException(ErrorCodeEnumeration.MEET0019);
        } else if (StringUtils.isEmpty(message.getMessage())) {
            throw new MeetException(ErrorCodeEnumeration.MEET0020);
        } else if (message.getMessage().length() > MessageAdminisrator.MAX_MESSAGE_LEGTH) {
            throw new MeetException(ErrorCodeEnumeration.MEET0021);
        }
    }
}
