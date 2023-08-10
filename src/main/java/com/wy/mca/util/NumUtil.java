package com.wy.mca.util;

/**
 * @author wangyong01
 */
public class NumUtil {

    /**
     * 生成随机数字
     * @param size
     * @param range
     * @return
     */
    public static int[] generate(int size, int range){
        int[] arr = new int[size];
        for (int i=0; i<size; i++){
            arr[i] = (int)(Math.random() * range + 1);
        }

        return arr;
    }

    /**
     * 计算数字的长度
     * @param num
     * @return
     */
    public static int calDigit(int num){
        int res = 0;
        while (num != 0){
            res++;
            num = num / 10;
        }
        return res;
    }

    /**
     * 计算数字长度
     * @param num
     * @return
     */
    public static int getNumLen(int num){
        return String.valueOf(num).length();
    }

    /**
     * 获取数字digit位的值 digit=1 代表个位；digit=2，代表十位；digit代表百位...
     * @param num
     * @param digit
     * @return
     */
    public static int getNum(int num, int digit){
        return (int) (num / Math.pow(10, digit-1) ) % 10;
    }
}
