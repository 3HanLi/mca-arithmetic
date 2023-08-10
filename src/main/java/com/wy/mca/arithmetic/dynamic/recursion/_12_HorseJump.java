package com.wy.mca.arithmetic.dynamic.recursion;

import org.junit.Test;

/**
 * @Description 马踏棋盘
 * 1、问题描述：假如现在有一个棋盘，9行10列，现在马从(0,0)出发，需要经过K步到达(x,y)，请问有多少种走法
 * 2、案例说明
 * 3、解题思路
 * 4、暴力递归
 * 5、动态规划
 *
 * @Author wangyong01
 * @Date 2023/4/8 1:01 PM
 * @Version 1.0
 */
public class _12_HorseJump {

    /**
     * 棋盘的行
     */
    public static final int rows = 9;

    /**
     * 棋盘的列
     */
    public static final int cols = 10;

    @Test
    public void jump(){
        int waysVR = getWaysVR(0, 0, 10, 7, 7);
        System.out.println("waysVR:" + waysVR);

        int waysDp = getWaysDp(10, 7, 7);
        System.out.println("waysDp:" + waysDp);
    }

    public int getWaysVR(int i, int j, int rest, int x, int y){
        //1.1 base case：跳出棋盘
        if (i < 0 || i > rows -1 || j < 0 || j > cols -1){
            return 0;
        }

        //1.2 base case: 剩余步数为0时，刚好落在(x,y)
        if (rest == 0){
            return i == x && j == y ? 1 : 0;
        }

        int ways = 0;
        //2 其他情况: 走日可以向8个方向走
        ways += getWaysVR(i -1 , j + 2, rest -1, x, y);
        ways += getWaysVR(i -2 , j + 1, rest -1, x, y);
        ways += getWaysVR(i -2 , j - 1, rest -1, x, y);
        ways += getWaysVR(i -1, j - 2, rest -1, x, y);
        ways += getWaysVR(i + 1, j - 2, rest -1, x, y);
        ways += getWaysVR(i + 2, j - 1, rest -1, x, y);
        ways += getWaysVR(i + 2, j + 1, rest -1, x, y);
        ways += getWaysVR(i + 1, j + 2, rest -1, x, y);

        return ways;
    }

    /**
     * 动态规划
     * @param step
     * @param x
     * @param y
     * @return
     */
    public int getWaysDp(int step, int x, int y){
        int[][][] dp = new int[rows][cols][step + 1];
        dp[x][y][0] = 1;

        for (int rest = 1; rest < step + 1; rest++){
            for (int i=0; i<rows; i++){
                for (int j=0; j<cols; j++){
                    int ways = 0;
                    //2 其他情况: 走日可以向8个方向走
                    ways += dpProcess(i-1, j+2, rest-1, dp);
                    ways += dpProcess(i-2, j+1, rest-1, dp);
                    ways += dpProcess(i-2, j-1, rest-1, dp);
                    ways += dpProcess(i-1, j-2, rest-1, dp);
                    ways += dpProcess(i+1, j-2, rest-1, dp);
                    ways += dpProcess(i+2, j-1, rest-1, dp);
                    ways += dpProcess(i+2, j+1, rest-1, dp);
                    ways += dpProcess(i+1, j+2, rest-1, dp);
                    dp[i][j][rest] = ways;
                }
            }
        }

        return dp[0][0][step];
    }

    private int dpProcess(int i, int j, int rest, int[][][] dp){
        if (i < 0 || i > rows -1 || j < 0 || j > cols -1){
            return 0;
        }
        return dp[i][j][rest];
    }

}
