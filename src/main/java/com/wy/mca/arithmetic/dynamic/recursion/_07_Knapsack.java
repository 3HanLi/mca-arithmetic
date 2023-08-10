package com.wy.mca.arithmetic.dynamic.recursion;


import com.wy.mca.util.ArrUtil;

/**
 * 背包问题
 *
 * 1）问题描述：有两个长度相等的数组w和v，w代表货物的重量，v代表货物的价值，背包的最大容量为bag，计算这个背包能装下的最大价值是多少
 *
 * 2）案例说明
 *    2.1 w=[3,5,2] ，v=[7,10,5]，bag=5
 *    2.2 当取背包[0,2]下标的货物时，价值最大为12
 *
 * 3）解题思路：
 *    3.1 依次选择或者不选择当前货物，使用背包容量 减去 所选货物质量，获取选择货物的最大价值
 *
 * 4）暴力递归
 *    4.1 base case
 *        a) 当剩余容量rest<0时，说明背包已满，直接返回
 *        b) 当货物取完时，直接返回
 *    4.2 其他情况分析
 *        a) 不要当前货物，那么继续递归：violentRecurse(w,v,i+1,rest)
 *        b) 要当前获取，那么：v[i] + violentRecurse(w,v,i+1,rest-w[i])
 *
 * 5）动态规划 -> 位置依赖
 *    5.1 列出递归调用，查看是否有重复解：violentRecurse(int[] w, int[] v, int i, int rest)
 *        w = {5,2,7}
 *        v = {10,3,8}
 *        bag = 15
 *        [0,1]要，vR(w,v,2,8)
 *        [0,1]不要，vR[w,v,2,8]
 *        此时出现重复解，说明可以改动态规划
 *    5.2 根据变量定义缓存表，根据入参，返回表格对应位置的值
 *    5.3 根据动态模型填表格
 *
 * @author 背包问题
 */
public class _07_Knapsack {

    public static void main(String[] args) {
        int[] w = {3, 5, 2};
        int[] v = {7, 10, 5};
        int bag = 5;
        System.out.println("First round");
        System.out.println("violentInvoke:" + violentInvoke(w, v, bag));
        System.out.println("dp:" + dp(w, v, bag));

        w = ArrUtil.generatePositiveRandomArray(100, 100);
        v = ArrUtil.generatePositiveRandomArray(100, 500);
        bag = 1000;
        System.out.println("Second round");
        System.out.println("violentInvoke:" + violentInvoke(w, v, bag));
        System.out.println("dp:" + dp(w, v, bag));
    }

    /**
     * 递归主函数
     *
     * @param w
     * @param v
     * @param bag
     */
    private static int violentInvoke(int[] w, int[] v, int bag){
        int maxValue = violentRecurse(w, v, 0, bag);
        return maxValue;
    }

    /**
     * 暴力递归
     *
     * @param w
     * @param v
     * @param i
     * @param rest
     * @return
     */
    private static int violentRecurse(int[] w, int[] v, int i, int rest){
        //1 背包已满
        if (rest < 0){
            return -1;
        }
        //2 已装入所有货物
        if (i == w.length){
            return 0;
        }
        int p1 = violentRecurse(w, v, i+1, rest);
        int p2 = 0;
        int restValue = violentRecurse(w, v, i + 1, rest - w[i]);
        if (restValue != -1){
            p2 += v[i] + restValue;
        }

        return Math.max(p1, p2);
    }

    private static int dp(int[] w, int[] v, int bag){
        if (bag < 0 || null == w || v == null || w.length != v.length){
            return 0;
        }

        //1 根据变量定义缓存表
        int N = w.length + 1;
        int M = bag + 1;
        int[][] dp = new int[N][M];

        //3 分析位置依赖，填表格
        for (int i = N - 2; i >= 0; i--) {
            for (int rest = 0; rest < M; rest++) {
                int p1 = dp[i+1][rest];
                int p2 = 0;
                int restValue = rest - w[i] < 0 ? -1 : dp[i+1][rest-w[i]];
                if (restValue != -1){
                    p2 += v[i] + restValue;
                }
                dp[i][rest] = Math.max(p1,p2);
            }
        }

        //2 根据暴力递归主函数传参，返回表格中的值
        return dp[0][bag];
    }


}
