package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.ServerCommunicator;

public class EndGameController {
    @FXML
    Label label;
    @FXML
    public void playAgain(MouseEvent e)
    {
        ServerCommunicator.getInstance().openSocket("25.103.157.11");
        //ServerCommunicator.getInstance().openSocket("localhost");//("25.103.157.11");
        ServerCommunicator.getInstance().read((Stage)((Button) e.getSource()).getScene().getWindow());
    }
    @FXML
    public void exit(MouseEvent e)
    {
        Platform.exit();
        System.exit(0);
    }
    public void setScore(String score)
    {
        label.setText(score);
    }
}
