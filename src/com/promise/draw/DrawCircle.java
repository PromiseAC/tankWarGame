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
        g.drawOval(10, 10, 100, 100);
    }
}