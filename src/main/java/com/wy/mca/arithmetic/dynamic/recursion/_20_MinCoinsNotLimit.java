package com.wy.mca.arithmetic.dynamic.recursion;

import org.junit.Test;

/**
 * @Description
 * @Author wangyong01
 * @Date 2023/5/8 2:47 PM
 * @Version 1.0
 */
public class _20_MinCoinsNotLimit {

    @Test
    public void minCoinsNotLimit(){
        int[] arr = {2,5,10,12};
        int aim = 138;
        int coins = minCoinsNotLimitVR(arr, 0, aim);
        System.out.println("coins:" + coins);

        int coinsDP = minCoinsNotLimitDP(arr, aim);
        System.out.println("coinsDP:" + coinsDP);

        int coinsDP2 = minCoinsNotLimitDP2(arr, aim);
        System.out.println("coinsDP2:" + coinsDP2);
    }

    public int minCoinsNotLimitVR(int[] arr, int index, int rest){
        if (rest == 0){
            return 0;
        }
        if (index == arr.length){
            return rest==0 ? 0 : Integer.MAX_VALUE;
        }
        int coins = Integer.MAX_VALUE;
        for (int zhang=0; zhang * arr[index] <= rest; zhang++){
            int next = minCoinsNotLimitVR(arr, index + 1, rest - zhang * arr[index]);
            if (next != Integer.MAX_VALUE){
                coins = Math.min(coins, zhang + next);
            }
        }

        return coins;
    }

    public int minCoinsNotLimitDP(int[] arr, int aim){
        int length = arr.length;
        int[][] dp = new int[length +1][aim+1];
        for (int rest=1; rest<=aim; rest++){
            dp[length][rest] = Integer.MAX_VALUE;
        }

        for (int index=length-1; index>=0; index--){
            for (int rest=1; rest<=aim; rest++){
                int coins = Integer.MAX_VALUE;
                for (int zhang=0; zhang * arr[index] <= rest; zhang++){
                    int next = minCoinsNotLimitVR(arr, index + 1, rest - zhang * arr[index]);
                    if (next != Integer.MAX_VALUE){
                        coins = Math.min(coins, zhang + next);
                    }
                }
                dp[index][rest] = coins;
            }
        }

        return dp[0][aim];
    }

    public int minCoinsNotLimitDP2(int[] arr, int aim){
        int length = arr.length;
        int[][] dp = new int[length +1][aim+1];
        for (int rest=1; rest<=aim; rest++){
            dp[length][rest] = Integer.MAX_VALUE;
        }

        for (int index=length-1; index>=0; index--){
            for (int rest=1; rest<=aim; rest++){
                //位置依赖分析
                dp[index][rest] = dp[index+1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest-arr[index]] != Integer.MAX_VALUE){
                    dp[index][rest] = Math.min(dp[index][rest-arr[index]] + 1, dp[index+1][rest]);
                }
            }
        }

        return dp[0][aim];
    }

}
