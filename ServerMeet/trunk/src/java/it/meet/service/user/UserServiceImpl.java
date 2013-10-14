package it.meet.service.user;

import it.meet.administrator.user.UserAdministrator;
import it.meet.common.database.DatabaseAdministrator;
import it.meet.service.common.entity.ResponseDTO;
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

            responseDTO.setErrorCode("");
            responseDTO.setErrorDescription("");

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

            responseDTO.setErrorCode("");
            responseDTO.setErrorDescription("");

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

            responseDTO.setErrorCode("");
            responseDTO.setErrorDescription("");

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

            responseDTO.setErrorCode("");
            responseDTO.setErrorDescription("");

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
            
            responseDTO.setErrorCode("");
            responseDTO.setErrorDescription("");
            
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
