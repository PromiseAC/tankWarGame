package com.promise.tankGame03;

/**
 * 炸弹
 */
public class Bomb {
    int x, y;
    int life = 12;  //炸弹的生命周期
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.y = y;
        this.x = x;
    }

    //减少生命值
    public void lifeDown() {  //配合图片出现的爆炸效果
        if (life > 0) {
            life --;
        } else {
            isLive = false;
        }

    }
}
