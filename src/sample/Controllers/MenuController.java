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

public class MenuController {

    @FXML
    public void play(MouseEvent e)
    {
        ServerCommunicator.getInstance().openSocket("25.103.157.11");
        ServerCommunicator.getInstance().read((Stage)((Button) e.getSource()).getScene().getWindow());
    }

    @FXML
    public void exit(MouseEvent e)
    {
        ServerCommunicator.getInstance().exitMsg();
        Platform.exit();
        System.exit(0);
    }

}
