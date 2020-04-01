package sample.Tasks;

import javafx.application.Platform;
import javafx.concurrent.Task;
import sample.Game.Board;
import sample.ServerCommunicator;

import java.io.IOException;

public class GameTask extends Task<Void> {

    private Board board;

    public GameTask(Board board)
    {
        this.board = board;
    }

    @Override
    protected Void call() throws Exception {

        byte[] input = new byte[8];

        while(true) {
            try {
                ServerCommunicator.getInstance().getSocket().getInputStream().read(input, 0, 5);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String in = new String(input);

            if(in.substring(0,3).equals("end"))
                break;
            else if(in.substring(0,3).equals("mov"))
            {
                int x = Integer.parseInt((in.substring(3,6)));
                int y = Integer.parseInt((in.substring(6,9)));

                board.getEnemy().setX(x);
                board.getEnemy().setY(y);
            }
            /*else if(in.substring(0,4).equals("movW"))
            {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        board.getEnemy().moveUp();
                        board.drawBoard();
                    }
                });
            }
            else if(in.substring(0,4).equals("movS"))
            {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        board.getEnemy().moveDown();
                        board.drawBoard();
                    }
                });
            }
            else if(in.substring(0,4).equals("movA"))
            {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        board.getEnemy().moveLeft();
                        board.drawBoard();
                    }
                });
            }
            else if(in.substring(0,4).equals("movD"))
            {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        board.getEnemy().moveRight();
                        board.drawBoard();
                    }
                });
            }*/
            else if(in.substring(0,3).equals("bmb"))
            {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ServerCommunicator.getInstance().placeBomb(board.getEnemy());
                    }
                });
            }
        }

        return null;
    }
}
