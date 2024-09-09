package com.promise.tankGame03;

import java.util.Vector;

public class Hero extends Tank {
    //定义一个Shot对象，表示一个射击（线程）
    Shot shot = null;
    public Hero(int x, int y) {
        super(x, y);
    }
    /*public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }*/
    //可以发射多颗子弹
    Vector<Shot> shots = new Vector<>();

    //射击
    public void shotEnemyTank() {
        //创建Shot对象，根据当前Hero对象的位置和方向创建Shot
        switch (getDirect()) {//得到Hero对象方向
            case 0://向上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        //把新建的shot放入到shots
        shots.add(shot);
        //启动我们的Shot线程
        new Thread(shot).start();
    }
}
