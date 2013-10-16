package it.meet.chat.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class create a server socket and accept the client connetion.
 *
 * @author Luigi Vorraro
 */
public class ChatServerListener extends Thread {

    /**
     * The lister port of server socket
     */
    private static final int LISTENING_PORT = 2002;
    /**
     * Indicates if the termination event has been dispatched to this action.
     */
    private boolean terminated = false;
    /**
     * The server socket
     */
    private ServerSocket serverSocket;
    /**
     * The dispatchet of message
     */
    private ServerDispatcher serverDispatcher;

    @Override
    public void run() {
        // Open server socket for listening
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
            System.out.println("NakovChatServer started on port " + LISTENING_PORT);

            // Start ServerDispatcher thread
            serverDispatcher = new ServerDispatcher();
            serverDispatcher.start();

            // Accept and handle client connections
            while (!terminated) {
                try {
                    Socket socket = serverSocket.accept();

                    System.out.println("CLient accepted on port " + socket.getPort());

                    ClientControlThread clientControlThread = new ClientControlThread(socket, serverDispatcher);
                    clientControlThread.start();

                } catch (IOException ioe) {
                    Logger.getLogger(ClientControlThread.class.getName()).log(Level.SEVERE, null, ioe);
                }
            }
        } catch (IOException se) {
            System.err.println("Can not start listening on port " + LISTENING_PORT);
            Logger.getLogger(ChatServerListener.class.getName()).log(Level.SEVERE, null, se);
        } finally {
            serverDispatcher.terminate();
        }
    }

    /**
     * Terminate the current thread
     */
    public final void terminate() {
        terminated = true;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(ChatServerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
