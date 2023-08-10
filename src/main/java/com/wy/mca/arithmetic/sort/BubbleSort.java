package com.wy.mca.arithmetic.sort;

import com.wy.mca.util.ArrUtil;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        validate(100);
    }

    private static void validate(int len){
        int[] arr = ArrUtil.generateRandomArray(len, 100);
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        bubbleSort(arr);
        Arrays.sort(arr2);
        System.out.println("arr=arr2:" + Arrays.equals(arr, arr2));
    }

    /**
     * 冒泡排序思路
     * 1) arr[0]和arr[1]比较，大的值放在arr[1]
     * 2) arr[1]和arr[2]比较，大的值放在arr[2]
     * ...
     * 3) arr[n-2]和arr[n-1]比较，大的值放在arr[n-1]
     *
     * 总结：
     * 0 ~ N-1
     * 0 ~ N-2
     * 0 ~ N-3
     * @param arr
     */
    private static void bubbleSort(int[] arr){
        for (int i=arr.length; i>0; i--){
            //0 - N-1
            //0 - N-2
            for (int j=0; j<i-1; j++){
                if (arr[j] > arr[j+1]){
                    ArrUtil.swap(arr, j, j+1);
                }
            }
        }
    }
}
