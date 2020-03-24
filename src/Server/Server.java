package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    private ServerSocket serverSocket;
    private Socket player1;
    private Socket player2;
    ThreadPoolExecutor poolExecutor;
    boolean gameOn;

    public Server()
    {
        poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    }

    public void start()
    {
        try{
            ServerSocket serverSocket = new ServerSocket(9797);
            player1 = serverSocket.accept();
            System.out.println("Player 1 connected");
            player2 = serverSocket.accept();
            System.out.println("Player 2 connected");

            player1.getOutputStream().write("start".getBytes(StandardCharsets.US_ASCII));
            player2.getOutputStream().write("start".getBytes(StandardCharsets.US_ASCII));

            System.out.println("Game is about to start");

            poolExecutor.submit(new PlayerTask(player1,1));
            poolExecutor.submit(new PlayerTask(player2,2));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gameOver()
    {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }
}
