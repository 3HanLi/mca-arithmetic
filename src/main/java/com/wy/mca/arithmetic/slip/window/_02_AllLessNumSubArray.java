package com.wy.mca.arithmetic.slip.window;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1、问题描述
 *    给定一个整形数组arr和数字num，某个arr中的子数组sub，如果想达标，必须满足：sub中最大值-sub中最小值<=num，返回arr中达标子数组的数量
 * 2、案例说明
 * 3、解题思路
 *
 * @Description 小于num的子数组数量
 * @Author wangyong01
 * @Date 2023/5/11 11:00 AM
 * @Version 1.0
 */
public class _02_AllLessNumSubArray {

    @Test
    public void allLessNumberArrayTest(){
        int[] arr = {7,6,2,0};
        int sum = 5;
        int nums = allLessNumSubArrayCountVR(arr, sum);
        System.out.println("numsVR:" + nums);

        int arrayCountSlipWindow = allLessNumberSubArrayCountSlipWindow(arr, sum);
        System.out.println("arrayCountSlipWindow:" + arrayCountSlipWindow);
    }

    public int allLessNumSubArrayCountVR(int[] arr, int sum){
        int count = 0;
        for (int L=0; L<arr.length; L++){
            for (int R=L; R<arr.length; R++){
                int minValue = arr[L];
                int maxValue = arr[L];
                for (int k=L; k<=R; k++){
                    minValue = Math.min(arr[k], minValue);
                    maxValue = Math.max(arr[k], maxValue);
                }
                if (maxValue - minValue <= sum){
                    count++;
                }
            }
        }
        return count;
    }

    public int allLessNumberSubArrayCountSlipWindow(int[] arr, int sum){
        int count = 0;
        Deque<Integer> maxDequeIndex = new LinkedList<>();
        Deque<Integer> minDequeIndex = new LinkedList<>();
        int R = 0;
        for (int L=0; L<arr.length; L++){
            while (R < arr.length){
                //[7,5,8,1] -> [2,3]
                while (!maxDequeIndex.isEmpty() && arr[maxDequeIndex.getLast()] <= arr[R]){
                    maxDequeIndex.removeLast();
                }
                maxDequeIndex.addLast(R);

                //[7,5,8,1] - > [3]
                while (!minDequeIndex.isEmpty() && arr[minDequeIndex.getLast()] >= arr[R]){
                    minDequeIndex.removeLast();
                }
                minDequeIndex.addLast(R);

                if (arr[maxDequeIndex.getFirst()] - arr[minDequeIndex.getFirst()] > sum){
                    break;
                } else {
                    R++;
                }
            }
            count += R-L;
            if (maxDequeIndex.getFirst() == L){
                maxDequeIndex.removeFirst();
            }
            if (minDequeIndex.getFirst() == L){
                minDequeIndex.removeFirst();
            }

        }

        return count;
    }

}
