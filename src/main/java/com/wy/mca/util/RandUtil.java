package com.wy.mca.util;

/**
 * @author wangyong01
 */
public class RandUtil {

    public static int generateRandomNum(int range){
        return (int)(Math.random() * range) - (int)(Math.random() * range);
    }

    public static int generatePositiveRandomNum(int range){
        return (int)(Math.random() * range);
    }

}
