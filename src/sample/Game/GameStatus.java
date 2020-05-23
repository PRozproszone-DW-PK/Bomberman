package sample.Game;

public class GameStatus {
    private int my_x;
    private int my_y;

    private int mov_number;

    public GameStatus(int my_x, int my_y, int mov_number) {
        this.my_x = my_x;
        this.my_y = my_y;

        this.mov_number = mov_number;
    }

    public int getMy_x() {
        return my_x;
    }

    public int getMy_y() {
        return my_y;
    }



    public int getMov_number() {
        return mov_number;
    }




}
