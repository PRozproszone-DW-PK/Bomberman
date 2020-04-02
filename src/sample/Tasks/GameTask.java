package sample.Tasks;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import sample.Controllers.GameController;
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

        byte[] input = new byte[9];

        while(true) {
            try {
                ServerCommunicator.getInstance().getSocket().getInputStream().read(input, 0, 9);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String in = new String(input);

            if(in.substring(0,3).equals("win")) {
                GameController.endGame((Stage) (board.getCanvas()).getScene().getWindow(),"win");
                break;
            }
            else  if(in.substring(0,3).equals("ext")) {
                GameController.endGame((Stage) (board.getCanvas()).getScene().getWindow(),"sur");
                break;
            }
            else if(in.substring(0,3).equals("los")) {
                GameController.endGame((Stage) (board.getCanvas()).getScene().getWindow(),"los");
                break;
            }
            else if(in.substring(0,3).equals("mov"))
            {
                int x = Integer.parseInt((in.substring(3,6)));
                int y = Integer.parseInt((in.substring(6,9)));

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        board.getEnemy().setX(x);
                        board.getEnemy().setY(y);
                        board.drawBoard();
                    }
                });
            }
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
