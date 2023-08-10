package com.wy.mca.leetcode.sort.merge;

import com.wy.mca.util.ArrUtil;

/**
 * 给定一个数组arr，两个数Low和upper，返回arr中有多少个子数组的累加和在low和upper之间
 *
 * @author wangyong01
 */
public class CountRangeSum {

    public static void main(String[] args) {
        validate(100);
    }

    private static void validate(int arrLen){
        int lower = 10;
        int upper = 40;
        int[] arr = ArrUtil.generateRandomArray(arrLen, Integer.MAX_VALUE);

        long[] sum = generateSum(arr);
        int sum1 = violenceCrack(sum, lower, upper);
        int sum2 = countRange(sum,0, arr.length-1, lower, upper);

        if (sum1 != sum2){
            System.out.println("程序错误....");
        }else {
            System.out.println("程序正确....");
        }
    }

    private static int violenceCrack(long[] arr, int lower, int upper){
        int sum = 0;

        for (int i=0; i<arr.length; i++){
            if (arr[i] >= lower && arr[i] <= upper){
                sum++;
            }
            for (int j=0; j<i; j++){
                if (arr[i]-arr[j] >= lower && arr[i] - arr[j] <= upper){
                    sum++;
                }
            }
        }

        return  sum;
    }

    private static long[] generateSum(int[] arr){
        long sumI = 0;
        long[] sum = new long[arr.length];

        for (int i=0; i<arr.length;i++){
            sumI += arr[i];
            sum[i] = sumI;
        }
        return sum;
    }

    /**
     * 问题描述 给定一个数组arr，两个数Low和upper，返回arr中有多少个子数组的累加和在low和upper之间
     *
     * 举例说明
     * 1) arr=[1,-1,-2,3]，low和upper为[-1,1]
     * 2) 那么满足条件的子数组如下
     *    [1]	[1,-1]	[1,-1,-2,3]
     *    [-1]	[-1,-2,3]
     *    [-2,3]
     * 3) 满足条件的子数组个数为6
     * 4) 推导说明
     *    4.1 思路转换：定义数组sum，sum[i]记录arr[i]之前的类加和
     *    4.2 假如数组sum长度为18,sum[17]=100,
     *    4.3 区间范围为[lower,upper]=[10,40]
     *    4.4 那么其实就是计算[0-17]范围上有多数范围在[60,90]之间
     *    4.5 继续推导：对于下标为i的元素sum[i]，其实就是计算0-i范围上有多少个数在[60,90]之间
     *
     * 解题思路
     * 1) 将数组arr转换为数组sum,sum中存储i位置之前的累加和，假如i位置累加和为x
     * 2) 假如区间范围是[lower,upper]，其实就是计算i之前累计和在[x-r,x-l]之间的个数
     *
     * @param arr
     * @param l
     * @param r
     * @param lower
     * @param upper
     * @return
     */
    private static int countRange(long[] arr, int l, int r, int lower, int upper){
        if (l == r){
            return (arr[l] >= lower && arr[l] <= upper) ? 1 : 0;
        }
        int m = l + ((r-l) >> 1);
        return  countRange(arr, l, m, lower, upper) +
                countRange(arr, m+1, r, lower, upper) +
                merge(arr, l, m, r, lower, upper);
    }

    private static int merge(long[] arr, int l, int m, int r, int lower, int upper){
        int sum = 0;
        //左边部分的左指针
        int winLl = l;
        //左边部分的右指针
        int winLr = l;
        //眼睛盯着右边的元素，查看左边元素满足[x-upper,x-lower]的下标，计算得到满足条件的个数
        for (int i=m+1; i<=r; i++){
            long min = arr[i] - upper;
            while (winLl <= m && arr[winLl] < min){
                winLl ++;
            }
            long max = arr[i] - lower;
            while (winLr <= m && arr[winLr] <= max){
                winLr ++;
            }
            sum += (winLr - winLl);
        }

        long[] help = new long[r-l+1];
        int p1 = l;
        int p2 = m + 1;
        int i = 0;
        while (p1 <= m && p2 <= r){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (int k=0; k<help.length; k++){
            arr[l++] = help[k];
        }

        return sum;
    }

}
