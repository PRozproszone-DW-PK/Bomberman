package sample.Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ServerCommunicator;

import java.util.ArrayList;
import java.util.Iterator;

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
        int power = 30;

        canvas.getGraphicsContext2D().setFill(Color.ORANGE);
        canvas.getGraphicsContext2D().fillRect(x,y,width,height);

        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                if(i==0)
                {
                    int length=1;
                    for(;length<power;length++)
                    {
                        if(enemy.contains(x-length,y+j))
                                enemy.die((Stage)(canvas).getScene().getWindow());

                        Iterator<Fragile> it = fragiles.iterator();

                        while(it.hasNext())
                        {
                            Fragile next = it.next();
                            if(next.contains(x-length,y+j))
                            {
                                fragiles.remove(next);
                                break;
                            }
                        }
                    }
                    canvas.getGraphicsContext2D().fillRect(x-length,y+j,length,1);
                }
                if(j==0)
                {
                    int length=1;
                    for(;length<power;length++)
                    {
                        if(enemy.contains(x+i,y-length))
                            enemy.die((Stage)(canvas).getScene().getWindow());

                        Iterator<Fragile> it = fragiles.iterator();

                        while(it.hasNext())
                        {
                            Fragile next = it.next();
                            if(next.contains(x+i,y-length))
                            {
                                fragiles.remove(next);
                                break;
                            }
                        }
                    }
                    canvas.getGraphicsContext2D().fillRect(x+i,y-length,1,length);
                }
                if(i==width-1)
                {
                    int length=1;
                    for(;length<power;length++)
                    {
                        if (enemy.contains(x + width + length, y + j))
                            enemy.die((Stage)(canvas).getScene().getWindow());


                        Iterator<Fragile> it = fragiles.iterator();

                        while (it.hasNext())
                        {
                            Fragile next = it.next();
                            if (next.contains(x + width + length, y + j)) {
                                fragiles.remove(next);
                                break;
                            }
                        }
                    }
                    canvas.getGraphicsContext2D().fillRect(x+width,y+j,length,1);
                }
                if(j==height-1)
                {
                    int length=1;
                    for(;length<power;length++)
                    {
                        if (enemy.contains(x + i, y + height + length))
                            enemy.die((Stage)(canvas).getScene().getWindow());

                        Iterator<Fragile> it = fragiles.iterator();

                        while (it.hasNext()) {
                            Fragile next = it.next();
                            if (next.contains(x + i, y + height + length)) {
                                fragiles.remove(next);
                                break;
                            }
                        }
                    }
                    canvas.getGraphicsContext2D().fillRect(x+i,y+height,1,length);
                }
            }
        }
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
}
