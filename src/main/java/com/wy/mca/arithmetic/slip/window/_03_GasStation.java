package com.wy.mca.arithmetic.slip.window;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 1、问题描述
 * 2、案例说明
 * 3、解题思路
 *
 * @Description 加油站
 * @Author wangyong01
 * @Date 2023/5/11 3:16 PM
 * @Version 1.0
 */
public class _03_GasStation {

    @Test
    public void gasStationTest(){
        int[] gas = {1,1,5,2,9,3};
        int[] cost = {3,4,1,4,3,4};

        boolean[] canRun = runCircle(gas, cost);
        System.out.println("canRun:" + Arrays.toString(canRun));
    }

    public boolean[] runCircle(int[] gas, int[] cost){
        int N = gas.length;
        boolean[] canRun = new boolean[N];

        //1 gas-cost：计算当前位置跑到下一个位置后剩余的里程
        int[] remain = new int[N];

        //2 准备一个2倍长度的累加和数组
        int M = N * 2;
        int[] sum = new int[M];
        for (int i=0; i<N; i++){
            remain[i] = gas[i] - cost[i];
            sum[i] = remain[i];
            sum[N+i] = remain[i];
        }
        for (int i=1; i<M; i++){
            sum[i] += sum[i-1];
        }

        //3 准备一个长度为N的滑动窗口，如果以i开始的滑动窗口内的最小值如果>0，那么就可以跑完一圈
        //3.1 滑动窗口minIndex记录[0,N)范围内从小到大的数字对应的索引
        Deque<Integer> minIndex = new LinkedList<>();
        for (int i=0; i<N; i++){
            while (!minIndex.isEmpty() && sum[minIndex.getLast()] >= sum[i]){
                minIndex.removeLast();
            }
            minIndex.addLast(i);
        }

        //3.2 滑动窗口前一个值
        int preValue = 0;
        for (int i=0; i<N; i++){
            //3.3 滑动窗口内最小值 - 前一个值就是，就是从i出发
            if (sum[minIndex.getFirst()] - preValue >= 0){
                canRun[i]= true;
            }
            //3.4
            if (minIndex.getFirst() == i){
                minIndex.removeFirst();
            }
            //3.5
            while (!minIndex.isEmpty() && sum[minIndex.getLast()] >= sum[i+N]){
                minIndex.removeLast();
            }
            //3.6
            minIndex.addLast(i+N);
            //3.7 更新前一个值
            preValue = sum[i];
        }

        return canRun;
    }
}
