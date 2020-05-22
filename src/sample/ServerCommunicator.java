package sample;

import javafx.concurrent.Task;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.Game.Board;
import sample.Game.Player;
import sample.Tasks.BombTask;
import sample.Tasks.GameTask;
import sample.Tasks.ReadTask;
import sample.Tasks.SendTask;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerCommunicator {

    public static final ServerCommunicator SERVER_COMMUNICATOR = new ServerCommunicator();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private ExecutorService gameExecutor = Executors.newSingleThreadExecutor();
    private ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    private Socket server;

    private Board board;

    public ServerCommunicator()
    {

    }

    public static ServerCommunicator getInstance()
    {
        return SERVER_COMMUNICATOR;
    }

    public void openSocket(String address)
    {
        try {
            server = new Socket( address, 9797);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket()
    {
        return server;
    }

    public void closeSocket()
    {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public Board getBoard()
    {
        return board;
    }
    public void statusMsg(int x, int y,int bomb_x, int bomb_y,boolean bomb_status,int movCounter )
    {
        String plX = String.valueOf(x);
        String plY = String.valueOf(y);
        String bmX = String.valueOf(bomb_x);
        String bmY = String.valueOf(bomb_y);
        String bmS = String.valueOf(bomb_status);
        String mc = String.valueOf(movCounter);

        plX = "0".repeat(3-plX.length()) + plX;
        plY = "0".repeat(3-plY.length()) + plY;
        bmX = "0".repeat(3-bmX.length()) + bmX;
        bmY = "0".repeat(3-bmY.length()) + bmY;
        mc = "0".repeat(4-mc.length()) + mc;

        Task<Void> sendTask = new SendTask("sta" + plX + plY + bmX + bmY + "0" + mc , server);
        executor.submit(sendTask);
    }
   /* public void moveMsg(int x, int y)
    {
        String plX = String.valueOf(x);
        String plY = String.valueOf(y);

        plX = "0".repeat(3-plX.length()) + plX;
        plY = "0".repeat(3-plY.length()) + plY;

        Task<Void> sendTask = new SendTask("mov" + plX + plY, server);
        executor.submit(sendTask);
    }


    public void bombMsg()
    {
        Task<Void> sendTask = new SendTask("bmb", server);
        executor.submit(sendTask);
    }*/

    public void read(Stage stage)
    {
        Task<Void> readTask = new ReadTask(server,stage);
        executor.submit(readTask);
    }

    public void readInGame(Board board)
    {
        Task<Void> gameTask = new GameTask(board);
        gameExecutor.submit(gameTask);
    }

    public void placeBomb(Player player)
    {
        player.getBomb().place(((player.getX()+12)/25)*25,((player.getY()+12)/25)*25);
        poolExecutor.submit(new BombTask(player.getBomb()));
    }
    public void endMsg()
    {
        Task<Void> sendTask = new SendTask("end", server);
        executor.submit(sendTask);
    }
    public void exitMsg()
    {
        Task<Void> sendTask = new SendTask("ext", server);
        executor.submit(sendTask);
    }
}
