package sample.Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Player extends BoardElement {

    private int moveSpeed;
    private boolean alive;
    private Bomb bomb;

    public Player(int x, int y, int width, int height, int moveSpeed, Color color) {
        super(x, y, width, height);
        this.moveSpeed = moveSpeed;
        this.alive = true;
        this.color = color;

        this.bomb = new Bomb(0,0,10,10);
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
    }

    public void placeBomb()
    {
        if(!bomb.isPlaced())
        {
            bomb.place(x+5,y+5);

        }
    }

    public Bomb getBomb()
    {
        return bomb;
    }
}
