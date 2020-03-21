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

            while(alive)
            {
                buffIn.read(buffer);
                String msg = new String(buffer);
                System.out.println(msg);
                if((msg.substring(0,3)).equals("die"))
                {
                    System.out.println("Player " + playerNum + " dies!");
                    alive=false;
                }
                else if((msg.substring(0,3)).equals("mov"))
                {
                    System.out.println("Player " + playerNum + " moves " + msg.substring(3,4));
                }
                else if((msg.substring(0,3)).equals("bmb"))
                {
                    System.out.println("gowno");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
