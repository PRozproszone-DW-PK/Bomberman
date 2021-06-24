package sample;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import sample.Game.Board;
import sample.Tasks.GameTask;
import sample.Tasks.ReadTask;
import sample.Tasks.SendTask;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCommunicator {

    public static final ServerCommunicator SERVER_COMMUNICATOR = new ServerCommunicator();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private ExecutorService gameExecutor = Executors.newSingleThreadExecutor();
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
            server = new Socket( address, 50000);
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

    public void statusMsg(int x, int y,boolean bomb_status,int movCounter )
    {
        String plX = String.valueOf(x);
        String plY = String.valueOf(y);
        String bmS;
        if(bomb_status)bmS = String.valueOf(1);
        else bmS = String.valueOf(0);


        String mc = String.valueOf(movCounter);

        plX = "0".repeat(3-plX.length()) + plX;
        plY = "0".repeat(3-plY.length()) + plY;
        mc = "0".repeat(4-mc.length()) + mc;
        System.out.println("sta" + plX + plY + bmS + mc );
        Task<Void> sendTask = new SendTask("sta" + plX + plY + bmS + mc , server);
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