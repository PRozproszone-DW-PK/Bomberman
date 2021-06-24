package sample.Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

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
    }

    public void explode(Canvas canvas, BoardElement[][] playground, Player player, ArrayList<Fragile> fragiles)
    {
        int power = 2;

        canvas.getGraphicsContext2D().setFill(Color.ORANGE);
        canvas.getGraphicsContext2D().fillRect(x,y,width,height);

        final int tileX = this.x/25;
        final int tileY = this.y/25;

        canvas.getGraphicsContext2D().fillRect(tileX*25,tileY*25, 25,25);
        if(abs(tileX*25 - player.getX()) <=24 && abs(tileY*25 - player.getY()) <=24)
            player.die();

        for(int i = 1;i<=power;i++)
        {
            if(tileX+i<15 && !(playground[tileX+i][tileY] instanceof Wall))
            {
                canvas.getGraphicsContext2D().fillRect(tileX*25+i*25,tileY*25, 25,25);
                if(abs(tileX*25+i*25 - player.getX()) <=24 && abs(tileY*25 - player.getY()) <=24)
                    player.die();

                Iterator<Fragile> it = fragiles.iterator();

                while(it.hasNext())
                {
                    Fragile next = it.next();
                    if(abs(tileX*25+i*25 - next.getX()) <=24 && abs(tileY*25 - next.getY()) <=24)
                    {
                        fragiles.remove(next);
                        break;
                    }
                }
            }
            else break;
        }

        for(int i = 1;i<=power;i++)
        {
            if(tileX-i>=0 && !(playground[tileX-i][tileY] instanceof Wall))
            {
                canvas.getGraphicsContext2D().fillRect(tileX*25-i*25,tileY*25, 25,25);
                if(abs(tileX*25-i*25 - player.getX()) <=24 && abs(tileY*25 - player.getY()) <=24)
                    player.die();

                Iterator<Fragile> it = fragiles.iterator();

                while(it.hasNext())
                {
                    Fragile next = it.next();
                    if(abs(tileX*25-i*25 - next.getX()) <=24 && abs(tileY*25 - next.getY()) <=24)
                    {
                        fragiles.remove(next);
                        break;
                    }
                }
            }
            else break;
        }

        for(int i = 1;i<=power;i++)
        {
            if(tileY+i<15 &&!(playground[tileX][tileY+i] instanceof Wall))
            {
                canvas.getGraphicsContext2D().fillRect(tileX*25,tileY*25+i*25, 25,25);
                if(abs(tileX*25 - player.getX()) <=24 && abs(tileY*25+i*25 - player.getY()) <=24)
                    player.die();

                Iterator<Fragile> it = fragiles.iterator();

                while(it.hasNext())
                {
                    Fragile next = it.next();
                    if(abs(tileX*25 - next.getX()) <=24 && abs(tileY*25+i*25 - next.getY()) <=24)
                    {
                        fragiles.remove(next);
                        break;
                    }
                }
            }
            else break;
        }

        for(int i = 1;i<=power;i++)
        {
            if(tileY-i>=0 && !(playground[tileX][tileY-i] instanceof Wall))
            {
                canvas.getGraphicsContext2D().fillRect(tileX*25,tileY*25-i*25, 25,25);
                if(abs(tileX*25 - player.getX()) <=24 && abs(tileY*25-i*25 - player.getY()) <=24)
                    player.die();

                Iterator<Fragile> it = fragiles.iterator();

                while(it.hasNext())
                {
                    Fragile next = it.next();
                    if(abs(tileX*25 - next.getX()) <=24 && abs(tileY*25-i*25 - next.getY()) <=24)
                    {
                        fragiles.remove(next);
                        break;
                    }
                }
            }
            else break;
        }
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
}
