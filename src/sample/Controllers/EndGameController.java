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
    Label room;
    @FXML
    public void playAgain(MouseEvent e)
    {
        room.setText("Wait for enemy");
        //ServerCommunicator.getInstance().openSocket("25.103.157.11");
        ServerCommunicator.getInstance().openSocket("localhost");//("25.103.157.11");
        ServerCommunicator.getInstance().read((Stage)((Button) e.getSource()).getScene().getWindow());
    }
    @FXML
    public void exit(MouseEvent e)
    {
        ServerCommunicator.getInstance().exitMsg();
        Platform.exit();
        System.exit(0);
    }
    public void setScore(String score)
    {
        label.setText(score);
    }
}
