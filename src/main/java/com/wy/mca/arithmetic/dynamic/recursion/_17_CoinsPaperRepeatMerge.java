package com.wy.mca.arithmetic.dynamic.recursion;

import com.google.common.collect.Maps;
import lombok.Data;
import org.junit.Test;

import java.util.Map;

/**
 * @Description
 * @Author wangyong01
 * @Date 2023/4/14 3:25 PM
 * @Version 1.0
 */
public class _17_CoinsPaperRepeatMerge {

    @Test
    public void coinsPaperRepeatMerge(){
        int[] repeatCoins = {1,2,1,1,2,1,1};
        int rest = 4;

        Info infos = getInfos(repeatCoins);
        int[] coins = infos.getCoins();
        int[] zhangs = infos.getZhangs();
        int waysVR = waysVR(coins, zhangs, 0, rest);
        System.out.println("waysVR:" + waysVR);

        int waysDP = waysDP(coins, zhangs, rest);
        System.out.println("waysDP:" + waysDP);

    }

    public int waysVR(int[] coins, int[] zhangs, int index, int rest){
        if (index == coins.length){
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang=0; zhang <= zhangs[index] && rest-coins[index] * zhang >=0; zhang++){
            ways+=waysVR(coins, zhangs, index+1, rest-zhang * coins[index]);
        }
        return ways;
    }
    public int waysDP(int[] coins, int[] zhangs, int rest){
        int len = coins.length;
        int[][] dp = new int[len+1][rest+1];
        dp[len][0] = 1;

        for (int i=len-1; i>=0; i--){
            for (int j=0; j<=rest;j++){
                dp[i][j] = dp[i+1][j];
                if (j-coins[i] >= 0){
                    dp[i][j] += dp[i][j-coins[i]];
                }
                if (j - (zhangs[i] + 1) * coins[i] >=0){
                    dp[i][j] -= dp[i+1][j - (zhangs[i] + 1) * coins[i]];
                }
            }
        }

        return dp[0][rest];
    }

    public Info getInfos(int[] repeatCoins){
        Map<Integer, Integer> coinCountMap = Maps.newTreeMap();
        for (Integer coin : repeatCoins){
            Integer count = coinCountMap.containsKey(coin) ? coinCountMap.get(coin) : 0;
            coinCountMap.put(coin, count+1);
        }

        Info info = new Info();

        int size = coinCountMap.size();
        int[] coins = new int[size];
        int[] zhangs = new int[size];
        int index = 0;
        for (Map.Entry<Integer, Integer> coinsNumEntry : coinCountMap.entrySet()){
            coins[index] = coinsNumEntry.getKey();
            zhangs[index] = coinsNumEntry.getValue();
            index++;
        }

        info.setCoins(coins);
        info.setZhangs(zhangs);
        return info;
    }

}

@Data
class Info{
    int[] coins;
    int[] zhangs;
}
