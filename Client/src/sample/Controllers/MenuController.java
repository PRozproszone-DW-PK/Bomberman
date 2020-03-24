package sample.Controllers;

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
        ServerCommunicator.getInstance().openSocket("25.103.157.11");
        ServerCommunicator.getInstance().read();
    }


    @FXML
    public void exit(MouseEvent e)
    {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.close();
    }

}
