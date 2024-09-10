package com.promise.tankGame04;

/**
 * 射击子弹
 */
public class Shot implements Runnable {
    int x;//子弹x坐标
    int y;//子弹y左边
    int direct = 0;//子弹方向
    int speed = 2;//子弹的速度
    boolean isLive = true;//子弹是否存活

    public Shot(int x, int y, int direct) {
        this.direct = direct;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {//射击

        while (true) {
            //休眠50ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct) {
                case 0://上
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
                default:
            }
            //当子弹碰到敌人坦克时，也应该结束线程
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                isLive = false;
                break;
            }
        }

    }
}
