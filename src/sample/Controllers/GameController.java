package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import sample.ServerCommunicator;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


public class GameController implements Initializable {
    static public int size = 10;
    private char[][] board;
    private int pos_x=0;
    private int pos_y=0;
    private int speed=5;
    private int radius=30;
    private int rec_size=30;
    private GraphicsContext gc;
    @FXML private Canvas img ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.board = new char[][]
                        {{'p', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', ' '},
                        {' ', ' ', ' ', ' ', 'w', ' ', ' ', ' ', 'w', ' '},
                        {' ', ' ', ' ', ' ', 'w', ' ', ' ', ' ', 'w', ' '},
                        {' ', ' ', 'w', 'w', 'w', ' ', ' ', ' ', 'w', ' '},
                        {' ', ' ', ' ', ' ', 'w', ' ', ' ', ' ', 'w', ' '},
                        {' ', ' ', ' ', ' ', 'w', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', 'w','w', 'w', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
        img.setFocusTraversable(true);
        gc = img.getGraphicsContext2D();
        drawCanvas(gc);
    }
    @FXML private void drawCanvas(GraphicsContext gc) {
        int  x=0, y=0;
        gc.setFill(Color.GREEN);
        gc.setLineWidth(5);
        for(int i=0; i< size;i++)
        {
            for(int j=0; j< size;j++) {
                if (board[i][j] == ' ') {
                    gc.setFill(Color.GREEN);
                }
                else if(board[i][j] == 'w') {
                    gc.setFill(Color.BLUE);
                }
                gc.fillRect(x, y, rec_size, rec_size);
                x+=30;
            }
            x=0;
            y+=30;
        }
        gc.setFill(Color.BLACK);
        gc.fillOval(pos_x, pos_y, radius, radius);
    }
    @FXML
    private void move(KeyEvent e)
    {
        if(e.getCode()== KeyCode.W)
        {
            if(pos_y!=0 && !(pos_y%rec_size==0 && (board[pos_y/rec_size-1][pos_x/rec_size]=='w' || board[pos_y/rec_size-1][pos_x/rec_size+1]=='w')))
            {
                pos_y-=speed;
                drawCanvas(gc);
                //ServerCommunicator.getInstance().moveMsg(e.getCode());
            }
        }
        else if(e.getCode()==KeyCode.A)
        {
            if(pos_x!=0 && !(pos_x%rec_size==0 && (board[pos_y/rec_size][pos_x/rec_size-1]=='w' || board[pos_y/rec_size+1][pos_x/rec_size-1]=='w') ))
            {
                pos_x-=speed;
                drawCanvas(gc);
                //ServerCommunicator.getInstance().moveMsg(e.getCode());
            }
        }
        else if(e.getCode()==KeyCode.S)
        {
            if(pos_y!=rec_size*(size-1) && !(pos_y%rec_size==0 && (board[pos_y/rec_size+1][pos_x/rec_size]=='w' || board[pos_y/rec_size+1][pos_x/rec_size+1]=='w')))
            {
                pos_y+=speed;
                drawCanvas(gc);
                //ServerCommunicator.getInstance().moveMsg(e.getCode());
            }
        }
        else if(e.getCode()==KeyCode.D)
        {
            if(pos_x!=rec_size*(size-1) && !(pos_x%rec_size==0 && (board[pos_y/rec_size][pos_x/rec_size+1]=='w' || board[pos_y/rec_size+1][pos_x/rec_size+1]=='w')))
            {
                pos_x+=speed;
                drawCanvas(gc);
                //ServerCommunicator.getInstance().moveMsg(e.getCode());
            }
        }

    }


}
