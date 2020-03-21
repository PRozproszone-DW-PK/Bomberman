package sample;

import javafx.application.Application;
import javafx.concurrent.Task;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 500));

        primaryStage.show();

        root.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                Task<Void> sendTask = new SendTask('W');
                executor.submit(sendTask);
                System.out.println("W key was pressed");
            }
            if (e.getCode() == KeyCode.A) {
                Task<Void> sendTask = new SendTask('A');
                executor.submit(sendTask);
                System.out.println("A key was pressed");
            }
            if (e.getCode() == KeyCode.D) {
                Task<Void> sendTask = new SendTask('D');
                executor.submit(sendTask);
                System.out.println("D key was pressed");
            }
            if (e.getCode() == KeyCode.S) {
                Task<Void> sendTask = new SendTask('S');
                executor.submit(sendTask);
                System.out.println("S key was pressed");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
