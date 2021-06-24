package sample.Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Wall extends BoardElement {
    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.BROWN;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.getGraphicsContext2D().setFill(color);
        canvas.getGraphicsContext2D().fillRect(x,y,width,height);
    }
}
