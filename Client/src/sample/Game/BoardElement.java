package sample.Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BoardElement {

    protected Color color;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public BoardElement(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = Color.WHITE;
    }

    public void draw(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.strokeRect(x,y,width,height);
        gc.setFill(color);
        gc.fillRect(x,y,width,height);
    }

    public synchronized Boolean contains(int x, int y)
    {
        return x >= this.x && x <= this.x + this.width-1 && y >= this.y && y <= this.y + this.height-1;
    }

    public int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public synchronized void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
