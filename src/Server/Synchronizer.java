package Server;

import java.io.IOException;
import java.net.Socket;

public class Synchronizer {

    public static synchronized void move(Socket playerSocket, Socket enemySocket, String msg)
    {
        try {
            enemySocket.getOutputStream().write((msg.substring(0, 9)).getBytes());
            playerSocket.getOutputStream().write(("ymv"+msg.substring(3,9)).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
