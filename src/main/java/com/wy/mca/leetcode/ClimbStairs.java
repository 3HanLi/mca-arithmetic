package com.wy.mca.leetcode;

/**
 * 爬楼梯
 * @author wangyong01
 */
public class ClimbStairs {

    public static void main(String[] args) {
        int climbStairs = climbStairs(8);
        System.out.printf("climbStairs01 ->" + climbStairs);
    }

    /**
     * 思路一：找规律
     * n=1 -> 1
     * n=2 -> 2
     * n=3 -> 3
     * n=4 -> 5
     * n=5 -> 8
     * n=6 -> 13
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n <= 3){
            return n;
        }
        return climbStairs(n-1) + climbStairs2(n-2);
    }

    public static int climbStairs2(int n) {
        return 0;
    }


}
