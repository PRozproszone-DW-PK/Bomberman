package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    private Socket player1;
    private Socket player2;
    ThreadPoolExecutor poolExecutor;

    public Server()
    {
        poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
    }

    public void start()
    {
        try{
            ServerSocket serverSocket = new ServerSocket(9797);
            player1 = serverSocket.accept();
            System.out.println("Player 1 connected");
            player2 = serverSocket.accept();
            System.out.println("Player 2 connected");

            player1.getOutputStream().write("start025025325325".getBytes(StandardCharsets.US_ASCII));
            player2.getOutputStream().write("start325325025025".getBytes(StandardCharsets.US_ASCII));

            System.out.println("Game is about to start");

            GameThread game = new GameThread(player1,player2);

            poolExecutor.submit(new PlayerTask(player1,player2,1, serverSocket, game));
            poolExecutor.submit(new PlayerTask(player2,player1,2, serverSocket, game));
            poolExecutor.submit(game);

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
