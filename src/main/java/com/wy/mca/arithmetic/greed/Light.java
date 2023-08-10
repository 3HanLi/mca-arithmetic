package com.wy.mca.arithmetic.greed;

/**
 * @Description 点灯问题
 * @Author wangyong01
 * @Date 2022/7/18 9:18 下午
 * @Version 1.0
 */
public class Light {

    public static void main(String[] args) {
        String chs = "X.X..X....XX";
        int leastLight = getLeastLight(chs);
        System.out.println("leastLight:" + leastLight);


    }

    /**
     * 贪心算法
     *
     * @param str
     * @return
     */
    public static int getLeastLight(String str){
        char[] chars = str.toCharArray();

        int i=0;
        int light = 0;
        while (i < str.length()){
            if (chars[i] == 'X'){
                i++;
            }else {
                light ++;
                if (i+1 == chars.length){
                    break;
                }
                if (chars[i+1] == '.'){
                    i = i+3;
                }else {
                    i = i+2;
                }
            }
        }

        return light;
    }

    /**
     * 暴力递归
     *
     * @param str
     * @return
     */
    public static int violentInvokeRecursive(String str){
        return 0;
    }

    private static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

}
