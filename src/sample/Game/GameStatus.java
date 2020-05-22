package sample.Game;

public class GameStatus {
    private int my_x;
    private int my_y;
    private int enemy_x;
    private int enemy_y;
    private int my_bomb_x;
    private int my_bomb_y;
    private int enemy_bomb_x;
    private int enemy_bomb_y;
    private int my_bomb_state;
    private int enemy_bomb_state;
    private int mov_number;

    public GameStatus(int my_x, int my_y, /*int enemy_x, int enemy_y, int my_bomb_x, int my_bomb_y, int enemy_bomb_x, int enemy_bomb_y, int my_bomb_state, int enemy_bomb_state,*/ int mov_number) {
        this.my_x = my_x;
        this.my_y = my_y;
        /*this.enemy_x = enemy_x;
        this.enemy_y = enemy_y;
        this.my_bomb_x = my_bomb_x;
        this.my_bomb_y = my_bomb_y;
        this.enemy_bomb_x = enemy_bomb_x;
        this.enemy_bomb_y = enemy_bomb_y;
        this.my_bomb_state = my_bomb_state;
        this.enemy_bomb_state = enemy_bomb_state;*/
        this.mov_number = mov_number;
    }

    public int getMy_x() {
        return my_x;
    }

    public int getMy_y() {
        return my_y;
    }

    public int getEnemy_x() {
        return enemy_x;
    }

    public int getEnemy_y() {
        return enemy_y;
    }

    public int getMy_bomb_x() {
        return my_bomb_x;
    }

    public int getMy_bomb_y() {
        return my_bomb_y;
    }

    public int getEnemy_bomb_x() {
        return enemy_bomb_x;
    }

    public int getEnemy_bomb_y() {
        return enemy_bomb_y;
    }

    public int getMy_bomb_state() {
        return my_bomb_state;
    }

    public int getEnemy_bomb_state() {
        return enemy_bomb_state;
    }

    public int getMov_number() {
        return mov_number;
    }




}
