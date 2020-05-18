package sample.Tasks;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
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

        bomb.changeColor(Color.BLACK);

        Platform.runLater(() -> bomb.draw(ServerCommunicator.getInstance().getBoard().getCanvas()));

        Thread.sleep(1000);
        bomb.changeColor(Color.ORANGE);
        Thread.sleep(1000);
        bomb.changeColor(Color.RED);
        Thread.sleep(1000);

        ServerCommunicator sc = ServerCommunicator.getInstance();

        for (int i = 0; i < 100; i++) {
            Thread.sleep(5);
            Platform.runLater(() -> bomb.explode(sc.getBoard().getCanvas(),sc.getBoard().getPlayground(),sc.getBoard().getEnemy(),sc.getBoard().getFragiles()));
        }
        bomb.setPlaced(false);
        Platform.runLater(() -> sc.getBoard().drawBoard());

        return null;
    }
}
