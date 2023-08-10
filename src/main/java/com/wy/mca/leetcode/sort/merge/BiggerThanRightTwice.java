package com.wy.mca.leetcode.sort.merge;

import com.wy.mca.util.ArrUtil;

/**
 * 两倍问题
 * @author wanyong01
 */
public class BiggerThanRightTwice {

    public static void main(String[] args) {
        validate(100);
    }

    private static void validate(int arrLen){
        int[] arr = ArrUtil.generateRandomArray(arrLen, 100);
        int sum1 = violenceCrack(arr);
        int sum2 = biggerNum(arr,0, arr.length-1);

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
                if (arr[i] > 2 * arr[j]){
                    sum ++;
                }
            }
        }
        return  sum;
    }

    /**
     * 两倍问题
     * 问题描述：一个数字右边有多少个数字，就算乘以2都比自己小
     * 举例说明：
     * 1) 数组为[6,7,1,3,2]
     * 2) 求解过程
     *    6右边有两个数1 2，乘以2也比自己小      ==>2个
     *    7右边有三个数1 3 2，乘以2也比自己小    ==>3个
     * 3) 问题答案：2 + 3
     *
     * 解题思路 这里采用降序的方式
     * 1) 先让左侧有序
     * 2) 再让右侧有序
     * 3) 在合并排序的过程中计算结果
     *    3.1 在合并的过程中，我们始终能得到一个结论：[l,m]有序,[m+1,r]有序
     *    3.2 分别滑动左右两个数组的指针
     *    3.3 如果左侧的元素 > 2 * 右侧的元素，则满足条件的个数为 [r - winR + 1];
     *
     * 4) 扩展：采用升序排序
     *
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int biggerNum(int[] arr, int l, int r){
        if (l == r){
            return 0;
        }
        int m = l + ((r-l) >> 1);
        return biggerNum(arr, l, m) + biggerNum(arr, m+1, r) + mergeReverse(arr, l, m, r);
    }

    private static int mergeReverse(int[] arr, int l, int m, int r){
        int num = 0;
        int[] help = new int[r - l + 1];
        int i=0;

        int p1 = l;
        int p2 = m + 1;


        int winR = m + 1;
        for (int k=l; k<=m; k++){
//            while (winR <= r && arr[k] > 2 * arr[winR]){
//                winR ++;
//            }
//            num += winR - m -1;
            //采用降序方式实现
            while (winR <= r){
                if (arr[k] > 2 * arr[winR]){
                    break;
                }
                winR ++;
            }
            num += r - winR + 1;
        }

        while (p1 <= m && p2 <= r){
//            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
            //采用降序方式排序
            help[i++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1<=m){
            help[i++] = arr[p1++];
        }
        while (p2<=r){
            help[i++] = arr[p2++];
        }
        for (int k=0; k<help.length; k++){
            arr[l+k] = help[k];
        }

        return num;
    }
}
