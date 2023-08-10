package com.wy.mca.arithmetic.dynamic.recursion;

import org.junit.Test;

/**
 * @Description
 * @Author wangyong01
 * @Date 2023/5/8 4:03 PM
 * @Version 1.0
 */
public class _021_SplitNumber {

    @Test
    public void splitNumber(){
        int num = 6;
        //这里传num的原因：
        int splitNumberVR = splitNumberVR(1, num);
        System.out.println("splitNumberVR:" + splitNumberVR);

        int splitNumberDP = splitNumberDP(num);
        System.out.println("splitNumberDP:" + splitNumberDP);

        int splitNumberDP2 = splitNumberDP2(num);
        System.out.println("splitNumberDP2:" + splitNumberDP2);
    }

    /**
     * 暴力递归
     * @param pre   前面选择的数字
     * @param rest  剩下的数字
     * @return
     */
    public int splitNumberVR(int pre, int rest){
        if (rest == 0){
            return 1;
        }
        if (pre == rest){
            return 1;
        }
        if (pre > rest){
            return 0;
        }
        int ways = 0;
        for (int i=pre; i<=rest; i++){
            //注意：这里只能确定当前单元格依赖于左侧的某个单元格，但是不能确定是依赖于左上还是左下，需要分析
            ways += splitNumberVR(i, rest-i);
        }

        return ways;
    }

    public int splitNumberDP(int num){
        int[][] dp = new int[num+1][num+1];
        for (int i=1; i<=num; i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        //确定表格填充方向
        for (int pre=num; pre>0; pre--){
            for (int rest=1; rest<=num; rest++){
                int ways = 0;
                for (int i=pre; i<=rest; i++){
                    ways += dp[i][rest-i];
                }
                dp[pre][rest] = ways;
            }
        }

        return dp[1][num];
    }

    public int splitNumberDP2(int num){
        int[][] dp = new int[num+1][num+1];
        for (int i=1; i<=num; i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int pre=num-1; pre>0; pre--){
            for (int rest=1; rest<=num; rest++){
                dp[pre][rest] = dp[pre+1][rest];
                if (rest-pre >= 0){
                    dp[pre][rest] += dp[pre][rest-pre];
                }
            }
        }

        return dp[1][num];
    }

}
