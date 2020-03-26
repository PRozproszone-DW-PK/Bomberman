package Server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class PlayerTask implements Runnable {

    private int playerNum;
    private Socket socket;
    private Socket enemySocket;
    byte[] buffer;

    public PlayerTask(Socket socket,Socket enemySocket, int num)
    {
        playerNum = num;
        buffer = new byte[4];
        this.socket = socket;
        this.enemySocket = enemySocket;
    }

    @Override
    public void run() {

        try(InputStream in = socket.getInputStream();
            BufferedInputStream buffIn = new BufferedInputStream(in)){

            while(socket.isConnected())
            {
                if(buffIn.read(buffer,0,4)!=-1) {

                    String msg = new String(buffer);

                    switch ((msg.substring(0, 3))) {
                        case "die":
                            break;
                        case "mov":
                            enemySocket.getOutputStream().write((msg.substring(0, 4)).getBytes());
                            break;
                        case "bmb":
                            enemySocket.getOutputStream().write("bmb".getBytes());
                            break;
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
