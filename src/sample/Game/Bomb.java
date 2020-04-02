package sample.Game;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ServerCommunicator;

import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.abs;

public class Bomb extends BoardElement {

    private boolean placed;

    public Bomb(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.placed = false;
        this.color = Color.BLACK;
    }

    public boolean isPlaced()
    {
        return placed;
    }

    public void changeColor(Color color)
    {
        this.color = color;
    }

    public void place(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.placed = true;
       // ServerCommunicator.getInstance().placeBomb(this);
    }

    public void explode(Canvas canvas, BoardElement[][] playground, Player enemy, ArrayList<Fragile> fragiles)
    {
        int power = 2;

        canvas.getGraphicsContext2D().setFill(Color.ORANGE);
        canvas.getGraphicsContext2D().fillRect(x,y,width,height);

        final int tileX = this.x/25;
        final int tileY = this.y/25;

        //if(playground[tileX][tileY])



        for(int i = 1;i<=power;i++)
        {
            if(tileX+i<15 && !(playground[tileX+i][tileY] instanceof Wall))
            {
                if(abs(tileX*25+i*25 - enemy.getX()) <=24 && abs(tileY*25 - enemy.getY()) <=24)
                    enemy.die();
                canvas.getGraphicsContext2D().fillRect(tileX*25+i*25,tileY*25, 25,25);
            }
        }

        for(int i = 1;i<=power;i++)
        {
            if(tileX-i>=0 && !(playground[tileX-i][tileY] instanceof Wall))
            {
                if(abs(tileX*25-i*25 - enemy.getX()) <=24 && abs(tileY*25 - enemy.getY()) <=24)
                    enemy.die();
                canvas.getGraphicsContext2D().fillRect(tileX*25-i*25,tileY*25, 25,25);
            }
        }

        for(int i = 1;i<=power;i++)
        {
            if(tileY+i<15 &&!(playground[tileX][tileY+i] instanceof Wall))
            {
                if(abs(tileX*25 - enemy.getX()) <=24 && abs(tileY*25+i*25 - enemy.getY()) <=24)
                    enemy.die();
                canvas.getGraphicsContext2D().fillRect(tileX*25,tileY*25+i*25, 25,25);
            }
        }

        for(int i = 1;i<=power;i++)
        {
            if(tileY-i>=0 && !(playground[tileX][tileY-i] instanceof Wall))
            {
                if(abs(tileX*25 - enemy.getX()) <=24 && abs(tileY*25-i*25 - enemy.getY()) <=24)
                    enemy.die();
                canvas.getGraphicsContext2D().fillRect(tileX*25,tileY*25-i*25, 25,25);
            }
        }
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
}
