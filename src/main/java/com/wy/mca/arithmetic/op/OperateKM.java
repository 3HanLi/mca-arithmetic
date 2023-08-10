package com.wy.mca.arithmetic.op;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 亦或操作符和对数器练习
 *
 * @author wangyong01
 */
public class OperateKM {

    public static void main(String[] args) {
        int times = 1;
        int kinds = 5;
        int range = 1000;
        int k = 3;
        int m = 4;
        validate(times, kinds, range, k, m);
    }

    /**
     * 验证次数
     * @param times
     */
    private static void validate(int times, int kinds, int range, int k, int m){
        for (int i=0; i<times; i++){
            int[] arr = generateRandomArr(kinds, range, k, m);
            int findNumber01 = findKTimesNumber(arr, k, m);
            int findNumber02 = findKTimesNumberMap(arr, k, m);
            if (findNumber01 != findNumber02){
                System.out.println("Error");
            }
        }
    }

    /**
     * 一个数组中arr有一个数出现了K次，其他数出现了M次	1 < K <M，计算出现K次的数k
     * @param arr
     * @param k
     * @param m
     */
    private static int findKTimesNumber(int[] arr, int k, int m){
        //1 定义数组t，长度为32，每个元素代表数组中元素在该二进制位出现的个数
        int[] t = new int[32];
        //2 想办法将arr中元素的二进制位更新到t中
        for (int num : arr){
            for (int i=0; i<t.length; i++){
                if (((num >> i) & 1 ) != 0){
                    t[i] ++;
                }
            }
        }
        //3 t中每个元素%m 如果不为0，说明该位置的1肯定是k的二进制位在这个地方的和
        //3.1 定义变量answer，和该位置做^操作，保留最后的1
        int answer = 0;
        for (int i=0; i<t.length; i++){
            if (t[i] % m != 0){
                answer |= 1 << i;
            }
        }
        //3.2 想办法让1还原
        return answer;
    }

    /**
     * 对数器：使用Map的方式获取出现K次的数
     * @param arr
     * @param k
     * @param m
     * @return
     */
    private static int findKTimesNumberMap(int[] arr, int k, int m){
        Map<Integer,Integer> numCountMap = Maps.newHashMap();
        for (int num : arr){
            if (numCountMap.containsKey(num)){
                Integer count = numCountMap.get(num);
                numCountMap.put(num, count+1);
            }else {
                numCountMap.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()){
            if (entry.getValue() == k){
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * 生成随机数组
     * @param kinds 数的种类
     * @param range 数的范围边界
     * @param k     k次
     * @param m     m次
     * @return
     */
    private static int[] generateRandomArr(int kinds, int range, int k, int m){
        int length = k + m *(kinds - 1);
        int[] arr = new int[length];
        int kNum = generateRandomNum(range);
        int startIndex = 0;
        for (startIndex=0; startIndex<k; startIndex++){
            arr[startIndex] = kNum;
        }

        for (int kind=0; kind<kinds-1; kind++){
            int mNum = generateRandomNum(range);
            while (kNum == mNum){
                mNum = generateRandomNum(range);
            }
            int mEndIndex = startIndex + m;
            for (; startIndex < mEndIndex; startIndex++){
                arr[startIndex] = mNum;
            }
        }

        for (int i=0; i<arr.length; i++){
            int j = (int) (Math.random() * length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    private static int generateRandomNum(int range){
        return (int)(Math.random() * range) - (int)(Math.random() * range);
    }

}
