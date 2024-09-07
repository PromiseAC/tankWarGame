package com.promise.draw.tankgame;

import javax.swing.*;

public class TankeGame01 extends JFrame {
    //定义Mypanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankeGame01 t = new TankeGame01();
    }
    public TankeGame01() {
        mp = new MyPanel();
        this.add(mp);//把面板（就是游戏的绘图区域）加进去
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
