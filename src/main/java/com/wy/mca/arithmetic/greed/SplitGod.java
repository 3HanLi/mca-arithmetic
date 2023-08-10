package com.wy.mca.arithmetic.greed;

import java.util.PriorityQueue;

/**
 * @Description 切金块问题
 * @Author wangyong01
 * @Date 2022/7/14 6:27 下午
 * @Version 1.0
 */
public class SplitGod {

    public static void main(String[] args) {
        int[] gods = {2,1,7,3,4,2,1};

        int greedMinCost = splitGodGreed(gods);
        System.out.println("greedMinCost:" + greedMinCost);

        int violentRecursiveInvoke = violentRecursiveInvoke(gods);
        System.out.println("violentRecursiveInvoke:" + violentRecursiveInvoke);
    }

//    步骤：       
//    a) 创建一个小根堆，放入所有的元素       
//    b) 每次弹出2个元素，累加后放入小根堆【右图红色的就是累加和，也是切割方案】       
//    c) 直到小根堆元素清空
    /**
     * 1 问题描述
     *   现在有一个金块，长度为n，每切割一次都会花费当前金块长度数量的铜板，计算如何切分代价最小
     *
     * 2 案例说明
     *   2.1 金块长度是60，现在需要切割成三份 arr = {10,20,30} 
     *   2.2 切割方案分析-1 
     *       a) 先切割10，耗费铜板60（金块长度）
     *       b) 在切割20，耗费铜板50（金块长度） 
     *       累计耗费金币：110   
     *   2.3 切割方案分析-2 
     *       a) 先切割30，耗费铜板60（金块长度）
     *       b) 在切割20，耗费铜板30（金块长度） 
     *       累计耗费金币 90
     *
     * 3 贪心策略
     *   3.1 样本数据
     *       arr={2，1，7，3，4，2，1}
     *   3.2 步骤
     *       a) 创建一个小根堆，放入所有的元素
     *       b) 每次弹出2个元素，累加后放入小根堆【右图红色的就是累加和，也是切割方案】
     *       c) 直到小根堆元素清空
     *   3.3 最终的累加和就是需要耗费的最小铜板数量
     * @param gods
     * @return
     */
    public static int splitGodGreed(int[] gods){
        //1.1 先把元素放入小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int god : gods){
            priorityQueue.add(god);
        }

        int sum = 0;
        //1.2 只要小根堆不为空，就弹出2个元素计算累加和并放入小根堆
        while (priorityQueue.size() > 1){
            int curSum = priorityQueue.poll() + priorityQueue.poll();
            priorityQueue.add(curSum);
            sum += curSum;
        }

        //1.3 最终耗费的铜板数量
        return sum ;
    }


    public static int violentRecursiveInvoke(int[] gods){
        return violentRecursive(gods, 0);
    }

    /**
     * 暴力递归
     * @param gods  剩余的金块
     * @param cost  花费的金币
     * @return
     */
    private static int violentRecursive(int[] gods, int cost){
        //1.1 仅剩下一个金块，无需切割
        if (gods.length == 1){
            return cost;
        }
        int minCost = Integer.MAX_VALUE;
        for (int i=0; i<gods.length-1; i++){
            for (int j=i+1; j<gods.length; j++){
                //1.2 耗费的最小金币数量
                minCost = Math.min(minCost, violentRecursive(copyNewGodsWithoutIndex(gods, i, j), cost + gods[i] + gods[j]));
            }
        }

        return minCost;
    }

    private static int[] copyNewGodsWithoutIndex(int[] gods, int i, int j){
        int[] newGods = new int[gods.length - 1];
        int curI = 0;
        for (int k=0; k< gods.length; k++){
            if (k != i && k!= j){
                newGods[curI++] = gods[k];
            }
        }
        newGods[curI] = gods[i] + gods[j];
        return newGods;
    }

}
