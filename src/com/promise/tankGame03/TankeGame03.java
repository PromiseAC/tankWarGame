package com.promise.tankGame03;



import javax.swing.*;

public class TankeGame03 extends JFrame {
    //定义Mypanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankeGame03 t = new TankeGame03();
    }
    public TankeGame03() {
        mp = new MyPanel();
        //将mp放入 Thread,并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把面板（就是游戏的绘图区域）加进去
        this.setSize(1000, 750);
        this.addKeyListener(mp);//让JFrame监听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
