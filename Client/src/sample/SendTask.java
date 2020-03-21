package sample;

import javafx.concurrent.Task;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SendTask extends Task<Void> {

    public SendTask() {
    }

    @Override
    protected Void call() throws Exception {

        updateMessage("Initiating...");
        InetAddress address2 = InetAddress.getByName("79.163.141.201");
        try (Socket server = new Socket( address2, 9797)) {

        } catch (IOException e) {
            updateMessage("Server connection error");
            return null;
        }
        updateMessage("Finished");
        return null;
    }
}