package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    @FXML
    private Label statusLabel;
    @FXML
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    @FXML
    private void SendMessage(ActionEvent event) {

      //  Task<Void> sendTask = new SendTask('e');
       //statusLabel.textProperty().bind(sendTask.messageProperty());
       // executor.submit(sendTask);

    }

}
