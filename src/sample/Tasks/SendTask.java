package sample.Tasks;
import javafx.concurrent.Task;

import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SendTask extends Task<Void> {

    private char letter;
    private Socket server;

    public SendTask(char letter, Socket server) {
        this.letter = letter;
        this.server = server;
    }

    @Override
    protected Void call() throws Exception {
        String str = "mov"+letter;

        server.getOutputStream().write(str.getBytes(StandardCharsets.US_ASCII));

        return null;
    }
}