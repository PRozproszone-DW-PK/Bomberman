package Server;

import Game.Bomb;
import Game.Player;

public class BombThread implements Runnable {

    private GameThread game;
    private Player player;
    private int pNum;

    public BombThread(GameThread game, Player player, int pNum)
    {
        this.game = game;
        this.player = player;
        this.pNum = pNum;
    }

    @Override
    public void run() {
        try {
            game.bombUpdate(player,pNum);
            Thread.sleep(1000);
            game.bombUpdate(player,pNum);
            Thread.sleep(1000);
            game.bombUpdate(player,pNum);
            Thread.sleep(1000);
            game.bombUpdate(player,pNum);
            player.getBomb().setState(0);
            game.sendUpdate(pNum);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
