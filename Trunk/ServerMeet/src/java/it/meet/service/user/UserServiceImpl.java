package it.meet.service.user;

import it.meet.administrator.message.NotificationAdministrator;
import it.meet.administrator.user.UserAdministrator;
import it.meet.common.database.DatabaseAdministrator;
import it.meet.service.common.entity.ResponseDTO;
import it.meet.service.common.util.DeviceType;
import it.meet.service.common.util.ErrorCodeEnumeration;
import it.meet.service.common.util.MeetException;
import it.meet.service.user.entity.UserDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 * The service implementation
 *
 * @author Luigi Vorraro
 */
public class UserServiceImpl implements UserService {

    /**
     * Create a new services implementation
     */
    public UserServiceImpl() {
    }

    /**
     * Create a user in the system with the information passed.
     *
     * @param user the user information to create
     *
     * @return
     */
    public ResponseDTO createUser(UserDTO user) {
        ResponseDTO responseDTO = new ResponseDTO();
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();

            UserAdministrator userAdministrator = new UserAdministrator();

            userAdministrator.createUser(user, session);

            responseDTO.setErrorCode(ErrorCodeEnumeration.MEET0000.getErrorCode());
            responseDTO.setErrorDescription(ErrorCodeEnumeration.MEET0000.getErrorDescription());

        } catch (MeetException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            responseDTO.setErrorCode(ex.getErrorCode());
            responseDTO.setErrorDescription(ex.getErrorDescription());
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
        return responseDTO;
    }

    public UserDTO findUserByUserName(String userName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Add friend
     *
     * @param username
     * @param friendUsername
     */
    public ResponseDTO addFriend(String username, String friendUsername) {
        ResponseDTO responseDTO = new ResponseDTO();
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();

            UserAdministrator userAdministrator = new UserAdministrator();

            userAdministrator.addFriend(username, friendUsername, session);

            responseDTO.setErrorCode(ErrorCodeEnumeration.MEET0000.getErrorCode());
            responseDTO.setErrorDescription(ErrorCodeEnumeration.MEET0000.getErrorDescription());

        } catch (MeetException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            responseDTO.setErrorCode(ex.getErrorCode());
            responseDTO.setErrorDescription(ex.getErrorDescription());
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }

        return responseDTO;
    }

    /**
     * Register a friend request
     *
     * @param username the username of sender
     * @param friendUsername the username of friend receiver
     *
     * @return the result of operation
     *
     */
    public ResponseDTO sendFriendRequest(String username, String friendUsername, String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();

            UserAdministrator userAdministrator = new UserAdministrator();

            userAdministrator.sendFriendRequest(username, friendUsername, message, session);

            responseDTO.setErrorCode(ErrorCodeEnumeration.MEET0000.getErrorCode());
            responseDTO.setErrorDescription(ErrorCodeEnumeration.MEET0000.getErrorDescription());

        } catch (MeetException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            responseDTO.setErrorCode(ex.getErrorCode());
            responseDTO.setErrorDescription(ex.getErrorDescription());
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }

        return responseDTO;
    }

    /**
     *
     * @param username
     * @param friendUsername
     * @return
     */
    public ResponseDTO removeFriend(String username, String friendUsername) {
        ResponseDTO responseDTO = new ResponseDTO();
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();

            UserAdministrator userAdministrator = new UserAdministrator();
            userAdministrator.removeFriend(username, friendUsername, session);

            responseDTO.setErrorCode(ErrorCodeEnumeration.MEET0000.getErrorCode());
            responseDTO.setErrorDescription(ErrorCodeEnumeration.MEET0000.getErrorDescription());

        } catch (MeetException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            responseDTO.setErrorCode(ex.getErrorCode());
            responseDTO.setErrorDescription(ex.getErrorDescription());
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }

        return responseDTO;
    }

    /**
     * Cancel an append friend request (an not already accepted request)
     *
     * @param username
     * @param friendUsername
     * @param message
     * @return
     */
    public ResponseDTO cancelFriendRequest(String username, String friendUsername) {
        ResponseDTO responseDTO = new ResponseDTO();
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();

            UserAdministrator userAdministrator = new UserAdministrator();
            userAdministrator.removeUnacceptedFriendRequest(username, friendUsername, session);

            responseDTO.setErrorCode(ErrorCodeEnumeration.MEET0000.getErrorCode());
            responseDTO.setErrorDescription(ErrorCodeEnumeration.MEET0000.getErrorDescription());

        } catch (MeetException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            responseDTO.setErrorCode(ex.getErrorCode());
            responseDTO.setErrorDescription(ex.getErrorDescription());
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }

        return responseDTO;
    }

    /**
     * Update the registration id and the device type
     *
     * @param username the username of user
     * @param registrationId the registration id to update
     * @param deviceType the device type to update
     *
     * @return the response of operation
     */
    public ResponseDTO updateRegistrationId(String username, String registrationId, DeviceType deviceType) {
        ResponseDTO responseDTO = new ResponseDTO();
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();

            NotificationAdministrator notificationAdministrator = new NotificationAdministrator();
            notificationAdministrator.updateRegistrationId(username, deviceType, registrationId, session);

            responseDTO.setErrorCode(ErrorCodeEnumeration.MEET0000.getErrorCode());
            responseDTO.setErrorDescription(ErrorCodeEnumeration.MEET0000.getErrorDescription());

        } catch (MeetException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            responseDTO.setErrorCode(ex.getErrorCode());
            responseDTO.setErrorDescription(ex.getErrorDescription());
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }

        return responseDTO;
    }

    /**
     * Update the photo of user
     *
     * @param username the username of user
     * @param photo the photo
     *
     * @return the response of operation
     */
    public ResponseDTO updatePhoto(String username, byte[] photo) {
        ResponseDTO responseDTO = new ResponseDTO();
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();

            UserAdministrator userAdministrator = new UserAdministrator();
            userAdministrator.updatePhoto(username, photo, session);

            responseDTO.setErrorCode(ErrorCodeEnumeration.MEET0000.getErrorCode());
            responseDTO.setErrorDescription(ErrorCodeEnumeration.MEET0000.getErrorDescription());

        } catch (MeetException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            responseDTO.setErrorCode(ex.getErrorCode());
            responseDTO.setErrorDescription(ex.getErrorDescription());
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }

        return responseDTO;
    }

    /**
     * Execute the login operation
     *
     * @param username of user
     * @param password of user
     *
     * @return the response object
     *
     */
    public ResponseDTO login(String username, String password) {

        ResponseDTO responseDTO = new ResponseDTO();
        Session session = null;
        try {
            session = DatabaseAdministrator.getInstance().openSession();

            UserAdministrator userAdministrator = new UserAdministrator();
            boolean authenticate = userAdministrator.authenticate(username, password, session);
            if (!authenticate) {
                responseDTO.setErrorCode(ErrorCodeEnumeration.MEET0022.getErrorCode());
                responseDTO.setErrorDescription(ErrorCodeEnumeration.MEET0022.getErrorDescription());
            } else {
                responseDTO.setErrorCode(ErrorCodeEnumeration.MEET0000.getErrorCode());
                responseDTO.setErrorDescription(ErrorCodeEnumeration.MEET0000.getErrorDescription());
            }

        } catch (MeetException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            responseDTO.setErrorCode(ex.getErrorCode());
            responseDTO.setErrorDescription(ex.getErrorDescription());
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }

        return responseDTO;
    }
}
