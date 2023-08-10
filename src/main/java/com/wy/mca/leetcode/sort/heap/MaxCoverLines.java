package com.wy.mca.leetcode.sort.heap;

import cn.hutool.core.util.RandomUtil;
import com.wy.mca.util.PrintUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段数
 *
 * @author wangyong01
 */
public class MaxCoverLines {

    public static void main(String[] args) {
        int linesNum = 500;
        int rangeLeft = 0;
        int rangeRight = 100;
        int[][] lines = generateLines(linesNum, rangeLeft, rangeRight);
//        int[][] lines = {{1,4},{5,9},{4,7},{5,6},{1,3}};

        int maxCoverLinesWithViolent = getMaxCoverLinesWithViolent(lines);
        int maxCoverLinesWithHeap = getMaxCoverLinesWithHeap(lines);
        if (maxCoverLinesWithHeap != maxCoverLinesWithViolent){
            System.out.println("程序错误");
            System.out.println("violent:" + maxCoverLinesWithViolent);
            System.out.println("heap:" + maxCoverLinesWithHeap);
            PrintUtil.printTwoDimensionArr(lines);
        }
    }

    /**
     * 问题描述：
     * 给定很多线段，假如线段的区间范围是[start,end]，左右都是闭区间，start和end都是整数，线段重合的
     * 长度>=1，返回最多重合区域中，包含了几个线段
     *
     * 举例说明
     * 1) 现在有线段[1,6] [2,8] [3,10]，线段最多重合区域是[3,6]，包含了3个线段
     *
     * 暴力破解-解题思路
     * 1) 计算线段中出现的最大值min和最小值max，在min和max之间存在很多的中间数.5，比如：1.5，2.5，3.5等等
     * 2) 遍历所有线段，依次查看包含每个.5的线段个数，取最大的那个个数即可
     *
     * 时间复杂度：(max-min) * N
     * 1) N为线段个数
     * @param lines
     * @return
     */
    private static int getMaxCoverLinesWithViolent(int[][] lines){
        //1.1 计算最大值和最小值
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for (int[] oneArr: lines){
            minVal = Math.min(oneArr[0], minVal);
            maxVal = Math.max(oneArr[1], maxVal);
        }

        //1.2 从minVal->maxVal遍历，取[.5]的数字
        int coverCount = 0;
        double midInitialVal = minVal + 0.5;
        while (minVal < maxVal){
            int curMidCount = 0;
            //1.3 遍历所有线段，计算每个.5对应的线段个数
            for (int i=0; i<lines.length; i++){
                if (lines[i][0] < midInitialVal && lines[i][1] > midInitialVal){
                    curMidCount ++;
                }
            }
            //1.4 取每个[.5]对应的线段数的最大值
            coverCount = Math.max(coverCount, curMidCount);
            midInitialVal++;
            minVal ++;
        }

        return coverCount;
    }

    /**
     * 通过堆（优先队列）解决最大线段覆盖问题
     * 解题思路：
     * 1) 对线段按照start进行排序
     * 2) 准备一个小根堆，依次遍历所有线段，弹出小根堆中小于start的元素，并压入end
     * 3) 小根堆中曾经的最大长度 就是 我们要计算的重合区域最多的线段数量
     *
     * 举例说明
     * 1) [1,7]	小根堆一开始为空，那么什么也不弹出，压入7           ==>[7]
     * 2) [2,3]	小根堆中小于等于2的元素没有，什么也不弹出，压入3	    ==>[3,7]
     * 3) [4,6]	小根堆中小于等于4的元素为3，弹出3，压入6			==>[6,7]
     * 4) 最终的结果是2个
     *
     * 时间复杂度：N * logN
     *
     * @param lines
     * @return
     */
    private static int getMaxCoverLinesWithHeap(int[][] lines){
        Line[] lineArr = new Line[lines.length];
        for (int i=0; i<lines.length; i++){
            lineArr[i] = new Line();
            lineArr[i].start = lines[i][0];
            lineArr[i].end = lines[i][1];
        }
        //1.1 对线段按照start进行排序
        Arrays.sort(lineArr, Comparator.comparingInt(o -> o.start));

        //1.2 准备小根堆
        int maxSize = 0;
        PriorityQueue<Integer> endPriorityQueue = new PriorityQueue<>();
        for (Line line : lineArr){
            //1.3 弹出小根堆中，所有小于当前线段start的元素
            while (!endPriorityQueue.isEmpty() && endPriorityQueue.peek() <= line.start){
                endPriorityQueue.poll();
            }
            //1.4 并把当前线段的end加入小根堆
            endPriorityQueue.add(line.end);
            //2 返回小根堆曾经的最大长度
            maxSize = Math.max(endPriorityQueue.size(), maxSize);
        }

        return maxSize;
    }

    /**
     * 生成指定个数的线段数
     * @param linesNum      线段个数
     * @param rangeLeft     线段左边界
     * @param rangeRight    线段右边界
     * @return
     */
    private static int[][] generateLines(int linesNum, int rangeLeft, int rangeRight){
        int[][] lines = new int[linesNum][2];
        for (int i=0; i<linesNum; i++){
            int randomLeft = RandomUtil.randomInt(rangeLeft, rangeRight);
            int randomRight = RandomUtil.randomInt(rangeLeft, rangeRight);
            while (randomLeft == randomRight){
                randomLeft = RandomUtil.randomInt(rangeLeft, rangeRight);
                randomRight = RandomUtil.randomInt(rangeLeft, rangeRight);
            }
            lines[i][0] = Math.min(randomLeft, randomRight);
            lines[i][1] = Math.max(randomLeft, randomRight);
        }

        return lines;
    }

    /**
     * 线段
     */
    private static class Line{

        private int start;

        private int end;

    }

}
