package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Game.Board;
import sample.ServerCommunicator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    public Canvas canvas;

    private Board board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        board = new Board(canvas);
        ServerCommunicator.getInstance().setBoard(board);
        board.drawBoard();

        Platform.runLater(() -> canvas.getScene().setOnKeyPressed(this::move));
        ServerCommunicator.getInstance().readInGame(board);
    }

    public void setAlly(int x, int y)
    {
        board.getPlayer().setX(x);
        board.getPlayer().setY(y);
    }

    public void setEnemy(int x, int y)
    {
        board.getEnemy().setX(x);
        board.getEnemy().setY(y);
    }

    public void move(KeyEvent e)
    {
        Platform.runLater(() -> {

            if(e.getCode()== KeyCode.W)
            {
                if(!board.anyVerticalObstacles(board.getPlayer().getX(),
                        board.getPlayer().getX()+board.getPlayer().getWidth()-1,
                        board.getPlayer().getY()-board.getPlayer().getMoveSpeed()))
                {
                    board.getPlayer().moveUp();
                    ServerCommunicator.getInstance().moveMsg(e.getCode());
                }
            }
            else if(e.getCode()== KeyCode.A)
            {
                if(!board.anyHorizontalObstacles(board.getPlayer().getY(),
                        board.getPlayer().getY()+board.getPlayer().getHeight()-1,
                        board.getPlayer().getX()-board.getPlayer().getMoveSpeed()))
                {
                    board.getPlayer().moveLeft();
                    ServerCommunicator.getInstance().moveMsg(e.getCode());
                }
            }
            else if(e.getCode()== KeyCode.S)
            {
                if(!board.anyVerticalObstacles(board.getPlayer().getX(),
                        board.getPlayer().getX()+board.getPlayer().getWidth()-1,
                        board.getPlayer().getY()+board.getPlayer().getHeight()+board.getPlayer().getMoveSpeed()-1))
                {
                    board.getPlayer().moveDown();
                    ServerCommunicator.getInstance().moveMsg(e.getCode());
                }
            }
            else if(e.getCode()== KeyCode.D)
            {
                if(!board.anyHorizontalObstacles(board.getPlayer().getY(),
                        board.getPlayer().getY()+board.getPlayer().getHeight()-1,
                        board.getPlayer().getX()+board.getPlayer().getWidth()+board.getPlayer().getMoveSpeed()-1))
                {
                    board.getPlayer().moveRight();
                    ServerCommunicator.getInstance().moveMsg(e.getCode());
                }
            }
            else if(e.getCode()== KeyCode.B)
            {
                board.getPlayer().placeBomb();
            }

            board.drawBoard();

        });
    }
    public static void endGame(Stage window, String score)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                window.close();

                Stage window = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EndGame.fxml"));

                try {
                    Parent root = fxmlLoader.load();

                    window.setScene(new Scene(root, 400, 400));
                    EndGameController gc = fxmlLoader.getController();
                    window.show();
                    if(score.equals("win"))gc.setScore("You win !!!");
                    else if(score.equals("los")) gc.setScore("You lost !!!");
                    else gc.setScore("Enemy has surrendered !!!");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @FXML
    public void exit(MouseEvent e)
    {
        ServerCommunicator.getInstance().exitMsg();
        Platform.exit();
        System.exit(0);
    }
}
