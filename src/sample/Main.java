package sample;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Game.fxml"));
        primaryStage.setScene(new Scene(root, 300, 350));
        primaryStage.setTitle("Bomberman the game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
