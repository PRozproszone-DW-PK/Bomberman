package sample.Tasks;

import javafx.application.Platform;
import javafx.concurrent.Task;
import sample.Game.Bomb;
import sample.ServerCommunicator;

public class BombTask extends Task<Void> {

    private Bomb bomb;

    public BombTask(Bomb bomb)
    {
        this.bomb = bomb;
    }

    @Override
    protected Void call() throws Exception {

        ServerCommunicator sc = ServerCommunicator.getInstance();
        bomb.setPlaced(false);
        for (int i = 0; i < 50; i++) {
            Thread.sleep(10);
            Platform.runLater(() -> bomb.explode(sc.getBoard().getCanvas(),sc.getBoard().getPlayground(),sc.getBoard().getPlayer(),sc.getBoard().getFragiles()));
        }

        sc.getBoard().drawBoard();
        System.out.println("Gsfvfdd");

        return null;
    }
}
