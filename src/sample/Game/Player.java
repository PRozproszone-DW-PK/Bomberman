package sample.Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Controllers.GameController;
import sample.ServerCommunicator;

public class Player extends BoardElement {

    private int moveSpeed;
    private boolean alive;
    private Bomb bomb;

    public void setMovCounter(int movCounter) {
        this.movCounter = movCounter;
    }

    private int movCounter;




    public Player(int x, int y, int width, int height, int moveSpeed, Color color) {
        super(x, y, width, height);
        this.moveSpeed = moveSpeed;
        this.alive = true;
        this.color = color;
        this.movCounter=0;

        this.bomb = new Bomb(x,y,25,25);
    }
    public int getMovCounter() {
        return movCounter;
    }
    public void incrementMovCounter() {
        this.movCounter++;
    }

    public void moveLeft()
    {
        this.x = x-moveSpeed;
    }

    public void moveRight()
    {
        this.x = x+moveSpeed;
    }

    public void moveUp()
    {
        this.y = y-moveSpeed;
    }

    public void moveDown()
    {
        this.y = y+moveSpeed;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.getGraphicsContext2D().setFill(color);
        canvas.getGraphicsContext2D().fillRect(x,y,width,height);
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void die()
    {
        this.alive = false;
        ServerCommunicator.getInstance().endMsg();
    }

    public void placeBomb()
    {
        if(!bomb.isPlaced())
        {
            incrementMovCounter();
            bomb.place(((x+12)/25)*25,((y+12)/25)*25);
            //ServerCommunicator.getInstance().placeBomb(this);
            //ServerCommunicator.getInstance().bombMsg();
        }
    }

    public Bomb getBomb()
    {
        return bomb;
    }
}
