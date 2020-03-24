package sample;

import javafx.concurrent.Task;
import javafx.scene.input.KeyCode;
import sample.Tasks.ReadTask;
import sample.Tasks.SendTask;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCommunicator {

    public static final ServerCommunicator SERVER_COMMUNICATOR = new ServerCommunicator();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Socket server;

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

    public void moveMsg(KeyCode key)
    {
        if(key==KeyCode.W)
        {
            Task<Void> sendTask = new SendTask('W', server);
            executor.submit(sendTask);
            System.out.println("W key was pressed");
        }
        else if(key==KeyCode.A)
        {
            Task<Void> sendTask = new SendTask('A', server);
            executor.submit(sendTask);
            System.out.println("W key was pressed");
        }
        else if(key==KeyCode.S)
        {
            Task<Void> sendTask = new SendTask('S', server);
            executor.submit(sendTask);
            System.out.println("S key was pressed");
        }
        else if(key==KeyCode.D)
        {
            Task<Void> sendTask = new SendTask('D', server);
            executor.submit(sendTask);
            System.out.println("D key was pressed");
        }
    }

    public void read()
    {
        Task<Void> readTask = new ReadTask(server);
        executor.submit(readTask);
    }
}
