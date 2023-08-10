package com.wy.mca.arithmetic.dynamic.recursion;

import org.junit.Test;

/**
 * @Description 砍怪兽
 * @Author wangyong01
 * @Date 2023/4/19 9:27 PM
 * @Version 1.0
 */
public class _19_KillMonster {

    @Test
    public void killMonster(){
        //怪兽有N滴血
        int N = 10;
        //一共砍K刀
        int K = 10;
        //每次砍0-M滴血
        int M = 10;

        long killNumbers = killNumbers(M, K, N);
        double all = Math.pow(M+1, K);
        System.out.println("Kills ratio:" + (killNumbers / all));

        long dp1 = dp1(M, K, N);
        System.out.println("dp-Kills ratio:" + (dp1 / all));

        long dp2 = dp2(M, K, N);
        System.out.println("dp2-Kills Ratio:" + (dp2 / all));
    }

    public long killNumbers(int M, int restK, int restN){
        if (restK == 0){
            return restN<=0 ? 1 : 0;
        }
        if (restN <= 0){
            return (long) Math.pow(M+1, restK);
        }
        long ways = 0;
        for (int i=0; i<=M; i++){
           ways +=  killNumbers(M, restK-1, restN-i);
        }

        return ways;
    }

    public long dp1(int M, int K, int N){
        long[][] dp = new long[K+1][N+1];
        dp[0][0] = 1;

        for (int restK=1; restK<=K; restK++){
            dp[restK][0] = (long) Math.pow(M+1, restK);
            for (int restN=1; restN<=N; restN++){
                long ways = 0;
                for (int i=0; i<=M; i++){
                    if (restN-i>=0){
                        ways += dp[restK-1][restN-i];
                    } else {
                        ways += (long)Math.pow(M+1, restK-1);
                    }
                }

                dp[restK][restN] = ways;
            }
        }

        return dp[K][N];
    }

    public long dp2(int M, int K, int N){
        long[][] dp = new long[K+1][N+1];
        dp[0][0] = 1;

        for (int restK=1; restK<=K; restK++){
            dp[restK][0] = (long) Math.pow(M+1, restK);
            for (int restN=1; restN<=N; restN++){
                long ways = 0;
                //dp[restK][restN] = dp[restK][restN-1] + dp[restK-1][restN] - dp[restK-1][restN-M-1]
                ways += dp[restK][restN-1] + dp[restK-1][restN];
                if (restN-M-1 >= 0){
                    ways-=dp[restK-1][restN-M-1];
                } else {
                    ways-=(long)Math.pow(M+1,restK-1);
                }

                dp[restK][restN] = ways;
            }
        }

        return dp[K][N];
    }
}
