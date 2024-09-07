package com.promise.TankGame02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 坦克大战的绘图区域
 */
//为了监听键盘事件， 实现KeyListener
public class MyPanel extends JPanel implements KeyListener {
    //定义我的坦克
    Hero hero = null;
    //定义敌人坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;
    public MyPanel(){
        hero = new Hero(100, 100);//初始化自己的坦克
        //初始化敌人的坦克
        for (int i = 0; i < enemyTankSize; i ++) {
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }

        hero.setSpeed(3);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色

        //画出自己的坦克-封装方法
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);

        //画出敌人的坦克,遍历Vector
        for (int i = 0; i < enemyTanks.size(); i ++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
        }
    }
    //编写方法画出坦克

    /**
     *
     * @param x 坦克的左上角x坐标
     * @param y  坦克的左上角y坐标
     * @param g  画笔
     * @param direct  坦克方法
     * @param type  坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type){
        //设置坦克的颜色
        switch (type) {
            case 0:  //敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1:  //我们的坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克的方向，来绘制对应的坦克
        //direct 表示方向：上右下左 0, 1, 2, 3
        switch (direct){
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false); //画出坦克左边的轮子
                g.fill3DRect(x + 30,y , 10, 60, false); //画出坦克左边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克的盖子
                g.fillOval( x + 10,  y + 20, 20, 20);//画出圆盖
                g.drawLine( x + 20,  y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false); //画出坦克上边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false); //画出坦克左边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克的盖子
                g.fillOval( x + 20,  y + 10, 20, 20);//画出圆盖
                g.drawLine( x + 30,  y + 20, x + 60, y + 20); //画出炮筒
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false); //画出坦克上边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false); //画出坦克左边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克的盖子
                g.fillOval( x + 10,  y + 20, 20, 20);//画出圆盖
                g.drawLine( x + 20,  y + 30, x + 20, y + 60); //画出炮筒
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false); //画出坦克上边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false); //画出坦克左边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克的盖子
                g.fillOval( x + 20,  y + 10, 20, 20);//画出圆盖
                g.drawLine( x + 30,  y + 20, x, y + 20); //画出炮筒
                break;
            default:
                System.out.println("暂不处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //wdsa键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            hero.moveUp();
        } else if(e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
