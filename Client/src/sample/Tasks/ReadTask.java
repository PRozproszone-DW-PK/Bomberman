package sample.Tasks;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.MenuController;

import java.io.IOException;
import java.net.Socket;

public class ReadTask extends Task<Void> {

    private byte[] input;
    private Socket server;
    private Stage stage;

    public ReadTask(Socket server,Stage stage) {
        input = new byte[8];
        this.server = server;
        this.stage = stage;
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
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stage.close();

                    Stage window = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Game.fxml"));

                    try {
                        Parent root = fxmlLoader.load();
                        window.setScene(new Scene(root, 400, 400));
                        window.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        return null;
    }
}
