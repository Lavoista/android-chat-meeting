package it.meet.service.user;

import it.meet.service.common.entity.ResponseDTO;
import it.meet.service.common.util.DeviceType;
import it.meet.service.user.entity.UserDTO;

public interface UserService {
    /**
     * Create user
     * 
     * @param user the user to create
     * @return 
     */
    ResponseDTO createUser(UserDTO user);
    
    UserDTO findUserByUserName(String userName);
    
    ResponseDTO addFriend(String username, String friendUsername);
    
    ResponseDTO sendFriendRequest(String username, String friendUsername, String message);
    
    ResponseDTO cancelFriendRequest(String username, String friendUsername);
    
    ResponseDTO removeFriend(String username, String friendUsername);
    
    ResponseDTO updateRegistrationId(String username, String registrationId, DeviceType deviceType);
    
    ResponseDTO updatePhoto(String username, byte[] photo);
    
}
