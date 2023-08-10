package com.wy.mca.arithmetic.sort;

import com.wy.mca.util.ArrUtil;
import com.wy.mca.util.NumUtil;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {
        validate(1000, 1000, 1000);
    }

    /**
     * 对数器验证
     * @param length
     * @param range
     * @param times
     */
    public static void validate(int length, int range, int times){
        for (int i=0; i<times; i++){
            int[] arr = ArrUtil.generatePositiveRandomArray(length, range);
            int[] copyOfArr = Arrays.copyOf(arr, arr.length);
            radixSort(arr);
            Arrays.sort(copyOfArr);
            if (!Arrays.equals(arr, copyOfArr)){
                System.out.println("run error");
            }
        }

    }

    public static void radixSort(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int num : arr){
            max = Math.max(num, max);
        }
        radixSort(arr, 0, arr.length-1, NumUtil.calDigit(max));
    }

    /**
     * 基数排序
     * 问题描述
     * 1) 对非负整数进行排序
     *
     * 解题思路
     * 1) 找到数组中最大的数字，位数为digit，对数组中其他元素位数不足digit的高位补零
     * 2) 个位数入桶：定义长度为10的桶，依次遍历数组元素，将个位数放到桶对应下标
     * 3) 从0号桶开始，依次倒出桶中的元素，原则：先入先出
     * 4) 继续上述操作步骤 2) 和 3)
     * 5) 最终倒出来的元素即是排好序的元素
     *
     * 举例说明
     * 1) 数组arr={103,13,27,25,17,9}，最大元素103对应的位数为3，对数组高位补零如下
     *    arr={103,013,027,025,017,09}
     * 2) 按照个位数入桶如下
     *    3==>  103 013
     *    5==>  025
     *    7==>  027 017
     *    9==>  009
     * 3) 将桶中元素倒出来
     *    103 013 025 027 017 009
     * 4.1) 继续十位数
     *    0==>  103 009
     *    1==>  013 017
     *    2==>  025 027
     *    倒出来后
     *    103 009 013 017 025 027
     * 4.2) 继续百位数
     *    0==> 009 013 017 025 027
     *    1==> 103
     *    倒出来后009 013 017 025 027
     * 5) 此时数组有序
     *
     * 前提说明：下面的方法对上述解题过程进行优化
     * 优化思路
     * 1) 准备长度为10的数组count，依次代表0-9出现的个数
     * 2) 准备长度为10的数组count01，数组的每个元素记录count数组元素的前缀类加和
     *    每个元素值的含义：<= 当前元素的个数
     *    比如：count[9] = 100，表示个位数 <= 9的元素有100个
     * 3) 准备一个辅助数组help，用于存储我们倒出来的桶中的元素顺序
     * 4) 逆序遍历数组arr，获取每个元素的个位数n（第一轮是个位数，第二轮是十位数..），
     *    help[count01[n]-1] = arr[i]
     *    count[n]--
     * 5) 重复上面的步骤
     *
     * 逆序遍历的原因
     * 1) 是为了将同一个桶中后面的元素放在后面，也就是这里实现了队列的先进先出，
     * 2) 举例说明
     *    2.1 arr = {103,013}   ==> 都位于3号桶，出桶顺序是103 013
     *        count[3] = 2; ==> count01[3] = 2
     *    2.2 逆序遍历元素
     *        013的下标为count[3]-1 = 1;
     *        103的下标为count[3]-1 = 0;
     *    2.3 此时
     *    help={103,013}
     *
     * 优化的原因
     * 1) 如果不使用help数组表示桶中倒出来的元素，那么我们就需要准备10个桶，依次遍历来倒出元素
     *
     * 时间复杂度 O(N * digit) ==> O(N)
     * 1) digit是最大元素的位数
     * 2) 由于数字确定的时候，digit就固定了，所以时间复杂度为O(N)
     *
     * @param arr
     * @param L
     * @param R
     * @param digit
     */
    public static void radixSort(int[] arr, int L, int R, int digit){
        for (int d=1; d<=digit; d++){
            //1.1 count元素记录arr[i]个位数字出现的个数
            int[] count = new int[10];
            for (int i=L; i<=R; i++){
                int num = NumUtil.getNum(arr[i], d);
                count[num]++;
            }
            //1.2 复用count，更新count的元素的值为前缀和
            for (int i=1; i<count.length; i++){
                count[i] = count[i] + count[i-1];
            }
            //1.3 辅助数组，存储倒出来的元素
            int[] help = new int[R-L+1];
            for (int i=R; i>=L; i--){
                int num = NumUtil.getNum(arr[i], d);
                help[count[num] - 1] = arr[i];
                count[num]--;
            }
            //1.4 将help中的元素倒出来后放到arr，然后开始从十位数入桶
            for (int i=0, j=L; j<=R; j++, i++){
                arr[j] = help[i];
            }
        }

    }

}
