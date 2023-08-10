package com.wy.mca.leetcode.sort.merge;

import com.wy.mca.util.ArrUtil;

/**
 * 归并排序解决逆序对问题
 * 问题描述：在一个数组中，如果两个数是降序关系，则是逆序对，求出逆序对的数量
 * 举例说明：
 *    [3,1,0,4,3,1]
 *    逆序对
 *    [3,1]	[3,0]	[3,1]
 *    [1,0]
 *    [4,3]	[4,1]
 *    [3,1]
 * 解题思路
 * 1) 让数组的左部分有序
 * 2) 让数组的有部分有序
 * 3) 数组合并
 *    3.1 准备辅助数组help，比较左右数组中值的大小，谁小拷贝谁
 *    3.2 拷贝右侧时说明发生逆序，计算逆序对
 *
 * @author admin
 */
public class ReversePair {

    public static void main(String[] args) {
        validate(2000);
    }

    private static void validate(int arrLen){
        int[] arr = ArrUtil.generateRandomArray(arrLen, 1000);
        int sum1 = violenceCrack(arr);
        int sum2 = smallSum(arr,0, arr.length-1);

        if (sum1 != sum2){
            System.out.println("程序错误....");
        }else {
            System.out.println("程序正确....");
        }
    }

    private static int violenceCrack(int[] arr){
        int sum = 0;
        for (int i=0; i<arr.length-1; i++){
            for (int j=i+1; j<arr.length; j++){
                if (arr[i] > arr[j]){
                    sum ++;
                }
            }
        }
        return  sum;
    }

    private static int smallSum(int[] arr, int l, int r){
        if (l == r){
            return 0;
        }
        int m = l + ((r-l) >> 1);
        return smallSum(arr, l, m) + smallSum(arr, m+1, r) + mergeReverse(arr, l, m, r);
    }

    /**
     * 归并 -> 由小到大
     * @param arr
     * @param l
     * @param m
     * @param r
     * @return
     */
    private static int mergePositive(int[] arr, int l, int m, int r){
        int sum = 0;
        int[] help = new int[r-l+1];
        int i = 0;

        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r){
            sum += arr[p1] > arr[p2] ? (m - p1 + 1) : 0;
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
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

    /**
     * 归并排序 -> 由大到小
     * @param arr
     * @param l
     * @param m
     * @param r
     * @return
     */
    private static int mergeReverse(int[] arr, int l, int m, int r){
        int sum = 0;
        int[] help = new int[r-l+1];
        int i = 0;

        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r){
            sum += arr[p1] > arr[p2] ? (r - p2 + 1) : 0;
            help[i++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];
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
