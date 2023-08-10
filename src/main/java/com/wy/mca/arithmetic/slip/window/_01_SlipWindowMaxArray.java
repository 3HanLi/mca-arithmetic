package com.wy.mca.arithmetic.slip.window;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description 固定窗口内最大值
 * 1、问题描述
 * 2、案例说明
 * 3、解题思路
 *    3.1 暴力递归
 *    3.2 滑动窗口
 *
 * @Author wangyong01
 * @Date 2023/5/10 6:30 PM
 * @Version 1.0
 */
public class _01_SlipWindowMaxArray {

    public static void main(String[] args) {
        int[] arr = new int[]{4,3,5,4,3,3,6,7};
        int w = 3;
        int[] maxWindowVR = maxWindowVR(arr, w);
        System.out.println("maxWindowVR:" + Arrays.toString(maxWindowVR));

        int[] maxWindowSlip = maxWindowSlip(arr, w);
        System.out.println("maxWindowSlip:" + Arrays.toString(maxWindowSlip));
    }

    public static int[] maxWindowVR(int[] arr, int w){
        int len = arr.length;
        int[] maxWindow = new int[len -w+1];

        int index = 0;
        int L = 0;
        int R = w-1;
        while (R < len){
            int max = Integer.MIN_VALUE;
            for (int i=L; i<=R; i++){
                max = Math.max(arr[i], max);
            }
            maxWindow[index++] = max;
            L++;
            R++;
        }

        return maxWindow;
    }

    public static int[] maxWindowSlip(int[] arr, int w){
        //1 滑动窗口
        int[] maxWindow = new int[arr.length-w+1];
        int index = 0;
        //2 双端队列：存放索引，索引对应的元素【从大->小】
        Deque<Integer> dequeIndex = new LinkedList<>();
        for (int R=0; R<arr.length; R++){
            //2.1 双端队列索引维护，保证索引对应的元素【从大->小】
            while (!dequeIndex.isEmpty() && arr[dequeIndex.getLast()] <= arr[R]){
                dequeIndex.removeLast();
            }
            dequeIndex.addLast(R);
            //2.2 最大元素对应的下标和右侧窗口R超过w，左侧窗口向右滑动
            if (R - w == dequeIndex.getFirst()){
                dequeIndex.removeFirst();
            }
            //2.3 只要R下标超过w-1，就需要不断更新窗口内最大值
            if (R >= w-1){
                maxWindow[index++] = arr[dequeIndex.getFirst()];
            }
        }

        return maxWindow;
    }
}
