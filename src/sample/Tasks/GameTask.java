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

        byte[] input = new byte[33];

        while(true) {
            try {
                ServerCommunicator.getInstance().getSocket().getInputStream().read(input, 0, 33);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String in = new String(input);
            System.out.println(in);

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
                int enemy_bomb_x = Integer.parseInt((in.substring(21,24)));
                int enemy_bomb_y = Integer.parseInt((in.substring(24,27)));
                int my_bomb_state = Integer.parseInt((in.substring(27,28)));
                int enemy_bomb_state = Integer.parseInt((in.substring(28,29)));
                int mov_number = Integer.parseInt((in.substring(29,33)));


                Platform.runLater(() -> {


//                    for(int i=0;i<board.getMoves().size();i++)
//                    {
//                        if(mov_number == board.getMoves().get(i).getMov_number())
//                        {
//                            if(board.getMoves().get(i).getMy_x()==my_x && board.getMoves().get(i).getMy_y()==my_y )
//                            {
//                                board.getMoves().remove(i);
//                            }
//                            else {
//                                board.getPlayer().setX(my_x);
//                                board.getPlayer().setY(my_y);
//                                board.getPlayer().setMovCounter(mov_number);
//                                board.getMoves().clear();
//                            }
//                            break;
//                        }
//                    }

                    board.getPlayer().setX(my_x);
                    board.getPlayer().setY(my_y);

                    board.getEnemy().setX(enemy_x);
                    board.getEnemy().setY(enemy_y);

                    //Platform.runLater(() -> ServerCommunicator.getInstance().placeBomb(board.getEnemy()));
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
