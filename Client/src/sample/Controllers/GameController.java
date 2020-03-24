package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.ServerCommunicator;

import java.util.Arrays;


public class GameController {
    final private int size = 10;
    private char[][] board;
    private int pos_x;
    private int pos_y;
    private GameController()
    {
        this.board = new char[][]
                         {{'p', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                         {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                         {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                         {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                         {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                         {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                         {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                         {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                         {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                         {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
        System.out.print(Arrays.deepToString(board));
    }
    private void Game(int y, int x)
    {
        board[pos_y][pos_x]=' ';
        board[pos_y+y][pos_x+x]='p';
        for(int i=0; i< size;i++)
        {
            for(int j=0; j< size;j++)
            {
                System.out.print(board[i][j]+" ");
            }
        }
    }
    @FXML
    private void move(KeyEvent e)
    {
        if(e.getCode()== KeyCode.W)
        {
            if(pos_y!=0 && board[pos_y-1][pos_x]!='w')
            {
                this.Game(-1,0);
                ServerCommunicator.getInstance().moveMsg(e.getCode());
            }
        }
        else if(e.getCode()==KeyCode.A)
        {
            if(pos_x!=0 && board[pos_y][pos_x-1]!='w')
            {
                this.Game(0,-1);
                ServerCommunicator.getInstance().moveMsg(e.getCode());
            }
        }
        else if(e.getCode()==KeyCode.S)
        {
            if(pos_y!=size-1 && board[pos_y+1][pos_x]!='w')
            {
                this.Game(1,0);
                ServerCommunicator.getInstance().moveMsg(e.getCode());
            }
        }
        else if(e.getCode()==KeyCode.D)
        {
            if(pos_x!=size-1 && board[pos_y][pos_x+1]!='w')
            {
                this.Game(0,1);
                ServerCommunicator.getInstance().moveMsg(e.getCode());
            }
        }
    }
}
