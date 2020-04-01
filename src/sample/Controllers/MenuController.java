package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.ServerCommunicator;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private MediaPlayer mediaPlayer;

    @FXML
    public void play(MouseEvent e)
    {
        //ServerCommunicator.getInstance().openSocket("25.103.157.11");
        ServerCommunicator.getInstance().openSocket("localhost");//("25.103.157.11");
        ServerCommunicator.getInstance().read((Stage)((Button) e.getSource()).getScene().getWindow());
    }

    @FXML
    public void exit(MouseEvent e)
    {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*Media media = new Media(this.getClass().getResource("/title.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();*/
    }
}
