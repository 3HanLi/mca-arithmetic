package com.wy.mca.arithmetic.dynamic.recursion;

import org.junit.Test;

/**
 * @Description 硬币问题（01）
 * 1、问题描述：arr是货币数组，其中的值都是正数，每个值都认为是不同的；给定一个数字aim，返回组成aim的方法数
 * 2、案例说明
 *    2.1 arr = {1,1,1,2}, aim=2
 *    2.2 组成aim的情况 aim[0]+aim[1], aim[0]+aim[2], aim[1]+aim[2], aim[3]，一共4种
 * 3、解题思路
 *    3.1 暴力递归
 *    3.2 动态规划
 * 4、重点关注：货币不可重复使用考虑的是当前货币要不要得问题；
 * @Author wangyong01
 * @Date 2023/4/12 9:20 PM
 * @Version 1.0
 */
public class _15_CoinsPaperAsDifferent {

    @Test
    public void coinsPaperDifferent(){
        int[] coins = {1,1,2,3};
        int aim = 4;

        int waysVR = waysVR(coins, 0, aim);
        System.out.println("waysVR:" + waysVR);

        int waysDp = waysDp(coins, aim);
        System.out.println("waysDp:" + waysDp);
    }

    public int waysVR(int[] coins, int i, int rest){
        //1.1 base case
        if (rest < 0){
            return 0;
        }

        if (i == coins.length){
            return rest == 0 ? 1 : 0;
        }
        //1.2 其他情况：当前位置要 和 不要
        return waysVR(coins, i+1, rest) + waysVR(coins, i+1, rest-coins[i]);
    }

    public int waysDp(int[] coins, int aim){
        int len = coins.length;
        int[][] dp = new int[len+1][aim+1];
        dp[len][0] = 1;

        for (int i=coins.length-1; i>=0; i--){
            for (int j=0; j<=aim; j++){
                dp[i][j] = dp[i+1][j];
                if (j - coins[i] >= 0){
                    dp[i][j] += dp[i+1][j-coins[i]];
                }
            }
        }

        return dp[0][aim];
    }

}
