package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Game.Board;
import sample.ServerCommunicator;

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
            else if(e.getCode()== KeyCode.SPACE)
            {
                board.getPlayer().placeBomb();
               // ServerCommunicator.getInstance().placeBomb(board.getPlayer());
            }

            board.drawBoard();

        });
    }
}
