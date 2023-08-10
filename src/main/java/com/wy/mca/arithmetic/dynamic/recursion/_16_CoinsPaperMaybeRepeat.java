package com.wy.mca.arithmetic.dynamic.recursion;

import org.junit.Test;

/**
 * @Description
 * @Author wangyong01
 * @Date 2023/4/14 11:06 AM
 * @Version 1.0
 */
public class _16_CoinsPaperMaybeRepeat {

    @Test
    public void coinsPaperRepeatUse(){
        int[] coins = {2,1,5,10};
        int aim = 20;

        int repeatVR = coinsPaperRepeatVR(coins, 0, aim);
        System.out.println("repeatVR:" + repeatVR);

        int repeatDP1 = coinsPaperRepeatDP1(coins, aim);
        System.out.println("repeatDP1:" + repeatDP1);

        int repeatDP2 = coinsPaperRepeatDP2(coins, aim);
        System.out.println("repeatDP2:" + repeatDP2);
    }

    public int coinsPaperRepeatVR(int[] coins, int i, int rest){
        if (i == coins.length){
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang=0; zhang*coins[i]<=rest; zhang++){
            ways += coinsPaperRepeatVR(coins, i+1, rest-zhang*coins[i]);
        }

        return ways;
    }

    public int coinsPaperRepeatDP1(int[] coins, int rest){
        int len = coins.length;
        int[][] dp = new int[len+1][rest+1];

        dp[len][0] = 1;

        for (int i= coins.length -1; i>=0; i--){
            for (int j=0; j<=rest; j++){
                int ways = 0;
                // dp(i,j) 依赖的单元格是动态的，这就导致时间复杂度上升，需要分析规律
                for (int zhang=0; zhang*coins[i]<=j; zhang++){
                    ways += dp[i+1][j-zhang*coins[i]];
                }
                dp[i][j] = ways;
            }
        }

        return dp[0][rest];
    }

    public int coinsPaperRepeatDP2(int[] coins, int rest){
        int len = coins.length;
        int[][] dp = new int[len+1][rest+1];

        dp[len][0] = 1;

        for (int i= coins.length -1; i>=0; i--){
            for (int j=0; j<=rest; j++){
                //根据规律填充dp(i,j)位置的值
                dp[i][j] = dp[i+1][j];
                if (j-coins[i]>=0){
                    dp[i][j] += dp[i][j-coins[i]];
                }
            }
        }

        return dp[0][rest];
    }

}
