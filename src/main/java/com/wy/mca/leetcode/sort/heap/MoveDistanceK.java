package com.wy.mca.leetcode.sort.heap;

import cn.hutool.core.util.RandomUtil;
import com.wy.mca.util.ArrUtil;
import com.wy.mca.util.PrintUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author wangyong01
 */
public class MoveDistanceK {

    public static void main(String[] args) {
        int length = 10;
        int k= 3;
        int[] arr = getDistanceLessK(length, k);
        PrintUtil.printOneDimensionArr(arr);

        sort(arr, k);
        PrintUtil.printOneDimensionArr(arr);
    }

    /**
     * 问题描述：现在有一个几乎有序的数组，如果他排好序的话，每个元素移动的范围不超过K，而 K远远小于数组长度
     *
     * 解决思路：
     * 1) 准备一个长度为K的小根堆，依次放入数组的前K-1个元素
     * 2) 从下标为K开始遍历数组，将元素依次加入小根堆，然后弹出小根堆中的元素后和数组当前下标元素进行交换
     *
     * 时间复杂度：N * logK
     *
     * @param arr
     * @param k
     */
    private static void sort(int[] arr, int k){
        //1 定义优先队列(小根堆)，长度为k
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);

        //2 将数组中k下标之前的元素加入优先队列
        for (int i=0; i<k; i++){
            priorityQueue.add(arr[i]);
        }

        //3 弹出优先队列中的值(最小值)，放到数组的0下标
        //3.1 队列中加入k+1位置的元素，弹出后放到数组1的下标
        //3.2 队列中加入k+2位置的元素，弹出后放到数组2的下标
        int swapIndex = -1;
        for (int i=k; i<arr.length; i++){
            priorityQueue.add(arr[i]);
            int poll = priorityQueue.poll();
            swapIndex = i-k;
            arr[swapIndex] = poll;
        }

        while (!priorityQueue.isEmpty()){
            arr[++swapIndex] = priorityQueue.poll();
        }
    }

    /**
     * 生成一个排序后，数组元素移动范围不超过K的数组
     * k < length：k远远小于length
     * @param length
     * @param k
     * @return
     */
    private static int[] getDistanceLessK(int length, int k){
        int[] arr = ArrUtil.generateRandomArray(length, 100);
        Arrays.sort(arr);
        boolean[] swap = new boolean[arr.length];
        for (int i=0; i<length-k; i++){
            int maxLenK = i + k;
            int randomIndex = RandomUtil.randomInt(i+1, maxLenK+1);
            if (!swap[i] && !swap[randomIndex]){
                ArrUtil.swap(arr, i, randomIndex);
                swap[i] = true;
                swap[randomIndex] = true;
            }
        }

        return arr;
    }

}
