package Server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class PlayerTask implements Runnable {

    private int playerNum;
    private Socket socket;
    private boolean alive;
    byte[] buffer;

    public PlayerTask(Socket socket, int num)
    {
        playerNum = num;
        buffer = new byte[4];
        alive = true;
        this.socket = socket;
    }

    @Override
    public void run() {

        try(InputStream in = socket.getInputStream();
            BufferedInputStream buffIn = new BufferedInputStream(in)){

            while(alive && socket.isConnected())
            {
                if(buffIn.read(buffer,0,4)!=-1) {

                    String msg = new String(buffer);

                    switch ((msg.substring(0, 3))) {
                        case "die":
                            System.out.println("Player " + playerNum + " dies!");
                            alive = false;
                            break;
                        case "mov":
                            System.out.println("Player " + playerNum + " moves " + msg.substring(3, 4));
                            break;
                        case "bmb":
                            System.out.println("Player " + playerNum + " places bomb");
                            break;
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
