package com.wy.mca.leetcode.string;

/**
 * 最长回文串
 * 1) 给你一个字符串 s，找到 s 中最长的回文子串
 * 2) 案例
 *    输入：s = "babad"
 *    输出："bab"
 *    解释："aba" 同样是符合题意的答案
 * @author wangyong01
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String src = "babad";
        String palindrome = longestPalindrome(src);
        System.out.println(palindrome);
    }

    public static String longestPalindrome(String src) {
        String longest = "";
        for (int i=0; i<src.length(); i++){
            for (int j=i; j<src.length(); j++){
                if (j - i >= longest.length()){
                    String substring = src.substring(i, j + 1);
                    if (isHuiWen(substring) && longest.length() <= substring.length()){
                        longest = substring;
                    }
                }
            }
        }
        return longest;
    }

    /**
     * 判断是否是回文
     * @param src
     * @return
     */
    public static boolean isHuiWen(String src){
        for (int i=0; i<src.length(); i++){
            if (src.charAt(i) != src.charAt(src.length() - i - 1)){
                return false;
            }
        }
        return true;
    }

}
