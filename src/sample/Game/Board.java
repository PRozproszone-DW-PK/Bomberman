package sample.Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

public class Board {
    //zmienic tak zeby sciany przechowywac osobno od playgrounda bo po co je rysowac za kazdym razem jak sie nie zmieniaja
    private int width;
    private int height;
    private BoardElement[][] playground;
    private ArrayList<Fragile> fragiles;
    private Canvas canvas;
    private Player player;
    private Player enemy;



    private ArrayList<GameStatus> moves;

    public ArrayList<GameStatus> getMoves() {
        return moves;
    }

    public void addMov(GameStatus gs) {
        moves.add(gs);
    }

    public Board(Canvas canvas) {
        this.canvas = canvas;
        width=15;
        height=15;
        playground = new BoardElement[width][height];
        moves = new ArrayList<>();

        fragiles = new ArrayList<>();
        char map[][] =
               {{'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'},
                {'w',' ','f',' ',' ','f',' ',' ','f',' ',' ','f',' ',' ','w'},
                {'w',' ','w',' ',' ','w',' ',' ','w',' ',' ','w',' ',' ','w'},
                {'w',' ',' ',' ',' ','f',' ',' ','f',' ',' ','f','f','f','w'},
                {'w',' ',' ',' ',' ','f',' ',' ','f',' ',' ',' ',' ',' ','w'},
                {'w',' ','w','f','w','f','w','f','w','f','w','f','w',' ','w'},
                {'w',' ',' ','f',' ',' ',' ',' ','f',' ',' ',' ',' ',' ','w'},
                {'w',' ',' ','f',' ',' ',' ',' ','f',' ',' ',' ',' ',' ','w'},
                {'w',' ','w','f','f','w','f','f','w',' ',' ','w','f','f','w'},
                {'w',' ',' ',' ',' ','f',' ',' ','f',' ',' ',' ',' ',' ','w'},
                {'w',' ',' ',' ',' ','f',' ',' ','f',' ',' ',' ',' ',' ','w'},
                {'w',' ','w',' ','w','f','w',' ','w',' ','w','f','w','f','w'},
                {'w',' ','f',' ','f',' ',' ',' ',' ',' ',' ',' ','f',' ','w'},
                {'w',' ','f',' ','f',' ',' ',' ',' ',' ',' ',' ','f',' ','w'},
                {'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'}};

        final int spacing = 25;

        /*for(int i = 0; i<width; i++)
        {
            for(int j=0;j<height;j++)
            {
                if(i==0 || i== width-1 || j==0 || j== height-1 || (i==3 && j!=2) || (i>6 && j==4))
                    playground[i][j] = new Wall(i * spacing, j * spacing,25,25);
                else if(i>3&& i<6 && j==2) {
                    fragiles.add(new Fragile(i * spacing, j * spacing, 25, 25));
                    playground[i][j] = new BoardElement(i * spacing, j * spacing,25,25);
                }
                else
                    playground[i][j] = new BoardElement(i * spacing, j * spacing,25,25);
            }
        }*/
        for(int i = 0; i<width; i++)
        {
            for(int j=0;j<height;j++)
            {
                if(map[i][j]=='w')
                    playground[i][j] = new Wall(i * spacing, j * spacing,25,25);
                else if(map[i][j]=='f') {
                    fragiles.add(new Fragile(i * spacing, j * spacing, 25, 25));
                    playground[i][j] = new BoardElement(i * spacing, j * spacing,25,25);
                }
                else
                    playground[i][j] = new BoardElement(i * spacing, j * spacing,25,25);
            }
        }

        player = new Player(5 * spacing, 5 * spacing, 25, 25,5, Color.BLUE);
        enemy = new Player(7 * spacing, 7 * spacing, 25, 25,5, Color.RED);
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

        if(enemy.isAlive())
        {
            enemy.draw(canvas);
        }

        if(player.isAlive())
            player.draw(canvas);

        if(player.getBomb().isPlaced())
            player.getBomb().draw(canvas);

        if(enemy.getBomb().isPlaced())
            enemy.getBomb().draw(canvas);

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

                        Iterator<Fragile> it = fragiles.iterator();

                        while(it.hasNext())
                        {
                            Fragile next = it.next();
                            if(next.contains(x1+k,y))
                            {
                                return true;
                            }
                        }
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

                        Iterator<Fragile> it = fragiles.iterator();

                        while(it.hasNext())
                        {
                            Fragile next = it.next();
                            if(next.contains(x,y1+k))
                            {
                                return true;
                            }
                        }
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

    public Player getEnemy()
    {
        return enemy;
    }

    public BoardElement[][] getPlayground() {
        return playground;
    }

    public ArrayList<Fragile> getFragiles() {
        return fragiles;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
