package com.wy.mca.arithmetic.dynamic.recursion;

/**
 * @Description 最长回文子序列
 * 1、问题描述：给定一个字符串str，返回这个字符串的最长回文子序列长度
 *    1.1 子序列：可以不连续，但是顺序不能错乱；
 *    1.2 子串：必须是连续的
 * 2、举例说明：
 *    str=“a12b3c43def2ghiikpm，最长回文子序列是“1234321或者“123c321”，返回长度7
 * 3、解题思路
 *    3.1 字符串的最长回文子序列就是：字符串a和他的逆序字符串b的最长公共子序列，就是最长回文子序列
 *    3.2 通过范围尝试模型解决最长回文子序列，也就是定义函数
 *        f(chars[], L, R)：在L~R范围做尝试
 * 4、暴力递归
 *    4.1 base case
 *        a) 当L=R，最长回文子序列长度为1
 *        b) 当L+1=R，也就是只有2个字符时，最长回文子序列长度为1或者2，比如：ab aa
 *    4.2 其他情况分析
 *        a) 既不以L开头，也不以R结尾，如：a12321b
 *        b) 以L开头，不以R结尾，如：12321b
 *        c) 不以L开头，以R结尾，如：a123b321
 *        d) 既以L开头，也以R结尾，如：1ab23cd21
 * 5、动态规划
 *
 * @Author wangyong01
 * @Date 2023/4/7 8:56 PM
 * @Version 1.0
 */
public class _11_LongestPalindromeSubSequence {

    public static void main(String[] args) {
        //最长回文子序列为:1s233aa2s1
        String s1 = "ak1bsc2y3aa3rru2is1q";
        char[] chars = s1.toCharArray();
        int longestPalindrome = longestPalindromeRangeVR(chars, 0, chars.length - 1);

        System.out.println("longestPalindrome:" + longestPalindrome);

        int palindromeRangeDP = longestPalindromeRangeDP(chars);
        System.out.println("palindromeRangeDP:" + palindromeRangeDP);
    }

    /**
     * 最长回文子序列（暴力递归）：在L-R范围上进行尝试
     * @param chars
     * @param L
     * @param R
     * @return
     */
    public static int longestPalindromeRangeVR(char[] chars, int L, int R){
        //1.1 base case：当L=R，最长回文子序列长度为1
        if (L==R){
            return 1;
        }
        //1.2 base case：当L+1=R，也就是只有2个字符时，最长回文子序列长度为1或者2，比如：ab aa
        if (L+1==R){
            return chars[L] == chars[R] ? 2 : 1;
        }
        //2.1 既不以L开头，也不以R结尾，如：a12321b
        int p1 = longestPalindromeRangeVR(chars, L+1, R-1);
        //2.2 以L开头，不以R结尾，如：12321b
        int p2 = longestPalindromeRangeVR(chars, L, R-1);
        //2.3 不以L开头，以R结尾，如：a123b321
        int p3 = longestPalindromeRangeVR(chars, L+1, R);
        //2.4 既以L开头，也以R结尾，如：1ab23cd21
        //应该这么调用，但是这个会导致死递归，需要提前进行拆解出来  int p4 = longestPalindromeRangeDP(L, R);
        int p4 = 0;
        if (chars[L] == chars[R]){
            p4 = 2 + longestPalindromeRangeVR(chars, L+1, R-1);
        }

        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    /**
     * 范围尝试模型
     * @param chars
     * @return
     */
    public static int longestPalindromeRangeDP(char[] chars){
        int N = chars.length;
        int[][] dp = new int[N][N];
        for (int i=0; i<N; i++){
            dp[i][i] = 1;
        }

        for (int i=0; i<N-1; i++){
            dp[i][i+1] = chars[i] == chars[i+1] ? 2 : 1;
        }

        for (int L=N-3; L>=0; L--){
            for (int R=L+2; R<N; R++){
                int p1 = dp[L+1] [R-1];
                int p2 = dp[L][R-1];
                int p3 = dp[L+1][R];
                int p4 = 0;
                if (chars[L] == chars[R]){
                    p4 = 2 + dp[L+1][R-1];
                }

                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }

        return dp[0][N-1];
    }

}
