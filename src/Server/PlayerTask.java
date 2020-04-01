package Server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PlayerTask implements Runnable {

    private int playerNum;
    private Socket socket;
    private Socket enemySocket;
    private ServerSocket server;
    byte[] buffer;

    public PlayerTask(Socket socket, Socket enemySocket, int num, ServerSocket server)
    {
        playerNum = num;
        buffer = new byte[9];
        this.socket = socket;
        this.enemySocket = enemySocket;
        this.server = server;
    }

    @Override
    public void run() {

        try(InputStream in = socket.getInputStream();
            BufferedInputStream buffIn = new BufferedInputStream(in)){

            while(socket.isConnected())
            {
                if(buffIn.read(buffer,0,9)!=-1) {

                    String msg = new String(buffer);

                    switch ((msg.substring(0, 3))) {
                        case "end":
                            enemySocket.getOutputStream().write("los".getBytes());
                            socket.getOutputStream().write("win".getBytes());
                            socket.close();
                            enemySocket.close();
                            server.close();
                            Server server = new Server();
                            server.start();
                            break;
                        case "mov":
                            enemySocket.getOutputStream().write((msg.substring(0, 9)).getBytes());
                            break;
                        case "bmb":
                            enemySocket.getOutputStream().write("bmb".getBytes());
                            break;
                    }
                }

            }

        } catch (IOException e) {
           // e.printStackTrace();
        }

    }
}
