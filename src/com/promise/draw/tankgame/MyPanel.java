package com.promise.draw.tankgame;

import javax.swing.*;
import java.awt.*;

/**
 * 坦克大战的绘图区域
 */
public class MyPanel extends JPanel{
    //定义我的坦克
    Hero hero = null;
    public MyPanel(){
        hero = new Hero(100, 100);//初始化自己的坦克
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色

        //画出坦克-封装方法
        drawTank(hero.getX(), hero.getY(), g, 0, 1);
        drawTank(hero.getX() + 100, hero.getY(), g, 0, 0);
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
            case 0:  //我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1:  //敌人的坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克的方向，来绘制坦克
        switch (direct){
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false); //画出坦克左边的轮子
                g.fill3DRect(x + 30,y , 10, 60, false); //画出坦克左边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克的盖子
                g.fillOval( x + 10,  y + 20, 20, 20);//画出圆盖
                g.drawLine( x + 20,  y + 30, x + 20, y);
                break;
            default:
                System.out.println("暂不处理");
        }
    }
}
