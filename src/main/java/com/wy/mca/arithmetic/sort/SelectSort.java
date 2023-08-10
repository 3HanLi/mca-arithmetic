package com.wy.mca.arithmetic.sort;

import com.wy.mca.util.ArrUtil;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author wangyong01
 */
public class SelectSort {

    public static void main(String[] args) {
        validate(100);
    }

    private static void validate(int len){
        int[] arr = ArrUtil.generateRandomArray(len, 100);
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        selectSort(arr);
        Arrays.sort(arr2);
        System.out.println("arr=arr2:" + Arrays.equals(arr, arr2));
    }

    /**
     * 选择排序思路
     * 1) 从0-N-1中找到最小值和arr[0]交换
     * 2) 从1-N-1中找到最小值和arr[1]交换
     * ...
     * 以此类推，最终获取有序数组
     *
     * 时间复杂度评估
     *
     * @param arr
     */
    private static void selectSort(int[] arr){
        for (int i=0; i<arr.length-1; i++){
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            ArrUtil.swap(arr, i, minIndex);
        }
    }

}
