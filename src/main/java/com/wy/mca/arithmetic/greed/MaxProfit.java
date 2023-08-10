package com.wy.mca.arithmetic.greed;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description 最大利润
 * @Author wangyong01
 * @Date 2022/7/15 5:38 下午
 * @Version 1.0
 */
public class MaxProfit {

    public static void main(String[] args) {
        int w = 2;
        int k = 3;
        int[] cost =   {5,1,2,6};
        int[] profit = {1,3,5,4};
        int maxProfit = getMaxProfit(w, k, cost, profit);
        int recursiveInvoke = violentRecursiveInvoke(w, k, cost, profit);
        System.out.println("maxProfit:" + maxProfit);
        System.out.println("recursiveInvoke:" + recursiveInvoke);
    }

    /**
     * 1 问题描述
     *   cost[i]表示项目i的成本,profit[i]表示项目i的利润，初始资金是w，最多只能做K个项目，计算按照最大利润去做，最后的钱数
     * 2 案例说明
     *   int[] cost =   {5,1,2,6};   
     *   int[] profit = {1,3,5,4};   
     *   int w = 2;   
     *   int k = 3;
     * 3 贪心策略
     *   3.1 先按照花费建立小根堆，并放入所有元素；
     *   3.2 建立大根堆，用于按照利润进行排序；
     *   3.3 初始资金为w=2，可以做的项目为cost[1]和cost[2]，将满足条件的元素{1,3},{2,5}弹出放入大根堆；
     *   3.4 每做一个项目，查看一下是否能从小根堆中弹出元素放入大根堆，直到做够K=3个项目
     *
     * @param w         项目启动资金
     * @param k         项目个数
     * @param cost      每个项目的耗费成本
     * @param profit    每个项目对应的利润
     * @return
     */
    public static int getMaxProfit(int w, int k, int[] cost, int[] profit){
        //1.1 小根堆 按照花费排序
        PriorityQueue<CostProfit> costQueue = new PriorityQueue<>(Comparator.comparing(CostProfit::getCost));
        //1.2 大根堆 按照利润进行排序
        PriorityQueue<CostProfit> profitQueue = new PriorityQueue<>(Comparator.comparing(CostProfit::getProfit).reversed());

        for (int i=0; i<cost.length; i++){
            costQueue.add(new CostProfit(cost[i], profit[i]));
        }

        int sumW = w;
        for (int i=0; i<k; i++){
            //2.1 将小根堆中满足条件的元素放入大根堆
            while (!costQueue.isEmpty() && costQueue.peek().getCost() < sumW){
                profitQueue.add(costQueue.poll());
            }

            if (profitQueue.isEmpty()){
                return sumW;
            }
            //2.2 做一个大根堆中 堆顶的项目
            CostProfit costProfit = profitQueue.poll();
            sumW += costProfit.getProfit();
        }

        return sumW;
    }

    /**
     * 暴力递归
     *
     * @param curW              当前资金
     * @param curK              剩余项目
     * @param restCost          剩余项目的成本
     * @param restProfit        剩余项目的利润
     * @return
     */
    public static int violentRecursiveInvoke(int curW, int curK, int[] restCost, int[] restProfit){
        int sumW = curW;

        for (int i=0; i<curK; i++){
            for (int j=0; j<restCost.length; j++){
                if (restCost[j] < sumW){
                    sumW = Math.max(sumW, violentRecursiveInvoke(curW + restProfit[j], curK - 1, removeIndex(restCost, j), removeIndex(restProfit, j)));
                }
            }
        }

        return sumW;
    }

    private static int[] removeIndex(int[] arr , int index){
        int[] newArr = new int[arr.length-1];
        int curIndex = 0;
        for (int i=0; i<arr.length; i++){
            if (i!=index){
                newArr[curIndex++] = arr[i];
            }
        }

        return newArr;
    }

}

@Data
@AllArgsConstructor
class CostProfit{

    private int cost;

    private int profit;

}
