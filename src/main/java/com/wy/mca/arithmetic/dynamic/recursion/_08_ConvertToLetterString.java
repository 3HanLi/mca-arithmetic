package com.wy.mca.arithmetic.dynamic.recursion;

/**
 * 字符串转数字
 *
 * 1）问题描述：规定1-26分别和A-Z对应，给定一个字符串，查看有多少种转换结果
 *
 * 2）举例说明：
 *    2.1 111可以转换为AAA，也可以转换为KA和AK
 *    2.2 105只能转换为JE
 *    2.3 100无法转换
 *
 * 3）暴力递归
 *    3.1 base case
 *        a) 当下标越界的时候，说明找到了一种解决方案，否则一定会在中间的时候就终止
 *        b) 当前元素是0的时候，需要到下一个字符做决定，因为0无法单独转化
 *    3.2 其他情况分析
 *        a) 去i+1位置看看i能否单独转换
 *        b) 去i+2位置查看i和i+1的组合是否能够转换
 *
 * 4）暴力递归 -> 动态规划（缓存表）
 *
 * 5）暴力递归 -> 动态规划（位置依赖）
 *    5.1 画出递归调用，查看是否有重复解
 *                  vR(0)
 *              /        \
 *          vR(1)        vR(2)
 *          /   \       /    \
 *         vR(2) vR(3) vR(3) vR(4)
 *         补充：查看递归调用，其实就是将递归函数func(i)补全，从变量进行展开，查看是否有重复值
 *    5.2 根据变量范围定义缓存表，根据入参返回缓存表指定位置的值
 *    5.3 分析位置依赖关系，约束填充表格的范围和顺序，将暴力递归改为动态规划
 */
public class _08_ConvertToLetterString {

    public static void main(String[] args) {
        String str = "105";
        System.out.println(violentInvoke(str));

        System.out.println(dp(str));
    }

    private static int violentInvoke(String str){
        if (null == str || str.length() == 0){
            return 0;
        }
        return violentRecurse(str.toCharArray(), 0);
    }

    private static int violentRecurse(char[] chars, int i){
        if (i == chars.length){
            return 1;
        }
        if (chars[i] == '0'){
            return 0;
        }
        //去i+1位置看看i能否单独转换
        int p1 = violentRecurse(chars, i+1);
        //去i+2位置看看i和i+1的组合，是否能够转换
        if ((i+1 < chars.length) && ((chars[i] - '0') * 10 + chars[i+1] - '0' < 27)){
            p1 += violentRecurse(chars, i+2);
        }
        return p1;
    }

    private static int dp(String str){
        char[] chars = str.toCharArray();
        int N = chars.length;
        //1 根据变量i取值范围定义缓存表
        int[] dp = new int[N+1];
        dp[N] = 1;

        for (int i=N-1; i>=0; i--){
//            int p1 = 0;
//            if (chars[i] == '0'){
//                p1 = 0;
//            }else {
//                p1 = dp[i+1];
//                if ((i+1 < chars.length) && ((chars[i] - '0') * 10 + chars[i+1] - '0' < 27)){
//                    p1 += dp[i+2];
//                }
//            }

            //等同如下
            if (chars[i] != '0'){
                int p1 = dp[i+1];
                if ((i+1 < chars.length) && ((chars[i] - '0') * 10 + chars[i+1] - '0' < 27)){
                    p1 += dp[i+2];
                }
                dp[i] = p1;
            }
        }

        //2
        return dp[0];
    }
}
