package sample;

import javafx.concurrent.Task;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SendTask extends Task<Void> {
    private char letter;

    public SendTask(char letter) {
        this.letter=letter;
    }

    @Override
    protected Void call() throws Exception {
        String str = "mov"+letter;
        updateMessage("Initiating...");
        System.out.println("Initiating...");

        try (Socket server = new Socket( "25.103.157.11", 9797)) {
            server.getOutputStream().write(str.getBytes("US-ASCII"));
        } catch (IOException e) {
            updateMessage("Server connection error");
            System.out.println("Server connection error");
            return null;
        }
        updateMessage("Finished");
        System.out.println("Finished");
        return null;
    }
}