package com.promise.tankGame04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 坦克大战的绘图区域
 */
//为了让Panel不停的重绘子弹，需要将MyPanel实现Runnable，当做一个线程使用
//为了监听键盘事件， 实现KeyListener
public class MyPanel extends JPanel implements KeyListener, Runnable{
    //定义我的坦克
    Hero hero = null;
    //定义敌人坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个存放Node对象的Vector，用于恢复敌人坦克的坐标方向
    Vector<Node> nodes = new Vector<>();
    //定义一个Vector，用于存放炸弹
    //当子弹击中坦克时，加入一个Bomb对象到Bombs
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 5;

    //定义三种图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key){
        nodes = Recorder.getNodesAndEnemyTankRec();
        Recorder.setEnemyTanks(enemyTanks);
        hero = new Hero(100, 300);//初始化自己的坦克
        switch (key) {
            case "1":
                //初始化敌人的坦克
                for (int i = 0; i < enemyTankSize; i ++) {
                    EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
                    //将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks((enemyTanks));
                    //设置方向
                    enemyTank.setDirect(2);
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给该enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //加入enemyTank坦克的Vector成员
                    //加入到enemyTank的Vector成员
                    enemyTank.shots.add(shot);
                    //启动shot对象
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                //初始化敌人的坦克
                for (int i = 0; i < nodes.size(); i ++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks((enemyTanks));
                    //设置方向
                    enemyTank.setDirect(node.getDirect());
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给该enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //加入enemyTank坦克的Vector成员
                    //加入到enemyTank的Vector成员
                    enemyTank.shots.add(shot);
                    //启动shot对象
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("输入有误");
                break;
        }

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
        hero.setSpeed(3);
    }

    //编写方法，显示我方击毁地方坦克的信息
    public void showInfo(Graphics g) {
        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累计击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0);//画出一个敌方坦克
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        showInfo(g);

        //画出自己的坦克-封装方法
        if (hero != null && hero.isLive)
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);

//        //画出hero射击的子弹
//        if (hero.shot != null && hero.shot.isLive == true) {
//            g.draw3DRect(hero.shot.x, hero.shot.y, 1, 1, false);
//        }
        //将hero的子弹集合shots，遍历取出绘制
        for (int i = 0; i < hero.shots.size(); i ++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive == true) {
                g.draw3DRect(shot.x, shot.y, 1, 1, false);
            } else {
                hero.shots.remove(shot);
            }
        }

        //如果bombs集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);

            //根据当前这个bomb对象的life值去画出对应的图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }

            //让这个生命值减少
            bomb.lifeDown();
            //如果bomb life为0，就从bombs的集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }

        //画出敌人的坦克,遍历Vector
        for (int i = 0; i < enemyTanks.size(); i ++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断当前坦克是否还存活
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                //画出所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    //绘制
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        //从Vector里面移除
                        enemyTank.shots.remove(shot);
                    }
                }
            }
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
    //如果我们的坦克可以发射多个子弹
    //在判断我方子弹是否击中敌人坦克时，就需要把我们的子弹集合中
    //所有的子弹都取出和敌人的所有坦克进行判断
    public void hitEnemyTank() {
        //遍历我们的子弹
        for (int j = 0; j < hero.shots.size(); j ++) {
            Shot shot = hero.shots.get(j);
            //判断是否击中了敌人坦克
            if (shot != null && shot.isLive) {//当前我的子弹还存活
                //遍历敌人所有的坦克
                for (int i = 0; i < enemyTanks.size(); i ++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }

    }

    //编写方法判断敌人坦克是否击中我的坦克
    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i ++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历EnemyTank对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j ++) {
                Shot shot = enemyTank.shots.get(j);
                //判断shot是否击中我的坦克
                if (hero.isLive && shot.isLive) {
                    hitTank(shot, hero);
                }
            }
        }
    }

    //判断我方的坦克是否击中敌人的坦克
    public void hitTank(Shot s, Tank enemyTank) {
        switch (enemyTank.getDirect()) {
            case 0:
            case 2:
                if (s.x > enemyTank.getX() &&s.x < enemyTank.getX() + 40
                 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //当我的子弹击中敌人坦克后，将enemyTank 从vector拿掉
                    enemyTanks.remove(enemyTank);
                    //当我方击毁一个敌人坦克时，对数据allenemytankNum++
                    //因为enemyTank可以是Hero也可以是enemyTank
                    if (enemyTank instanceof  EnemyTank) {
                        Recorder.addAllEnemyTank();
                    }
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60
                && s.y > enemyTank.getY() &&  s.y < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //当我的子弹击中敌人坦克后，将enemyTank 从vector拿掉
                    enemyTanks.remove(enemyTank);
                    if (enemyTank instanceof  EnemyTank) {
                        Recorder.addAllEnemyTank();
                    }
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
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
            if (hero.getY() > 0)
                hero.moveUp();
        } else if(e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY() + 60 < 750)
                hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if (hero.getX() > 0)
                hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000)
                hero.moveRight();
        }
        //如果用户按下的是J，发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
//            //判断hero的子弹是否消亡
//            if (hero.shot == null || !hero.shot.isLive) {
//                hero.shotEnemyTank();
//            }
            hero.shotEnemyTank();

        }

        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100毫秒，重绘区域，刷新绘图区域，子弹就移动
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }
}
