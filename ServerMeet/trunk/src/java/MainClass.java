
import it.meet.chat.control.ChatServerListener;
import it.meet.service.common.entity.ResponseDTO;
import it.meet.service.user.UserServiceImpl;
import it.meet.service.user.entity.UserDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class test
 * 
 * @author Luigi Vorraro
 */
public class MainClass {

    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        mainClass.runServerChat();
    }
    
    private void runServerChat(){
        ChatServerListener chatServerListener = new ChatServerListener();
        chatServerListener.start();
        
        while(true){
            
        }
    }

    private void testInsertUser() {



        UserServiceImpl serviceImpl = new UserServiceImpl();

        UserDTO user = new UserDTO();
        user.setUsername("antonio");
        user.setPassword("aaaa");

        user.setAge(16);
        user.setEmail("aaa@sfsd.it");
        user.setName("Antonio");
        user.setSurname("Albano");
        user.setSex("M");
        user.setTelephoneNumber("+390812211234");
        
        ResponseDTO responseDTO = serviceImpl.createUser(user);
        
        Logger.getLogger(MainClass.class.getName()).log(Level.INFO, responseDTO.getErrorCode() + " " + responseDTO.getErrorDescription());
    }

    private void testAddFriend() {

        UserServiceImpl serviceImpl = new UserServiceImpl();

        ResponseDTO responseDTO = serviceImpl.addFriend("antonio", "luirzy");
        
        Logger.getLogger(MainClass.class.getName()).log(Level.INFO, responseDTO.getErrorCode() + " " + responseDTO.getErrorDescription());
    }
    
    
    private void testSendFriendRequest() {

        UserServiceImpl serviceImpl = new UserServiceImpl();

        ResponseDTO responseDTO = serviceImpl.sendFriendRequest("antonio", "luirzy", "Ciao sono Antonio, come stati???");
        
        Logger.getLogger(MainClass.class.getName()).log(Level.INFO, responseDTO.getErrorCode() + " " + responseDTO.getErrorDescription());
    }
    
    
    private void testRemoveFriend() {

        UserServiceImpl serviceImpl = new UserServiceImpl();

        ResponseDTO responseDTO = serviceImpl.removeFriend("antonio", "luirzy");
        
        Logger.getLogger(MainClass.class.getName()).log(Level.INFO, responseDTO.getErrorCode() + " " + responseDTO.getErrorDescription());
    }
    
    private void testRemoveFriendReq() {

        UserServiceImpl serviceImpl = new UserServiceImpl();

        ResponseDTO responseDTO = serviceImpl.cancelFriendRequest("antonio", "luirzy");
        
        Logger.getLogger(MainClass.class.getName()).log(Level.INFO, responseDTO.getErrorCode() + " " + responseDTO.getErrorDescription());
    }
}
