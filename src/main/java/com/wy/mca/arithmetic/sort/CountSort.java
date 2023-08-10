package com.wy.mca.arithmetic.sort;

import com.wy.mca.util.NumUtil;

import java.util.Arrays;

/**
 * 计数排序
 * @author wangyong01
 */
public class CountSort {

    public static void main(String[] args) {
        validate(100, 100, 100);
    }

    private static void validate(int size, int range, int times){
        for (int i=0; i<times; i++){
            int[] arr = NumUtil.generate(size, range);
            int[] copyArr = Arrays.copyOf(arr, arr.length);

            countSort(arr);
            Arrays.sort(copyArr);

            if (!Arrays.equals(arr,copyArr)){
                System.out.println("fucked");
            }
        }
    }

    /**
     * 计数排序
     * 题目要求
     * 1) 数组元素值不能为负数
     * 2) 数组范围不能过大
     *
     * 举例说明
     * 1) 假如数组中年龄是：arr = {1,3,5,1,5,8,9,18,28,91,90, 200}
     * 2) 数据的取值范围都不大，就可以采用计数排序
     *
     * 解题思路
     * 1) 遍历数组arr获取到最大值max，创建数组arr01
     *    int[] arr01 = new int[max+1]
     * 2) 遍历数组arr，将每个值arr[i]作为arr01的下标并++，即：arr01[arr[i]]++
     * 3) 遍历数组arr01，获取元素num>0，一次填充num个arr02[i]值
     * @param arr
     */
    public static void countSort(int[] arr){
        //1.1 获取元素最大值
        int max = Integer.MIN_VALUE;
        for (int num : arr){
            max = Math.max(max, num);
        }

        //1.2 定义数组，将每个值arr[i]++
        int[] arr01 = new int[max + 1];
        for (int num : arr){
            arr01[num]++;
        }

        //1.3 将数值取出来后重新放入arr
        int index = 0;
        for (int i=0; i<arr01.length; i++){
            if (arr01[i] > 0){
                while (arr01[i]-- > 0){
                    arr[index++] = i;
                }
            }
        }
    }
}
