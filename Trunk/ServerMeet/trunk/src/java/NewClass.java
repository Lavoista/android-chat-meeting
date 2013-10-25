import com.fasterxml.jackson.databind.ObjectMapper;
import it.meet.service.common.util.DateUtils;
import it.meet.service.messaging.Message;
import it.meet.service.messaging.MessageType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * The client test
 * 
 * 
 * @author Luigi Vorraro
 */
public class NewClass {

    public static final String SERVER_HOSTNAME = "localhost";
    public static final int SERVER_PORT = 2002;

    public static void main(String[] args) {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // Connect to Nakov Chat Server
            Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Connected to server "
                    + SERVER_HOSTNAME + ":" + SERVER_PORT);
        } catch (IOException ioe) {
            System.err.println("Can not establish connection to "
                    + SERVER_HOSTNAME + ":" + SERVER_PORT);
            ioe.printStackTrace();
            System.exit(-1);
        }

        // Create and start Sender thread
        Sender sender = new Sender(out);
        sender.setDaemon(true);
        sender.start();

        try {
            // Read messages from the server and print them
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException ioe) {
            System.err.println("Connection to server broken.");
            ioe.printStackTrace();
        }

    }
}

class Sender extends Thread {

    private PrintWriter mOut;
    private String username;

    public Sender(PrintWriter aOut) {
        mOut = aOut;
    }

    /**
     * Until interrupted reads messages from the standard input (keyboard) and
     * sends them to the chat server through the socket.
     */
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Il tuo username:");
            username = in.readLine();

            Message message = new Message();
            message.setSender(username);
            message.setPassword(username);
            message.setMessageType(MessageType.AUTHENTICATION_TYPE);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRespnse = objectMapper.writeValueAsString(message);

            mOut.println(jsonRespnse);
            mOut.flush();

            while (!isInterrupted()) {
                String messageString = in.readLine();

                String[] arrayString = messageString.split(":");

                message = new Message();
                message.setSender(username);
                message.setReceiver(arrayString[0]);
                message.setMessage(arrayString[1]);
                message.setMessageType(MessageType.CHAT_TYPE);
                message.setTimestap(DateUtils.getString(new Date()));

                objectMapper = new ObjectMapper();
                jsonRespnse = objectMapper.writeValueAsString(message);

                mOut.println(jsonRespnse);
                mOut.flush();
            }
        } catch (Exception ioe) {
            // Communication is broken
        } finally {
            mOut.close();
        }
    }
}