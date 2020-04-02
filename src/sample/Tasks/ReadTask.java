package sample.Tasks;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.GameController;
import sample.Game.Board;

import java.io.IOException;
import java.net.Socket;

public class ReadTask extends Task<Void> {

    private byte[] input;
    private Socket server;
    private Stage stage;
    private Board board;

    public ReadTask(Socket server,Stage stage) {
        input = new byte[8];
        this.server = server;
        this.stage = stage;
    }

    @Override
    protected Void call() throws Exception {

        byte[] input = new byte[17];

        try {
            server.getInputStream().read(input,0,17);
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


                    int alX = Integer.parseInt(in.substring(5,8));
                    int alY = Integer.parseInt(in.substring(8,11));

                    int enX = Integer.parseInt(in.substring(11,14));
                    int enY = Integer.parseInt(in.substring(14,17));



                    try {
                        Parent root = fxmlLoader.load();

                        window.setScene(new Scene(root, 400, 500));
                        GameController gc = fxmlLoader.getController();
                        gc.setAlly(alX,alY);
                        gc.setEnemy(enX,enY);
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
