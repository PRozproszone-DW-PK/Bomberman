package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.ServerCommunicator;

import java.io.IOException;

public class MenuController {

    @FXML
    public void play(MouseEvent e)
    {
        ServerCommunicator.getInstance().openSocket("localhost");//("25.103.157.11");
        ServerCommunicator.getInstance().read((Stage)((Button) e.getSource()).getScene().getWindow());
    }

    @FXML
    public void exit(MouseEvent e)
    {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.close();
    }

}
