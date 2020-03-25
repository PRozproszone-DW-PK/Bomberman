package sample.Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Board {
    //zmienic tak zeby sciany przechowywac osobno od playgrounda bo po co je rysowac za kazdym razem jak sie nie zmieniaja
    private int width;
    private int height;
    private BoardElement[][] playground;
    private ArrayList<Fragile> fragiles;
    private Canvas canvas;
    private Player player;
    private Player enemy;

    public Board(Canvas canvas) {
        this.canvas = canvas;
        width=15;
        height=15;
        playground = new BoardElement[width][height];
        fragiles = new ArrayList<>();

        int spacing = 20;

        for(int i = 0; i<width; i++)
        {
            for(int j=0;j<height;j++)
            {
                if(i==0 || i== width-1 || j==0 || j== height-1 || (i==3 && j!=2) || (i>6 && j==4))
                    playground[i][j] = new Wall(i * spacing + 20, j * spacing +20,20,20);
                else if(i>3&& i<6 && j==2) {
                    fragiles.add(new Fragile(i * spacing + 20, j * spacing + 20, 20, 20));
                    playground[i][j] = new BoardElement(i * spacing + 20, j * spacing +20,20,20);
                }
                else
                    playground[i][j] = new BoardElement(i * spacing + 20, j * spacing +20,20,20);
            }
        }

        player = new Player(5 * spacing + 20, 5 * spacing + 20, 20, 20,5, Color.BLUE);
        enemy = new Player(7 * spacing + 20, 7 * spacing + 20, 20, 20,5, Color.RED);
    }

    public void drawBoard()
    {
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                playground[i][j].draw(canvas);
            }
        }

        if(player.getBomb().isPlaced())
            player.getBomb().explode(canvas, playground, enemy,fragiles);

        if(enemy.isAlive())
        {
            enemy.draw(canvas);
        }

        if(player.isAlive())
            player.draw(canvas);

        if(player.getBomb().isPlaced())
            player.getBomb().draw(canvas);

        for(Fragile fragile : fragiles)
        {
            fragile.draw(canvas);
        }
    }

    public boolean anyVerticalObstacles(int x1, int x2, int y)
    {
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                if(playground[i][j] instanceof Wall)
                {
                    for(int k=0;k<=Math.abs(x2-x1);k++)
                    {
                        if(playground[i][j].contains(x1+k,y))
                            return true;
                        if(enemy.isAlive() && enemy.contains(x1+k,y))
                            return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean anyHorizontalObstacles(int y1, int y2, int x)
    {
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                if(playground[i][j] instanceof Wall)
                {
                    for(int k=0;k<=Math.abs(y2-y1);k++)
                    {
                        if(playground[i][j].contains(x,y1+k))
                            return true;
                        if(enemy.isAlive() && enemy.contains(x,y1+k))
                            return true;
                    }
                }
            }
        }

        return false;
    }

    public Player getPlayer()
    {
        return player;
    }
}
