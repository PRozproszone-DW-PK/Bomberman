package sample.Tasks;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import sample.Controllers.GameController;
import sample.Game.Board;
import sample.Game.GameStatus;
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
            else if(in.substring(0,3).equals("sta"))
            {
                int my_x = Integer.parseInt((in.substring(3,6)));
                int my_y = Integer.parseInt((in.substring(6,9)));
                int enemy_x = Integer.parseInt((in.substring(9,12)));
                int enemy_y = Integer.parseInt((in.substring(12,15)));
                int my_bomb_x = Integer.parseInt((in.substring(15,18)));
                int my_bomb_y = Integer.parseInt((in.substring(18,21)));
                int enemy_bomb_x = Integer.parseInt((in.substring(21,25)));
                int enemy_bomb_y = Integer.parseInt((in.substring(25,28)));
                int my_bomb_state = Integer.parseInt((in.substring(28,29)));
                int enemy_bomb_state = Integer.parseInt((in.substring(29,30)));
                int mov_number = Integer.parseInt((in.substring(30,34)));

                Platform.runLater(() -> {

                    int i;
                    for( i=0;i<board.getMoves().size();i++)
                    {
                        if(mov_number == board.getMoves().get(i).getMov_number())
                            break;
                    }
                    if(board.getMoves().get(i).getMy_x()==my_x && board.getMoves().get(i).getMy_y()==my_y )
                    {
                        board.getMoves().remove(i);
                    }
                    else {
                        board.getPlayer().setX(my_x);
                        board.getPlayer().setY(my_y);
                        board.getPlayer().setMovCounter(mov_number);
                        board.getMoves().clear();
                    }
                    
                    
                    board.getEnemy().setX(enemy_x);
                    board.getEnemy().setY(enemy_y);
                    Platform.runLater(() -> ServerCommunicator.getInstance().placeBomb(board.getEnemy()));
                    board.drawBoard();
                });

            }

            /*else if(in.substring(0,3).equals("mov"))
            {
                int x = Integer.parseInt((in.substring(3,6)));
                int y = Integer.parseInt((in.substring(6,9)));

                Platform.runLater(() -> {
                    board.getEnemy().setX(x);
                    board.getEnemy().setY(y);
                    board.drawBoard();
                });
            }
            else if(in.substring(0,3).equals("bmb"))
            {
                Platform.runLater(() -> ServerCommunicator.getInstance().placeBomb(board.getEnemy()));
            }
            else if(in.substring(0,3).equals("ymv"))
            {
                int x = Integer.parseInt((in.substring(3,6)));
                int y = Integer.parseInt((in.substring(6,9)));

                Platform.runLater(() -> {
                    board.getPlayer().setX(x);
                    board.getPlayer().setY(y);
                    board.drawBoard();
                });
            }*/
        }

        return null;
    }
}
