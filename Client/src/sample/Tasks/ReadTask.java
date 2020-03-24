package sample.Tasks;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class ReadTask extends Task<Void> {

    private byte[] input;
    private Socket server;

    public ReadTask(Socket server) {
        input = new byte[8];
        this.server = server;
    }

    @Override
    protected Void call() throws Exception {

        byte[] input = new byte[8];

        try {
            server.getInputStream().read(input,0,5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String in = new String(input);

        if(in.substring(0,5).equals("start"))
        {
            Stage window = new Stage();
            Parent root = null;
            try {
                System.out.println(root);
                root = FXMLLoader.load(getClass().getResource("./Game.fxml"));
                System.out.println(root);
                System.out.println("cojest");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            window.setTitle("Bomberman the game");
            window.setScene(new Scene(root, 300, 350));
            window.show();
        }

        return null;
    }
}
