package it.meet.administrator.user;

import it.meet.beans.FriendsRequests;
import it.meet.beans.FriendsRequestsId;
import it.meet.beans.Users;
import it.meet.service.common.util.DateUtils;
import it.meet.service.common.util.ErrorCodeEnumeration;
import it.meet.service.common.util.MeetException;
import it.meet.service.common.util.StringUtils;
import it.meet.service.user.entity.UserDTO;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * The user aministrator class
 *
 * @author Luigi Vorraro
 */
public class UserAdministrator {

    /**
     * Create a user administrator
     */
    public UserAdministrator() {
    }

    /**
     * Check if the user is authenticated with user and password passed
     *
     * @param username of user
     * @param password of user
     *
     * @return true if the user is authenticate, false otherwise.
     */
    public boolean authenticate(String username, String password, Session session) throws MeetException {
        boolean authenticate = false;
        try {

            Criteria criteria = session.createCriteria(Users.class);
            criteria.add(Restrictions.eq("username", username));
            criteria.add(Restrictions.eq("password", password));

            List<Users> result = criteria.list();
            if (result != null && !result.isEmpty()) {
                authenticate = true;
            }
        } catch (Exception ex) {
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }
        return authenticate;
    }

    /**
     * Create a new user on database
     *
     * @param user the user to create
     *
     */
    public void createUser(UserDTO userDTO, Session session) throws MeetException {

        checkUser(userDTO, session);

        Users user = new Users();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        if (StringUtils.isNotEmpty(userDTO.getName())) {
            user.setName(userDTO.getName());
        }
        if (StringUtils.isNotEmpty(userDTO.getSurname())) {
            user.setPassword(userDTO.getSurname());
        }
        if (StringUtils.isNotEmpty(userDTO.getSex())) {
            user.setSex(userDTO.getSex().charAt(0));
        }
        if (StringUtils.isNotEmpty(userDTO.getSex()) && StringUtils.checkEmailValidity(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getAge() > 0) {
            user.setAge(userDTO.getAge());
        }

        if (StringUtils.isNotEmpty(userDTO.getTelephoneNumber()) && StringUtils.checkPhoneNumberValidity(userDTO.getTelephoneNumber())) {
            user.setTelephonenumber(userDTO.getTelephoneNumber());
        }

        try {
            user.setEnddate(DateUtils.getDefaultEndDateString());
        } catch (ParseException ex) {
            Logger.getLogger(UserAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(UserAdministrator.class.getName()).log(Level.SEVERE, null, e);
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

    /**
     * Get the users with username specified
     *
     * @param username of user to search
     *
     * @return result user
     *
     * @throws MeetException
     */
    public Users getUserByUsername(String username, Session session) throws MeetException {
        Users user = null;

        try {

            Criteria criteria = session.createCriteria(Users.class);
            criteria.add(Restrictions.eq("username", username));

            List<Users> result = criteria.list();
            if (result != null && !result.isEmpty()) {
                user = result.get(0);
            }
        } catch (Exception ex) {
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }

        return user;
    }

    /**
     * Add one friend to list of user with username specified
     *
     * @param username of user
     * @param friendUsername of friend
     */
    public void addFriend(String username, String friendUsername, Session session) throws MeetException {

        Transaction tx = null;
        try {

            if (StringUtils.isEmpty(username)) {
                throw new MeetException(ErrorCodeEnumeration.MEET0009);
            }

            if (StringUtils.isEmpty(friendUsername)) {
                throw new MeetException(ErrorCodeEnumeration.MEET0010);
            }

            if (username.equals(friendUsername)) {
                throw new MeetException(ErrorCodeEnumeration.MEET0011);
            }

            Users user = getUserByUsername(username, session);
            if (user == null) {
                throw new MeetException(ErrorCodeEnumeration.MEET0008, username);
            }

            Users friend = getUserByUsername(friendUsername, session);
            if (friend == null) {
                throw new MeetException(ErrorCodeEnumeration.MEET0008, friendUsername);
            }

            user.getFriends().add(friend);
            friend.getFriends().add(user);
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            session.saveOrUpdate(friend);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }
    }

    /**
     * Add one friend to list of user with username specified
     *
     * @param username of user
     * @param friendUsername of friend
     */
    public void sendFriendRequest(String username, String friendUsername, String message, Session session) throws MeetException {

        Transaction tx = null;
        try {

            if (StringUtils.isEmpty(username)) {
                throw new MeetException(ErrorCodeEnumeration.MEET0009);
            }

            if (StringUtils.isEmpty(friendUsername)) {
                throw new MeetException(ErrorCodeEnumeration.MEET0010);
            }

            if (username.equals(friendUsername)) {
                throw new MeetException(ErrorCodeEnumeration.MEET0011);
            }

            Users user = getUserByUsername(username, session);
            if (user == null) {
                throw new MeetException(ErrorCodeEnumeration.MEET0008, username);
            }

            Users friend = getUserByUsername(friendUsername, session);
            if (friend == null) {
                throw new MeetException(ErrorCodeEnumeration.MEET0008, friendUsername);
            }

            FriendsRequests request = new FriendsRequests();
            FriendsRequestsId id = new FriendsRequestsId();
            id.setUsername(username);
            id.setUsernamefriend(friendUsername);
            id.setStartdate(DateUtils.getString(new Date()));
            request.setId(id);
            request.setDaterequest(new Date());
            request.setMessage(message);
            request.setEnddate(DateUtils.getDefaultEndDateString());


            tx = session.beginTransaction();
            session.saveOrUpdate(request);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }
    }

    /**
     * Add one friend to list of user with username specified
     *
     * @param username of user
     * @param friendUsername of friend
     */
    public void removeFriend(String username, String friendUsername, Session session) throws MeetException {

        Transaction tx = null;
        try {

            if (StringUtils.isEmpty(username)) {
                throw new MeetException(ErrorCodeEnumeration.MEET0009);
            }

            if (StringUtils.isEmpty(friendUsername)) {
                throw new MeetException(ErrorCodeEnumeration.MEET0010);
            }

            if (username.equals(friendUsername)) {
                throw new MeetException(ErrorCodeEnumeration.MEET0011);
            }

            Users user = getUserByUsername(username, session);
            if (user == null) {
                throw new MeetException(ErrorCodeEnumeration.MEET0008, username);
            }

            Users friend = getUserByUsername(friendUsername, session);
            if (friend == null) {
                throw new MeetException(ErrorCodeEnumeration.MEET0008, friendUsername);
            }

            user.getFriends().remove(friend);
            friend.getFriends().remove(user);
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            session.saveOrUpdate(friend);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }
    }

    /**
     * Add one friend to list of user with username specified
     *
     * @param username of user
     * @param friendUsername of friend
     */
    public void removeUnacceptedFriendRequest(String username, String friendUsername, Session session) throws MeetException {

        Transaction tx = null;
        try {

            List<FriendsRequests> friendsRequestses = session.createCriteria(FriendsRequests.class)
                    .add(Restrictions.eq("sender.username", username))
                    .add(Restrictions.eq("receiver.username", friendUsername))
                    .add(Restrictions.eq("enddate", DateUtils.getDefaultEndDateString()))
                    .add(Restrictions.isNull("accepted"))
                    .list();

            if (friendsRequestses != null && !friendsRequestses.isEmpty()) {
                Date now = new Date();
                tx = session.beginTransaction();
                for (FriendsRequests request : friendsRequestses) {
                    request.setEnddate(DateUtils.getString(now));
                    session.saveOrUpdate(request);
                }
                tx.commit();
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET0013, username, friendUsername);
            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }
    }

    /**
     *  Check if the user have a registration id to send a offline notification and 
     * if there isn't a notification already sent.
     * 
     * @param username the user to check
     * @param session the session from database
     * 
     * @return the registration id if need to send a notification.
     * 
     * @throws MeetException if any error occurs
     */
    public String checkSendNotification(String username, Session session) throws MeetException {
        String registrationId = "";
        try {

            Criteria criteria = session.createCriteria(Users.class);
            criteria.add(Restrictions.eq("username", username));
            criteria.add(Restrictions.isNotEmpty("registrationId"));
            


            List<Users> result = criteria.list();
            if (result != null && !result.isEmpty()) {
                registrationId = "sdkhoisdhioghdsoighdsoi";
            }
        } catch (Exception ex) {
            if (ex instanceof MeetException) {
                throw ((MeetException) ex);
            } else {
                throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
            }
        }




        return registrationId;
    }

    /**
     * Check if the user information are valid
     *
     * @param user the user into to check
     * @throws Exception
     */
    private void checkUser(UserDTO user, Session session) throws MeetException {
        if (user == null) {
            throw new MeetException(ErrorCodeEnumeration.MEET0001);
        } else if (StringUtils.isEmpty(user.getUsername())) {
            throw new MeetException(ErrorCodeEnumeration.MEET0004);
        } else if (StringUtils.isEmpty(user.getPassword())) {
            throw new MeetException(ErrorCodeEnumeration.MEET0005);
        } else if (StringUtils.isNotEmpty(user.getEmail()) && !StringUtils.checkEmailValidity(user.getEmail())) {
            throw new MeetException(ErrorCodeEnumeration.MEET0006);
        } else if (getUserByUsername(user.getUsername(), session) != null) {
            throw new MeetException(ErrorCodeEnumeration.MEET0002, user.getUsername());
        } else if (StringUtils.isNotEmpty(user.getTelephoneNumber()) && !StringUtils.checkPhoneNumberValidity(user.getTelephoneNumber())) {
            throw new MeetException(ErrorCodeEnumeration.MEET0012);
        }
    }
}
