package sample.Tasks;
import javafx.concurrent.Task;

import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SendTask extends Task<Void> {

    private String word;
    private Socket server;

    public SendTask(String word, Socket server) {
        this.word = word;
        this.server = server;
    }

    @Override
    protected Void call() throws Exception {
        server.getOutputStream().write(word.getBytes(StandardCharsets.US_ASCII));
        return null;
    }
}