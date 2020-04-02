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

    public void moveMsg(KeyCode key)
    {
        Player player = board.getPlayer();

        String plX = String.valueOf(player.getX());
        String plY = String.valueOf(player.getY());

        plX = "0".repeat(3-plX.length()) + plX;
        plY = "0".repeat(3-plY.length()) + plY;

        Task<Void> sendTask = new SendTask("mov" + plX + plY, server);
        executor.submit(sendTask);
    }

    public void bombMsg()
    {
        Task<Void> sendTask = new SendTask("bmb", server);
        executor.submit(sendTask);
    }

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
        player.getBomb().place(player.getX()+5,player.getY()+5);
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
