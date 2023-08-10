package com.wy.mca.util;

import java.util.Random;

/**
 * @author wangyong01
 */
public class MyStrUtil {

    /**
     * 生成随机字符串
     * @param maxLen        字符串最大长度
     * @param maxRange      字符边界
     * @return
     */
    public static String generateRandomStr(int maxLen,int maxRange){
        int curLen = (int)(Math.random() * maxLen + 1);
        String randomStr = "";
        for (int i=0; i<curLen; i++){
            char temp = (char)(97 + (int)(Math.random() * maxRange + 1));
            randomStr += temp;
        }
        return randomStr;
    }

    /**
     * 生成只包含数字的随机字符串
     *
     * @param maxLen
     * @return
     */
    public static String generateNumberStr(int maxLen){
        String chars = "1234567890";
        StringBuffer sb = new StringBuffer();
        Random random=new Random();
        for (int i=0; i<maxLen; i++){
            int index = random.nextInt(10);
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

}
