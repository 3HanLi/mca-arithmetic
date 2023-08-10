package com.wy.mca.arithmetic.dynamic.recursion;

import org.junit.Test;

/**
 * @Description
 * @Author wangyong01
 * @Date 2023/4/14 9:39 PM
 * @Version 1.0
 */
public class _18_BolDie {

    @Test
    public void surviveRatio(){
        //1.1 棋盘 N * M
        int N = 6;
        int M = 5;
        //1.2 一开始的坐标(2,3)
        int row = 2;
        int col = 3;
        //1.3 一共需要走k步
        int k = 5;

        int survivePos = survivePosVR(row, col, k, N, M);
        double ratio = ((double) survivePos) / Math.pow(4, k);
        System.out.println("ratio:" + ratio);

        int survivePosDP = survivePosDP(row, col, k, N, M);
        double ratioDP = ((double) survivePosDP) / Math.pow(4, k);
        System.out.println("ratioDp:" + ratioDP);
    }

    public int survivePosVR(int row, int col, int rest, int N, int M){
        if (row < 0 || row > N-1 || col < 0 || col > M-1){
            return 0;
        }
        if (rest == 0){
            return 1;
        }
        int up = survivePosVR(row-1, col, rest-1, N, M);
        int down = survivePosVR(row+1, col, rest-1, N, M);
        int left = survivePosVR(row, col-1, rest-1, N, M);
        int right = survivePosVR(row, col+1, rest-1, N, M);

        return up + down + left + right;
    }

    public int survivePosDP(int row, int col, int rest, int N, int M){
        int[][][] dp = new int[N][M][rest+1];

        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                dp[i][j][0] = 1;
            }
        }

        for (int k=1; k<=rest; k++){
            for (int i=0; i<N; i++){
                for (int j=0; j<M; j++){
                    int up = dpProcess(dp,i-1,j,k-1);
                    int down = dpProcess(dp,i+1,j,k-1);
                    int left = dpProcess(dp, i, j-1, k-1);
                    int right = dpProcess(dp, i, j+1, k-1);
                    dp[i][j][k] = up + down + left + right;
                }
            }
        }

        return dp[row][col][rest];
    }

    private int dpProcess(int[][][] dp, int i, int j, int k){
        if (i<0 || j<0 || i==dp.length || j==dp[0].length){
            return 0;
        }
        return dp[i][j][k];
    }
}
