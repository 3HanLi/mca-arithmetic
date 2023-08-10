package com.wy.mca.arithmetic.dynamic.recursion;

/**
 * 最长公共子序列
 *
 * 1）问题描述：给定两个字符串分别为str1，str2，计算他们最长公共子序列的长度
 *
 * 2）举例说明
 *    2.1 str1=a12bc345def，str2=mnp123qrs45z，最长公共子序列是12345
 *    2.2 那么最长公共子序列的长度就是5
 *
 * 3）解题思路：
 *    3.1 其实就是计算str1[0-i]和str2[0-j]上的最长公共子序列的长度
 *
 * 4）暴力递归
 *   4.1 base case
 *       a) i=0,j=0，那么str1[0]=str2[0]时，最长公共子序列长度为1
 *          chars1[i] == chars2[j] ? 1 : 0
 *       b) i=0,j!=0，那么
 *          b.1 str[0] = str[j]，最长公共子序列长度为1；
 *          b.2 否则：继续递归，判断str1[0]是否等于str2[j]
 *              chars1[i] == chars2[j] ? 1 : violentRecurse(chars1, chars2, i, j-1);
 *       c) j=0,i!=0，那么...
 *   4.2 其他情况分析，也就是 i和j都不为0
 *       a) 完全不考虑i，可能考虑j
 *          int p1 = violentRecurse(chars1, chars2, i-1, j);
 *       b) 完全不考虑j，可能考虑i
 *          int p2 = violentRecurse(chars1, chars2, i, j-1);
 *       c) i和j都考虑
 *          int p3 = chars1[i] == chars2[j] ? (1 + violentRecurse(chars1, chars2, i-1, j-1)) : 0;
 *       结果：取上述结果的最大值即可
 *
 * 5）动态规划
 *    5.1 分析是否有重复调用，决定是否能改动态规划，其实就是看多次递归调用是否会出现入参相同的情况
 *    5.2 分析变量变化范围，定义缓存表，根据入参返回缓存表指定位置的值
 *    5.3 分析位置依赖关系，约束填充表格的方向和范围（也就是for循环），然后拷贝暴力递归代码，将递归调用改为从缓存表取值
 *
 * 6) 总结：
 *    6.1 手写动态规划主要就是三步；
 *        1）暴力递归；
 *        2）根据变量定义缓存表，根据传参返回结果；
 *        3）分析位置依赖填表格（将暴力递归改成从表格中取值）
 *    6.2 该递归方式需要字符串从右向左进行推进，也叫做样本对应模型
 *
 */
public class _10_LongestCommonSubSequence {

    public static void main(String[] args) {
        String str1 = "a12bc345def";
        String str2 = "mnp123qrs45z";
        int violentInvoke = violentInvoke(str1, str2);
        System.out.println("暴力递归==>" + violentInvoke);

        int dp = dp(str1, str2);
        System.out.println("动态规划==>" + dp);
    }

    /**
     * 暴力递归调用函数
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int violentInvoke(String str1, String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int N = chars1.length;
        int M = str2.length() - 1;

        return violentRecurse(chars1, chars2, N -1, M-1);
    }

    /**
     * 暴力递归
     *
     * @param chars1
     * @param chars2
     * @param i
     * @param j
     * @return
     */
    private static int violentRecurse(char[] chars1, char[] chars2, int i, int j){
        //1.1 base case i=0, j=0;
        if (i == 0 && j == 0){
            return chars1[i] == chars2[j] ? 1 : 0;
        }
        //1.2 base case i=0, j!=0
        else if (i == 0){
            return chars1[i] == chars2[j] ? 1 : violentRecurse(chars1, chars2, i, j-1);
        }
        //1.3 base case j=0, i!=0
        else if (j == 0){
            return chars1[i] == chars2[j] ? 1 : violentRecurse(chars1, chars2, i-1, j);
        }
        //2 i!=0, j!=0,
        else {
            //2.1 完全不考虑i，可能考虑j
            int p1 = violentRecurse(chars1, chars2, i-1, j);
            //2.2 完全不考虑j，可能考虑i
            int p2 = violentRecurse(chars1, chars2, i, j-1);
            //2.3 i和j都考虑
            //2.3.1 理论上应该是直接调用violentRecurse(chars1, chars2, i, j)，但是这样会导致死递归
            //2.3.2 因为这里提前判断chars[i]和chars[j]，这样我们的递归才能继续向下执行
            int p3 = chars1[i] == chars2[j] ? (1 + violentRecurse(chars1, chars2, i-1, j-1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    /**
     * 动态规划
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int dp(String str1, String str2){
        //1 暴力递归主函数
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        //行
        int N = chars1.length;
        //列
        int M = chars2.length;

        //2
        int[][] dp = new int[N][chars2.length];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;

        for (int j=1; j<M; j++){
            dp[0][j] = chars1[0] == chars2[j] ?  1 : dp[0][j-1];
        }

        for (int i=1; i<N; i++){
            dp[i][0] = chars1[i] == chars2[0] ? 1 : dp[i-1][0];
        }

        for (int i=1; i<N; i++){
            for (int j=1; j<M; j++){
                int p1 = dp[i-1][j];
                int p2 = dp[i][j-1];
                int p3 = chars1[i] == chars2[j] ? (1 + dp[i-1][j-1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2,p3));
            }
        }

        return dp[N -1][chars2.length-1];
    }

}
