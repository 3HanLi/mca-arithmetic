package com.wy.mca.util;

import java.util.Random;

public class ArrUtil {

    /**
     * 生成指定长度的随机数组
     * @param length
     * @return
     */
    public static int[] generateRandomArray(int length, int range){
        int[] arr = new int[length];
        for (int i=0; i<arr.length; i++){
            arr[i] = RandUtil.generateRandomNum(range);
        }
        return arr;
    }

    public static int generateRandomRange(int rangeLeft, int rangeRight){
        int len = rangeRight -  rangeLeft +1;
        int[] arr = new int[len];
        for (int i=rangeLeft, j=0; i<=rangeLeft; i++, j++){
            arr[j] = i;
        }
        return arr[new Random().nextInt(len)];
    }

    public static Boolean[] generateRandomBoolean(int len){
        Boolean[] op = new Boolean[len];
        for (int i=0; i<len; i++){
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return op;
    }

    public static char[] generateRandomSmallChars(int len, boolean smallChars){
        String chars = smallChars ? "abcdefghijklmnopqrstuvwxyz" : "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] op = new char[len];
        Random random=new Random();
        for (int i=0; i<len; i++){
            int index = random.nextInt(26);
            op[i] = chars.charAt(index);
        }
        return op;
    }

    public static String[] generateRandomSmallStrings(int arrLen, int strLen){
        String[] strArr = new String[arrLen];
        Random random = new Random();
        for (int i=0; i<arrLen; i++){
            int curStrLen = random.nextInt(strLen);
            while (curStrLen == 0){
                curStrLen = random.nextInt(strLen);
            }
            char[] chars = generateRandomSmallChars(curStrLen, true);
            strArr[i] = new String(chars);
        }
        return strArr;
    }


    /**
     * 生成
     * @param length
     * @param range
     * @return
     */
    public static int[] generatePositiveRandomArray(int length, int range){
        int[] arr = new int[length];
        for (int i=0; i<arr.length; i++){
            arr[i] = RandUtil.generatePositiveRandomNum(range);
        }
        return arr;
    }

    /**
     * 数据交换 前提是 i!=j
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j){
        if (i ==j){
            return;
        }
        //a = a^b
        arr[i] = arr[i] ^ arr[j];
        //b = a^b
        arr[j] = arr[i] ^ arr[j];
        //a = a^b
        arr[i] = arr[i] ^ arr[j];
    }

}
