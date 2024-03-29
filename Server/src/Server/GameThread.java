package Server;

import Game.Bomb;
import Game.Player;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameThread implements Runnable {

    private Player p1;
    private Socket p1_socket;

    private Player p2;
    private Socket p2_socket;

    private LinkedList<String> moves;

    private final Object lock1;
    private final Object lock2;

    private ExecutorService executor1 = Executors.newSingleThreadExecutor();
    private ExecutorService executor2 = Executors.newSingleThreadExecutor();

    public GameThread(Socket p1_socket, Socket p2_socket)
    {
        this.p1 = new Player(25,25,0, new Bomb(0));
        this.p2 = new Player(325,325,0, new Bomb(0));
        this.moves = new LinkedList<>();
        this.p1_socket=p1_socket;
        this.p2_socket=p2_socket;
        this.lock1 = new Object();
        this.lock2 = new Object();
    }

    public void addMove(String move)
    {
        synchronized (lock1) {
            moves.add(move);
            lock1.notify();
            lock1.notifyAll();
        }

    }

    public void bombUpdate(Player player, int pNum)
    {
        synchronized (lock2) {
            player.getBomb().setState(player.getBomb().getState() + 1);
            sendUpdate(pNum);
        }
    }

    public void processMove()
    {
        synchronized (lock2) {
            String move = moves.poll();

            if (move != null) {
                int pNum = Integer.parseInt(move.substring(11, 12));
                int p_x = Integer.parseInt(move.substring(0, 3));
                int p_y = Integer.parseInt(move.substring(3, 6));
                int p_bomb_state = Integer.parseInt(move.substring(6, 7));
                int mov_number = Integer.parseInt(move.substring(7, 11));

                if (pNum == 1) {
                    p1.setMsgNum(mov_number);

                    if (Math.abs(p_x - p2.getX()) >= 25 || Math.abs(p_y - p2.getY()) >= 25) {
                        p1.setX(p_x);
                        p1.setY(p_y);
                    }

                    if (p1.getBomb().getState() == 0 && p_bomb_state == 1) {
                        executor1.submit(new BombThread(this, p1, pNum));
                    }

                    sendUpdate(1);
                } else if (pNum == 2) {

                    p2.setMsgNum(mov_number);

                    if (Math.abs(p_x - p1.getX()) >= 25 || Math.abs(p_y - p1.getY()) >= 25) {
                        p2.setX(p_x);
                        p2.setY(p_y);
                    }

                    if (p2.getBomb().getState() == 0 && p_bomb_state == 1) {
                        executor2.submit(new BombThread(this, p2, pNum));

                    }

                    sendUpdate(2);
                }

            }
        }
    }

    public void sendUpdate(int pNum)
    {

        String pl1X = String.valueOf(p1.getX());
        String pl1Y = String.valueOf(p1.getY());
        String bm1S = String.valueOf(p1.getBomb().getState());
        String mc1 = String.valueOf(p1.getMsgNum());

        String pl2X = String.valueOf(p2.getX());
        String pl2Y = String.valueOf(p2.getY());
        String bm2S = String.valueOf(p2.getBomb().getState());
        String mc2 = String.valueOf(p2.getMsgNum());

        pl1X = "0".repeat(3-pl1X.length()) + pl1X;
        pl1Y = "0".repeat(3-pl1Y.length()) + pl1Y;
        mc1 = "0".repeat(4-mc1.length()) + mc1;

        pl2X = "0".repeat(3-pl2X.length()) + pl2X;
        pl2Y = "0".repeat(3-pl2Y.length()) + pl2Y;
        mc2 = "0".repeat(4-mc2.length()) + mc2;

        if(pNum==1)
        {
            try {
                if(!p1_socket.isClosed())
                {
                    p1_socket.getOutputStream().write(("sta"+pl1X + pl1Y + pl2X + pl2Y  + bm1S + bm2S + mc1 + "1").getBytes());
                    p1_socket.getOutputStream().flush();
                }
                if(!p2_socket.isClosed())
                {
                    p2_socket.getOutputStream().write(("sta"+pl2X + pl2Y + pl1X + pl1Y  + bm2S + bm1S + mc2 + "0").getBytes());
                    p2_socket.getOutputStream().flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                if(!p1_socket.isClosed())
                {
                    p1_socket.getOutputStream().write(("sta"+pl1X + pl1Y + pl2X + pl2Y + bm1S + bm2S + mc1 + "0").getBytes());
                    p1_socket.getOutputStream().flush();
                }
                if(!p2_socket.isClosed())
                {
                    p2_socket.getOutputStream().write(("sta"+pl2X + pl2Y + pl1X + pl1Y + bm2S + bm1S + mc2 + "1").getBytes());
                    p2_socket.getOutputStream().flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {
        while(true)
        {
            synchronized (lock1){
                if(!moves.isEmpty())
                    processMove();
                else{
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
