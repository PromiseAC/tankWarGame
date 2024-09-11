package com.promise.tankGame04;


import java.io.*;
import java.util.Vector;

/**
 * 该类用于记录相关信息的.和文件交互
 */
public class Recorder {
    //定义变量，记录我方击毁敌人坦克数
    private static  int allEnemyTankNum = 0;
    //定义IO对象，准备写数据到文件中

    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\myrecord.txt";
    private static Vector<EnemyTank> enemyTanks = null;
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //定义一个Node 的Vector，用于保存敌人的信息node
    private static Vector<Node> nodes = new Vector<>();

    //增加一个方法，用于读取recordFile，恢复相关信息
    public static Vector<Node> getNodesAndEnemyTankRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //循环读取文件，生成nodes集合
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]),
                        Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }

    //增加一个方法，当游戏退出时，我们将allEnemyTankNum保存到recordFile
    public static  void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方击毁一个敌人坦克，allEnemyTankNum++
    public static void addAllEnemyTank() {
        allEnemyTankNum ++;
    }
}
