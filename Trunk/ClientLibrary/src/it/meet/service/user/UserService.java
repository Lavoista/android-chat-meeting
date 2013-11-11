package it.meet.service.user;

import it.meet.service.common.entity.ResponseDTO;
import it.meet.service.common.util.DeviceType;
import it.meet.service.user.entity.UserDTO;

public interface UserService {
    /**
     * Create user on databse
     * 
     * @param user the user to create
     * @return 
     */
    ResponseDTO createUser(UserDTO user);
    /**
     * Find user by username
     * 
     * @param userName
     * @return 
     */
    UserDTO findUserByUserName(String userName);
    /**
     * Add fried
     * 
     * @param username
     * @param friendUsername
     * @return 
     */
    ResponseDTO addFriend(String username, String friendUsername);
    
    ResponseDTO sendFriendRequest(String username, String friendUsername, String message);
    
    ResponseDTO cancelFriendRequest(String username, String friendUsername);
    
    ResponseDTO removeFriend(String username, String friendUsername);
    
    ResponseDTO updateRegistrationId(String username, String registrationId, DeviceType deviceType);
    
    ResponseDTO updatePhoto(String username, byte[] photo);
    /**
     * Execute the login action
     * 
     * @param username of user
     * @param password of user
     * 
     * @return the response dto
     * 
     */
    ResponseDTO login(String username, String password);
    
}
