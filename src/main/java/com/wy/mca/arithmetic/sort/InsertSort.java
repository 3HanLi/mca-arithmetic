package com.wy.mca.arithmetic.sort;

import com.wy.mca.util.ArrUtil;

import java.util.Arrays;

/**
 * @author wangyong01
 */
public class InsertSort {

    public static void main(String[] args) {
        validate(100);
    }

    private static void validate(int len){
        int[] arr = ArrUtil.generateRandomArray(len, 100);
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        insertSort(arr);
        Arrays.sort(arr2);
        System.out.println("arr=arr2:" + Arrays.equals(arr, arr2));
    }

    /**
     * 插入排序解题思路
     * 1) 让arr[0-0]上有序
     * 2) 让arr[0-1]上有序，如果arr[1]<arr[0]，交换
     * 3) 让arr[0-n-1]上有序，arr[n-1]不停向左移动，直到左边的数字不再比自己大，停止移动
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i=1; i<arr.length; i++){
            for (int j=i-1; j>=0; j--){
                if (arr[j] >= arr[j+1]){
                    ArrUtil.swap(arr, j, j+1);
                }
            }
        }
    }

}
