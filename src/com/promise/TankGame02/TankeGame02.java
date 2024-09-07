package com.promise.TankGame02;

import javax.swing.*;
import java.awt.event.KeyListener;

public class TankeGame02 extends JFrame {
    //定义Mypanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankeGame02 t = new TankeGame02();
    }
    public TankeGame02() {
        mp = new MyPanel();
        this.add(mp);//把面板（就是游戏的绘图区域）加进去
        this.setSize(1000, 750);
        this.addKeyListener(mp);//让JFrame监听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
