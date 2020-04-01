package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class EndGameController {
    @FXML
    public void exit(MouseEvent e)
    {
        Platform.exit();
        System.exit(0);
    }
}
