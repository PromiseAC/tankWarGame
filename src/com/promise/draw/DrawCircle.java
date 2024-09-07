package com.promise.draw;

import javax.swing.*;
import java.awt.*;

/**
 * @author PromiseAC
 * @version 1.0
 * 演示如何在面板上画出圆形
 */
public class DrawCircle extends JFrame{ //JFrame对应窗口，可以理解成是一个画框
    //定义一个面板
    private MyPanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();
    }
    public DrawCircle() {
        //初始化面板
        mp = new MyPanel();
        //把面板放入到窗口
        this.add(mp);
        //设置窗口的大小
        this.setSize(400, 300);
        //当点击窗口的小x，就关闭程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//可以显示
    }
}

//1.先定义一个MyPanel，继承JPanel类，画圆形，就在面板上画
class MyPanel extends JPanel {
    //说明
    //1. MyPanel 对象就是一个画板
    //2. Graphics g  把g理解成一个画笔
    //3. Graphics 提供很多绘图方法
    @Override
    public void paint(Graphics g) {//绘图方法
        super.paint(g);
        //g.drawOval(10, 10, 100, 100);

        //演示绘制不同的图形
        // 画直线 drawLine(int x1,int y1,int x2,int y2)
        //g.drawLine(120, 125, 115, 75);

        //画矩形边框 drawRect(int x, int y, int width, int height)
        //g.drawRect(10, 10, 100, 100);

        //画椭圆边框 drawOval(int x, int y, int width, int height)

        //填充矩形 fillRect(int x, int y, int width, int height)
        //设置画笔的颜色
        //g.setColor(Color.blue);
        //g.fillRect(100, 100, 10, 50);
        //g.fillRect(130, 100, 10, 50);
        //g.setColor(Color.red);
        //g.fillRect(118, 90, 3, 35);
        //填充椭圆 fillOval(int x, int y, int width, int height)
        //g.setColor(Color.red);
        //g.fillOval(109, 115, 20, 20);

        //画图片 drawImage(Image img, int x, int y, ..)
        //1. 获取图片资源, /bg.png 表示在该项目的根目录去获取 bg.png 图片资源
//        Image image = Toolkit.getDefaultToolkit().getImage("F:/Code/JAVAcode/JAVAIDE/tankWarGame/out/production/tankWar/b-7.jpg");
//        g.drawImage(image, 10, 10, 1125, 631, this);

        //画字符串 drawString(String str, int x, int y)//写字
        //给画笔设置颜色和字体
        //g.setColor(Color.red);
        //g.setFont(new Font("隶书", Font.BOLD, 50));
        //这里设置的 100， 100， 是 "北京你好"左下角
        //g.drawString("北京你好", 0, 100);//坐标对应左下角
        //设置画笔的字体 setFont(Font font)
        //设置画笔的颜色 setColor(Color c)
    }

}