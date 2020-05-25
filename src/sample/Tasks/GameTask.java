package sample.Tasks;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Controllers.GameController;
import sample.Game.Board;
import sample.ServerCommunicator;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameTask extends Task<Void> {

    private Board board;
    private ExecutorService exec;

    public GameTask(Board board)
    {
        this.board = board;
        exec = Executors.newSingleThreadExecutor();
    }

    @Override
    protected Void call() {

        byte[] input = new byte[22];

        while(true) {
            try {
                ServerCommunicator.getInstance().getSocket().getInputStream().read(input, 0, 22);
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
                int my_bomb_state = Integer.parseInt((in.substring(15,16)));
                int enemy_bomb_state = Integer.parseInt((in.substring(16,17)));
                int mov_number = Integer.parseInt((in.substring(17,21)));
                int back = Integer.parseInt((in.substring(21,22)));
                Platform.runLater(() -> {
                    if(back==1) {
                        for (int i = 0; i < board.getMoves().size(); i++) {
                            if (mov_number == board.getMoves().get(i).getMov_number()) {
                                if (board.getMoves().get(i).getMy_x() == my_x && board.getMoves().get(i).getMy_y() == my_y)
                                {
                                    board.getMoves().remove(i);
                                }
                                else
                                {
                                    board.getPlayer().setX(my_x);
                                    board.getPlayer().setY(my_y);
                                    board.getPlayer().setMovCounter(mov_number);
                                    board.getMoves().clear();
                                }
                                break;
                            }
                        }

                        if(my_bomb_state==1)
                        {
                            board.getPlayer().getBomb().changeColor(Color.BLACK);
                        }
                        else if(my_bomb_state==2)
                        {
                            board.getPlayer().getBomb().changeColor(Color.ORANGE);
                        }
                        else if(my_bomb_state==3)
                        {
                            board.getPlayer().getBomb().changeColor(Color.RED);
                        }
                        else if(my_bomb_state==4)
                        {
                            exec.submit(new BombTask(board.getPlayer().getBomb()));
                        }
                    }
                    else {

                        board.getEnemy().setX(enemy_x);
                        board.getEnemy().setY(enemy_y);

                        if(enemy_bomb_state==1)
                        {
                            board.getEnemy().placeBomb();
                            board.getEnemy().getBomb().changeColor(Color.BLACK);
                        }
                        else if(enemy_bomb_state==2)
                        {
                            board.getEnemy().getBomb().changeColor(Color.ORANGE);
                        }
                        else if(enemy_bomb_state==3)
                        {
                            board.getEnemy().getBomb().changeColor(Color.RED);
                        }
                        else if(enemy_bomb_state==4)
                        {
                            exec.submit(new BombTask(board.getEnemy().getBomb()));
                        }
                    }

                    board.drawBoard();
                });

            }

        }

        return null;
    }
}