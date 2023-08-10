package com.wy.mca.arithmetic.dynamic.recursion;

import org.junit.Test;

/**
 * @Description 最小路径和
 * @Author wangyong01
 * @Date 2023/4/10 9:19 PM
 * @Version 1.0
 */
public class _14_MinPathSum {

    @Test
    public void sumTest(){
        int[][] matrix = {
                {3,5,2,9},
                {4,2,8,3},
                {10,1,7,1},
                {5,6,4,0}
        };

        int minPathSumVR = minPathSumVR(matrix, 0, 0);
        System.out.println("minPathSumVR:" + minPathSumVR);

        int sumDp2 = minPathSumDp2(matrix);
        System.out.println("sumDp2:" + sumDp2);

        int sumDp3 = minPathSumDp3(matrix);
        System.out.println("sumDp3:" + sumDp3);
    }

    public int minPathSumVR(int[][] matrix, int i, int j){
        int row = matrix.length;
        int col = matrix[0].length;
        if (i == row-1 && j == col-1){
            return matrix[i][j];
        }

        if (i == row -1){
            return matrix[i][j] + minPathSumVR(matrix, i, j+1);
        }
        if (j == col -1){
            return matrix[i][j] + minPathSumVR(matrix, i+1, j);
        }

        int downSum = minPathSumVR(matrix, i + 1, j);
        int rightSum = minPathSumVR(matrix, i, j+1);

        return matrix[i][j] + Math.min(downSum, rightSum);
    }

    public int minPathSumDp1(int[][] matrix){
        return 0;
    }

    public int minPathSumDp2(int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];

        dp[0][0] = matrix[0][0];
        //填充第一行
        for (int j=1; j<col; j++){
            dp[0][j] = matrix[0][j] + dp[0][j-1];
        }

        //填充第一列
        for (int i=1; i<row; i++){
            dp[i][0] = matrix[i][0] + dp[i-1][0];
        }

        for (int i=1; i<row; i++){
            for (int j=1; j<col; j++){
                dp[i][j] = matrix[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[row-1][col-1];
    }

    public int minPathSumDp3(int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        int[] dp = new int[col];
        dp[0] = matrix[0][0];

        for (int j=1; j<col; j++){
            dp[j] = matrix[0][j] + dp[j-1];
        }

        for (int i=1; i<row; i++){
            dp[0] = matrix[i][0] + dp[0];
            for (int j=1; j<col; j++){
                dp[j] = matrix[i][j] + Math.min(dp[j], dp[j-1]);
            }
        }

        return dp[col-1];
    }

}
