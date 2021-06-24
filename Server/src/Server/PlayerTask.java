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

    private GameThread game;

    public PlayerTask(Socket socket, Socket enemySocket, int num, ServerSocket server, GameThread game)
    {
        playerNum = num;
        buffer = new byte[14];
        this.socket = socket;
        this.enemySocket = enemySocket;
        this.server = server;
        this.game = game;
    }

    @Override
    public void run() {
        try(InputStream in = socket.getInputStream();
            BufferedInputStream buffIn = new BufferedInputStream(in)){

            while(socket.isConnected() && !socket.isClosed() && !enemySocket.isClosed() && !server.isClosed())
            {
                if(buffIn.available()>0 && buffIn.read(buffer,0,14)!=-1) {

                    String msg = new String(buffer);

                    switch ((msg.substring(0, 3))) {
                        case "ext":
                            enemySocket.getOutputStream().write("ext".getBytes());
                            socket.close();
                            enemySocket.close();
                            server.close();
                            Server server2 = new Server();
                            server2.start();
                            break;
                        case "end":
                            enemySocket.getOutputStream().write("win".getBytes());
                            socket.getOutputStream().write("los".getBytes());
                            socket.close();
                            enemySocket.close();
                            server.close();
                            Server server3 = new Server();
                            server3.start();
                            break;
                        case "sta":
                            game.addMove((msg.substring(3, 14))+ playerNum);
                            break;
                    }
                }

            }

        } catch (IOException e) {
           e.printStackTrace();
        }

    }
}
