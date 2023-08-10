package com.wy.mca.leetcode.sort.heap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wy.mca.common.Customer;
import com.wy.mca.structure.heap.HeapGreater;
import com.wy.mca.util.ArrUtil;
import com.wy.mca.util.PrintUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 获取任一时刻得奖区的用户信息
 *
 * 问题描述：
 * 1) 给定数组arr和op，arr和op长度相等，arr[i]表示客户id，op[i]表示用户行为，举例说明
 *    1.1 arr = {5 ,3 ,1 ,1 ,3 ,5 ,5 ,5}
 *    1.2 op =  {T ,T ,T ,T ,F ,F ,T ,T}	T表示买东西，F表示退货
 *    1.3 上述行为依次表示：
 *        a) 客户5在0号时间点购买了1件商品；
 *        b) 客户3在1号时间点购买了一件商品；
 *        c) 时间点指的是数组下标
 * 2) 要求：返回每一个时间点发生时，买东西的前K名
 *
 * 举例说明：假如现在获取前2名的客户，购买行为如下
 * 1) 5号客户在0号时间点购买商品，此时得奖区为空，直接进入   ==>得奖区{5, 1, 0}
 * 2) 3号客户在1号时间点购买商品，此时得奖区不满，直接进入   ==>得奖区{5, 1, 0},{3, 1, 1}
 * 3) 1号客户在2号时间点购买商品，此时得奖区已满，直接进入   ==>候选区{1,1,2}
 * 4) 1号客户在3号时间点购买商品，此时得奖区已满，直接进入   ==>候选区区{1,2,2} 进入的时间点是2，此时购买2件商品
 *    4.1 如果候选区最大购买数量 > 得奖区最小购买数量，则替换；替换的时候如果得奖区存在购买数量一致的客户，优先替换时间点小的。
 *    4.2 如果候选区最大购买数量 < 得奖区最小购买数量，不替换；替换的时候如果候选区存在购买数量一致的客户，优先选取时间点早的替换得奖区。
 *    4.3 讲过上述转换后
 *        ==>得奖区{3,1,1},{1,2,2}
 *        ==>候选区{5,1,0}
 *
 * 解题思路
 * 1) 每一个事件发生的时候，都需要获取得奖区中的名单
 * 2) 如果用户购买的数量为0却发生退货，认为事件无效
 * 3) 如果得奖区不满的话，新购买的客户直接进入得奖区，如果得奖区满了，新来的客户不足以进入得奖区，则进入候选区
 * 4) 如果候选区中购买数量最多的用户足以进入得奖区（数量大于得奖区最小值），如果得奖区存在多个最小元素，优先替换时间早的
 * 5) 候选区和得奖区时间维护（时间可以认为就是数组的下标，且只会出现在得奖区和候选区中一侧）
 *
 * 实现方式
 * 1) 暴力实现
 * 2) 暴力实现 -> 加强堆
 *    2.1 时间复杂度：N * (logN + logK + K)
 *    2.2 参数说明
 *        a) N指的是数组长度，或者说是事件发生的次数
 *        b) logN指的是候选区小根堆每次调整的时间复杂度；logK指的是得奖区小根堆每次调整的时间复杂度；K指的是每次时间需要获取所有得奖区元素的复杂度
 *
 * @author wangyong01
 */
public class TopKWinners {

    public static void main(String[] args) {
        testViolent();
        System.out.println("=================");
        testHeap();
    }

    private static void testViolent(){
        int len = 20;
        int[] arr = ArrUtil.generatePositiveRandomArray(len, 5);
        PrintUtil.printOneDimensionArr(arr);

        Boolean[] op = ArrUtil.generateRandomBoolean(len);
        printBoolean(op);

        int k = 3;
        List<List<Integer>> violentTopKWinners = getViolentTopKWinners(arr, op, k);
        System.out.println("Print:" + violentTopKWinners);
    }

    private static void testHeap(){
        int len = 10;
        int[] arr = ArrUtil.generatePositiveRandomArray(len, 5);
        PrintUtil.printOneDimensionArr(arr);

        Boolean[] op = ArrUtil.generateRandomBoolean(len);
        printBoolean(op);

        int k = 3;
        List<List<Integer>> violentTopKWinners = getHeapTopKWinners(arr, op, k);
        System.out.println("Print:" + violentTopKWinners);
    }

    private static void printBoolean(Boolean[] arr){
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i] ? "t\t" : "f\t");
        }
        System.out.println();
    }

    /**
     * 通过加强堆实现
     *
     * @param arr
     * @param op
     * @param k
     * @return
     */
    public static List<List<Integer>> getHeapTopKWinners(int[] arr, Boolean[] op, int k){
        //购买东西客户Id->实体关系
        Map<Integer, Customer> customerMap = Maps.newHashMap();
        //候选区
        HeapGreater<Customer> canHeap = new HeapGreater<>(new CanComparator());
        //得奖区
        HeapGreater<Customer> winHeap = new HeapGreater<>(new WinComparator());
        //前K个获奖人
        List<List<Integer>> topKList = Lists.newArrayList();

        for (int i=0; i<arr.length; i++){
            int customerId = arr[i];
            boolean buy = op[i];
            //1.1 之前没有买过，此时退货，事件无效
            if (!customerMap.containsKey(customerId) && !buy){
                topKList.add(getWinnerList(winHeap));
                continue;
            }
            //1.2 之前买过，此时继续买或者退货
            if (customerMap.containsKey(customerId)){
                Customer customer = customerMap.get(customerId);
                if (buy){
                    customer.buy++;
                }else {
                    customer.buy--;
                    if (customer.buy == 0){
                        customerMap.remove(customerId);
                        if (canHeap.contains(customer)){
                            canHeap.remove(customer);
                        }
                        if (winHeap.contains(customer)){
                            winHeap.remove(customer);
                        }
                    }
                }
                if (canHeap.contains(customer)){
                    canHeap.resign(customer);
                }
                if (winHeap.contains(customer)){
                    winHeap.resign(customer);
                }
            }else {
                //1.3 之前没有买过，此时购买
                if (buy){
                    Customer customer = new Customer(customerId, 1, i);
                    customerMap.put(customerId, customer);
                    //2 尝试将候选区元素移入得奖区
                    //2.1 得奖区不满，直接加入得奖区
                    if (winHeap.getHeapSize() < k){
                        winHeap.push(customer);
                    }else {
                        canHeap.push(customer);
                    }
                }
            }
            if (!canHeap.isEmpty()){
                Customer canCustomer = canHeap.pop();
                Customer winCustomer = winHeap.pop();
                if (canCustomer.getBuy() > winCustomer.getBuy()){
                    canCustomer.setEnterTime(i);
                    winCustomer.setEnterTime(i);
                    canHeap.push(winCustomer);
                    winHeap.push(canCustomer);
                }else {
                    canHeap.push(canCustomer);
                    winHeap.push(winCustomer);
                }
            }
            topKList.add(getWinnerList(winHeap));
        }

        return topKList;
    }

    /**
     * 暴力破解
     *
     * @param arr   用户Id
     * @param op    用户购买行为：true表示购买，false表示退货
     * @param k     得奖区的最大数量
     * @return      每一时刻得奖区的用户id集合
     */
    public static List<List<Integer>> getViolentTopKWinners(int[] arr, Boolean[] op, int k){
        //购买东西客户Id->实体关系
        Map<Integer, Customer> customerMap = Maps.newHashMap();
        //候选区
        List<Customer> canList = Lists.newArrayList();
        //得奖区
        List<Customer> winnerList = Lists.newArrayList();
        //前K个获奖人
        List<List<Integer>> topKList = Lists.newArrayList();

        for (int i=0; i<arr.length; i++){
            int customerId = arr[i];
            boolean buy = op[i];
            //1.1 之前没有买过，此时退货，事件无效
            if (!customerMap.containsKey(customerId) && !buy){
                topKList.add(getWinnerList(winnerList));
                continue;
            }
            //1.2 之前买过，此时继续买或者退货
            if (customerMap.containsKey(customerId)){
                Customer customer = customerMap.get(customerId);
                if (buy){
                    customer.buy++;
                }else {
                    customer.buy--;
                    if (customer.buy == 0){
                        customerMap.remove(customerId);
                        canList.remove(customer);
                        winnerList.remove(customer);
                    }
                }
            }else {
                //1.3 之前没有买过，此时购买
                if (buy){
                    Customer customer = new Customer(customerId, 1, i);
                    customerMap.put(customerId, customer);
                    //2 尝试将候选区元素移入得奖区
                    //2.1 得奖区不满，直接加入得奖区
                    if (winnerList.size() < k){
                        winnerList.add(customer);
                    }else {
                        canList.add(customer);
                    }
                }
            }
            //3 对候选区和得奖区进行排序
            //3.1 候选区：优先按照购买数量排序（倒），在按照进入时间排序（正）
            canList.sort(new CanComparator());
            //3.2 得奖区：优先按照购买数量排序（正），在按照进入时间排序（正）
            winnerList.sort(new WinComparator());
            if (!canList.isEmpty()){
                Customer canCustomer = canList.get(0);
                Customer winCustomer = winnerList.get(0);
                if (canCustomer.getBuy() > winCustomer.getBuy()){
                    canCustomer.setEnterTime(i);
                    winCustomer.setEnterTime(i);
                    winnerList.set(0, canCustomer);
                    canList.set(0, winCustomer);
                }
            }
            topKList.add(getWinnerList(winnerList));
        }

        return topKList;
    }

    private static List<Integer> getWinnerList(List<Customer> winnerList){
        return winnerList.stream().map(Customer::getId).collect(Collectors.toList());
    }

    private static List<Integer> getWinnerList(HeapGreater<Customer> heapGreater){
        List<Customer> allElements = heapGreater.getAllElements();
        return allElements.stream().map(Customer::getId).collect(Collectors.toList());
    }

}

class WinComparator implements Comparator<Customer>{

    @Override
    public int compare(Customer x, Customer y) {
        return x.getBuy() == y.getBuy() ? x.getEnterTime() - y.getEnterTime() : x.getBuy() - y.getBuy();
    }
}

class CanComparator implements Comparator<Customer>{

    @Override
    public int compare(Customer x, Customer y) {
        return x.getBuy() == y.getBuy() ? x.getEnterTime() - y.getEnterTime() : y.getBuy() - x.getBuy();
    }
}




