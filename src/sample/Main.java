package sample;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
        primaryStage.setScene(new Scene(root, 300, 350));
        primaryStage.setTitle("Bomberman the game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}