package Game;

public class Player {

    private int x;
    private int y;
    private int msgNum;
    private Bomb bomb;

    public Player(int x, int y, int msgNum, Bomb bomb)
    {
        this.x = x;
        this.y = y;
        this.msgNum = msgNum;
        this.bomb = bomb;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }


    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }
}
