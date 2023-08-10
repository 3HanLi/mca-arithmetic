package com.wy.mca.leetcode.sort.merge;

import com.wy.mca.util.ArrUtil;

/**
 * 归并排序解决小和问题
 *
 * @author wangyong01
 */
public class SmallSum {

    public static void main(String[] args) {
        validate(100);
    }

    private static void validate(int arrLen){
        int[] arr = ArrUtil.generateRandomArray(arrLen, 10);
        int sum1 = violenceCrack(arr);
        int sum2 = smallSum(arr,0, arrLen-1);

        if (sum1 != sum2){
            System.out.println("程序错误....");
        }else {
            System.out.println("程序正确....");
        }
    }

    /**
     * 暴力破解
     *
     * @param arr
     * @return
     */
    private static int violenceCrack(int[] arr){
        int sum = 0;
        for (int i=0; i<arr.length; i++){
            for (int j=0; j<i; j++){
                if (arr[j] < arr[i]){
                    sum += arr[j];
                }
            }
        }
        return  sum;
    }

    /**
     * 小和问题说明：对于数组arr，求出每个元素左侧比自己小的数值，并计算这些值的和
     * 举例说明
     * 1) 数组arr=[6,3,2,1,6,7]
     * 2) 小和求解过程
     *    arr[0] = 6，左侧比自己小的元素没有，类加和为0
     *    arr[1] = 3，左侧比自己小的元素没有，类加和为0
     *    ..
     *    arr[4] = 6，左侧比自己小的元素为[3,2,1]，类加和为6
     *    arr[5] = 7，左侧比自己小的元素为[6,3,2,1,6]，类加和为18
     * 3) 小和为6 + 18 = 24
     *
     * 解题思路
     * 1) 让数组的左部分有序
     * 2) 让数组的有部分有序
     * 3) 合并数组，在合并的过程中执行如下操作
     *    3.1 准备辅助数组help，比较左右数组中值的大小，谁小拷贝谁
     *    3.2 拷贝左侧产生小和，计算[右侧]数组中有几个比自己大
     *    3.3 拷贝右侧不产生小和
     * 4) 总结：
     *    4.1 计算左侧有几个比自己小，其实就是计算右侧有几个比自己大
     *    4.2 同组之间不产生小和，跨组之间才产生小和
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int smallSum(int[] arr, int l, int r){
        if (l == r){
            return 0;
        }
        int m = l + ((r-l) >> 1); //坑
        return smallSum(arr, l, m) + smallSum(arr, m+1, r) + merge(arr, l, m, r);
    }

    /**
     * merge
     * @param arr
     * @param l
     * @param m
     * @param r
     * @return
     */
    private static int merge(int[] arr, int l, int m, int r){
        //[6,3,2,1,6,7] l=0, m = 2, r = 5
        //[2,3,6,1,6,7]
        int sum = 0;
        int[] help = new int[r-l+1];
        int i = 0;

        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r){
            sum += arr[p1] < arr[p2] ? arr[p1] * (r-p2+1) : 0 ;
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
