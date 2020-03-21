package sample;
import javafx.concurrent.Task;
import java.io.*;
import java.net.Socket;

public class SendTask extends Task<Void> {
    private char letter;
    private Socket server;
    public SendTask(char letter,Socket server) {
        this.letter=letter;
        this.server=server;
    }

    @Override
    protected Void call() throws Exception {
        String str = "mov"+letter;
        updateMessage("Initiating...");
        System.out.println("Initiating...");


        server.getOutputStream().write(str.getBytes("US-ASCII"));
        //server.getOutputStream().flush();

        updateMessage("Finished");
        System.out.println("Finished");
        return null;
    }
}