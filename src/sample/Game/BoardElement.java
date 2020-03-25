package sample.Game;

import javafx.scene.canvas.Canvas;
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
        canvas.getGraphicsContext2D().setFill(color);
        canvas.getGraphicsContext2D().fillRect(x,y,width,height);
    }

    public Boolean contains(int x, int y)
    {
        return x >= this.x && x <= this.x + this.width-1 && y >= this.y && y <= this.y + this.height-1;
    }

    public int getX() {
        return x;
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
