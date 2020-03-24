package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import sample.ServerCommunicator;


public class GameController {

    @FXML
    private Label statusLabel;


    @FXML
    private void SendMessage(ActionEvent event) {

      //  Task<Void> sendTask = new SendTask('e');
       //statusLabel.textProperty().bind(sendTask.messageProperty());
       // executor.submit(sendTask);

    }

    @FXML
    private void move(KeyEvent e)
    {
        ServerCommunicator.getInstance().moveMsg(e.getCode());
    }

}
