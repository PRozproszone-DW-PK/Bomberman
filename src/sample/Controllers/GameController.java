package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Game.Board;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    public Canvas canvas;

    private Board board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        board = new Board(canvas);
        board.drawBoard();

        Platform.runLater(() -> canvas.getScene().setOnKeyPressed(this::move));
    }

    public void move(KeyEvent e)
    {
        Platform.runLater(() -> {

            if(e.getCode()== KeyCode.W)
            {
                if(!board.anyVerticalObstacles(board.getPlayer().getX(),
                        board.getPlayer().getX()+board.getPlayer().getWidth()-1,
                        board.getPlayer().getY()-board.getPlayer().getMoveSpeed()))
                    board.getPlayer().moveUp();
            }
            else if(e.getCode()== KeyCode.A)
            {
                if(!board.anyHorizontalObstacles(board.getPlayer().getY(),
                        board.getPlayer().getY()+board.getPlayer().getHeight()-1,
                        board.getPlayer().getX()-board.getPlayer().getMoveSpeed()))
                    board.getPlayer().moveLeft();
            }
            else if(e.getCode()== KeyCode.S)
            {
                if(!board.anyVerticalObstacles(board.getPlayer().getX(),
                        board.getPlayer().getX()+board.getPlayer().getWidth()-1,
                        board.getPlayer().getY()+board.getPlayer().getHeight()+board.getPlayer().getMoveSpeed()-1))
                    board.getPlayer().moveDown();
            }
            else if(e.getCode()== KeyCode.D)
            {
                if(!board.anyHorizontalObstacles(board.getPlayer().getY(),
                        board.getPlayer().getY()+board.getPlayer().getHeight()-1,
                        board.getPlayer().getX()+board.getPlayer().getWidth()+board.getPlayer().getMoveSpeed()-1))
                    board.getPlayer().moveRight();
            }
            else if(e.getCode()== KeyCode.SPACE)
            {
                board.getPlayer().placeBomb();
            }

            board.drawBoard();

        });
    }
}
