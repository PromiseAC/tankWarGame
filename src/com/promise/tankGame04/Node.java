package com.promise.tankGame04;

public class Node {
    private int x;
    private int y;
    private int direct;

    public Node(int direct, int x, int y) {
        this.direct = direct;
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
}
