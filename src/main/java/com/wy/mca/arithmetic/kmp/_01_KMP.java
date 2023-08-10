package com.wy.mca.arithmetic.kmp;

import org.junit.Test;

/**
 * 1、问题描述：KMP用于解决字符串匹配问题
 * 2、案例说明：提供一个函数f(s1,s2)，如果s1包含s2，返回首次匹配到的下标，否则返回-1
 * 3、解题思路：
 *    3.1 生成字符串s2的位置信息，即：next数组
 *    3.2 比较字符串s1[x] != s2[y]时，需要根据y=next[y]跳转到下一个要匹配的字符下标
 * 4、关于时间复杂度的分析
 *    4.1 indexOf(s1,s2)：使用(x-y)分析时间复杂度，原因：y可能变大也可能变小，不好分析整体的趋势
 *    4.2 因而使用x-y的趋势进行分析
 *        a) 2.1中 x++, y++, x-y趋势不变
 *        b) 2.2中 y=next[y]会变小，x-y增长
 *        c) 2.3中，x++，x-y增长
 *
 *        通过分析(x-y)发现整体趋势是不回退的，因而时间复杂度只和外层的while循环有关，也就是O(N)
 *
 * @Description KMP算法详解
 * @Author wangyong01
 * @Date 2023/5/12 2:52 PM
 * @Version 1.0
 */
public class _01_KMP {

    @Test
    public void testIndexOf(){
        String s1 = "abcask";
        String s2 = "cas";
        int indexOf = indexOf(s1, s2);
        System.out.println("IndexOf:" + indexOf);
    }

    public int indexOf(String s1, String s2){
        if (null == s1 || null == s2 || s1.length() < s2.length()){
            return -1;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        //1.1 next[i]：next数组，记录每个字符的位置信息(记录的是长度，或者说是最大前缀子串最大下标 + 1)
        int[] next = next(s2);

        //1.2 字符串比较，y的前缀串是0.. 后缀串是 j.. | 当S1[x]!=S2[y]时，将[0..]向右平移和S1[j'..]对齐，比较S1[x]和S2[z]
        //S1[i..z'...j'..x]，比如：aabaat
        //S2[0..z ...j..y]，比如： aabaab -> [-1, 0, 1, 0, 1, 2]

        //s1当前字符
        int x = 0;
        //s2当前字符
        int y = 0;
        while (x < s1.length() && y < s2.length()){
            //2.1 当前字符相等时，继续比较下一个字符
            if (c1[x] == c2[y]){
                x++;
                y++;
            } else {
                //2.2 S2当前字符y的位置信息>=0，根据next[y]找到要比较的字符z
                if (y >= 0){
                    y = next[y];
                } else {
                    //2.3 当y=-1时，也就是S1[x]位置和S2[0]位置都不匹配，那么直接比较S1[x+1]位置
                    x++;
                }
            }
        }

        //3.1 如果y越界了说明完全匹配，那么x-y就是字符串匹配成功的开始下标
        //3.2 如果x越界了说明没有匹配到返回-1
        return y == s2.length() ? x - y : -1;
    }

    public int[] next(String s2){
        int[] next = new int[s2.length()];
        if (s2.length() == 1){
            return new int[]{-1};
        }
        char[] c2 = s2.toCharArray();
        //1.1 next数组前两个位置固定值为[-1,0]
        next[0] = -1;
        next[1] = 0;
        //2 s2 = (acd bst acd) {txe} (acd bst acd) {b   k}
        //                      ?                   i-1 i
        //2.1 next[i]位置更新：
        //    1) 如果s2[?]=s2[i-1]的话，那么s[i] = s[i-1] + 1，即：s[i] = 10
        //    2) 如果s2[?]!=s2[i-1]，那么需要找到?位置前缀串(acd)的下一个字符(b)是否和[i-1]位置相等，即：s[i] = 4
        int i= 2;
        //2.2 cn表示 ? 的下标，也就是(i-1)位置前缀子串的下一个坐标，我们需要拿该下标的字符和(i-1)位置的字符比较；
        // a c s x
        //    cn初始值为0，原因解释：i是从下标2开始的，(i-1)位置没有前缀串
        int cn = 0;
        while (i < s2.length()){
            //3.1 如果s[?] = s[i-1]
            if (c2[i-1] == c2[cn]){
                //则next[i] = cn+1
                next[i] = cn+1;
                //接下来更新i+1位置，且cn也需要向右移动
                i++;
                cn++;
            } else {
                //3.2 如果s[?] != s[i-1]，需要找到cn前缀子串的下一个位置
                if (cn > 0){
                    cn = next[cn];
                } else {
                    //3.3 如果cn<=0，表示没有匹配到，则next[i=1]，然后继续去更新i+1位置的值
                    next[i] = 0;
                    i++;
                }
            }
        }

        return next;
    }
}
