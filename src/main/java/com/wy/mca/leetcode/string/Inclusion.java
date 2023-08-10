package com.wy.mca.leetcode.string;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

/**
 * 字符串的排列 --> 问题编号:567
 * 1） 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列，即：第一个字符串的排列之一是第二个字符串的子串
 * 2) 案例
 *    a) s1 = "ab" s2 = "eidbaooo"  s2 包含s1
 *    b) s1 = "ab" s2 = "eidboaoo"  s2不包含s1
 * @author wangyong01
 */
public class Inclusion {

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";

        boolean inclusion = checkInclusion(s1, s2);
        System.out.printf(inclusion + "");
    }

    /**
     * 判断s2是否包含s1的字串(滑块问题)
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (StrUtil.isBlank(s1) || StrUtil.isBlank(s2)){
            return false;
        }
        if (s1.length() > s2.length()){
            return false;
        }

        int len1 = s1.length();
        int len2 = s2.length();
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (int i=0; i<len1; i++){
            count1[s1.charAt(i) - 'a'] ++;
            count2[s2.charAt(i) - 'a'] ++;
        }
        if (Arrays.equals(count1,count2)){
            return true;
        }

        for (int i=len1; i<len2 ; i++){
            count2[s2.charAt(i) - 'a'] ++;
            count2[s2.charAt(i - len1) - 'a'] --;
            if (Arrays.equals(count1, count2)){
                return true;
            }
        }
//        for (int i=0; i<len2 - len1; i++){
//            count2[s2.charAt(i + len1) - 'a'] ++;
//            count2[s2.charAt(i) - 'a'] --;
//            if (Arrays.equals(count1, count2)){
//                return true;
//            }
//        }

        return false;
    }

}
