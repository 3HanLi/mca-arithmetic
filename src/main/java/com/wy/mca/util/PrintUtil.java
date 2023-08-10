package com.wy.mca.util;


/**
 * @author wangyong01
 */
public class PrintUtil {

    /**
     * 打印一位数组
     * @param arr
     */
    public static void printOneDimensionArr(int[] arr){
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i] +"\t");
        }
        System.out.println();
    }

    /**
     * 打印数组
     * @param wvTable
     */
    public static void printTwoDimensionArr(int[][] wvTable){
        for (int i=0; i<wvTable.length; i++){
            for (int j=0; j<wvTable[i].length; j++){
                System.out.print(wvTable[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 打印
     * @param arr
     */
    public static <T> void printObject(T[] arr){
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }
}
