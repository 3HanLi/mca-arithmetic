package com.wy.mca.test;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * @Description 动态规划测试
 * @Author wangyong01
 * @Date 2023/3/22 11:29 上午
 * @Version 1.0
 */
public class TestDP {

    public static final String smallLetter = "abcdefghijklmnopqrstuvwxyz";
    public static final String smallLetterNumber = "abcdefghijklmnopqrstuvwxyz1234567890";

    public static void main(String[] args) {
        int n = 7;
        int start = 2;
        int aim = 4;
        int k = 4;
        int ways = getWays(start, k, aim, n);
        System.out.println("ways:" + ways);

        int waysCache = getWaysCache(start, k, aim, n);
        System.out.println("waysCache:" + waysCache);

        int waysDp = getWaysDp(start, k, aim, n);
        System.out.println("waysDp:" + waysDp);

    }

    /**
     * 机器人走路
     * @param cur
     * @param rest
     * @param aim
     * @param n
     * @return
     */
    public static int getWays(int cur, int rest, int aim ,int n){
        if (rest == 0){
            return cur == aim ? 1 : 0;
        } else {
            if (cur == 1){
                return getWays(cur +1, rest-1, aim, n);
            } else if (cur == n){
                return getWays(cur -1, rest -1, aim, n);
            } else{
                return getWays(cur +1, rest-1, aim, n) + getWays(cur -1, rest -1, aim, n);
            }
        }
    }

    public static int getWaysCache(int start, int k, int aim ,int n){
        int[][] cache = new int[n+1][k+1];
        for (int i=0; i<cache.length; i++){
            for (int j=0; j<cache[i].length; j++){
                cache[i][j] = -1;
            }
        }
        return getWaysCache(start, k, aim, n, cache);
    }

    /**
     * 机器人啥缓存
     * @param cur
     * @param rest
     * @param aim
     * @param n
     * @param cache
     * @return
     */
    public static int getWaysCache(int cur, int rest, int aim ,int n, int[][] cache){
        if (cache[cur][rest] != -1){
            return cache[cur][rest];
        }
        if (rest == 0){
            return cur == aim ? 1 : 0;
        } else {
            int result = -1;
            if (cur == 1){
                result = getWaysCache(cur +1, rest-1, aim, n, cache);
            } else if (cur == n){
                result = getWaysCache(cur -1, rest -1, aim, n, cache);
            } else{
                result = getWaysCache(cur +1, rest-1, aim, n, cache) + getWaysCache(cur -1, rest -1, aim, n, cache);
            }
            cache[cur][rest] =  result;

            return rest;
        }
    }

    /**
     * 动态规划
     * @param start
     * @param k
     * @param aim
     * @param n
     * @return
     */
    public static int getWaysDp(int start, int k, int aim ,int n){
        int[][] dp = new int[n+1][k+1];
        dp[aim][0] = 1;
        for (int rest=1; rest<=k; rest++){
            dp[1][rest] = dp[2][rest-1];
            for (int cur=2; cur<n; cur++){
                dp[cur][rest] = dp[cur +1][rest-1] + dp[cur -1][rest -1];
            }
            dp[n][rest] = dp[start-1][rest-1];
        }

        return dp[start][k];
    }

    @Test
    public void maxScore(){
        int[] score = {50, 100, 20, 10};
        int max = f(score, 0, score.length - 1);
        System.out.println("max:" + max);

        int scoreDp = maxScoreDp(score);
        System.out.println("maxDp:" + scoreDp);
    }

    public int f(int[] arr, int l, int r){
        if (l == r){
            return arr[l];
        }
        int p1 = arr[l] + g(arr, l+1, r);
        int p2 = arr[r] + g(arr, l, r-1);

        return Math.max(p1, p2);
    }

    public int g(int[] arr, int l, int r){
        if (l == r){
            return 0;
        }
        int p1 = f(arr, l+1, r);
        int p2 = f(arr, l, r-1);
        return Math.min(p1, p2);
    }

    public int maxScoreDp(int[] scores){
        int length = scores.length;
        int[][] f = new int[length][length];
        int[][] g = new int[length][length];

        for (int i=0; i<scores.length; i++){
            f[i][i] = scores[i];
        }

        for (int i=1; i<scores.length; i++){
            int row = 0;
            int col = i;

            while (col < length){
                f[row][col] = Math.max(scores[row] + g[row+1][col], scores[col] + g[row][col-1]);
                g[row][col] = Math.min(f[row+1][col], f[row][col-1]);

                row++;
                col++;
            }
        }

        return f[0][length-1];
    }

    @Test
    public void maxBag(){
        int[] w = {3, 5, 2};
        int[] v = {7, 10, 5};

        int bag = 5;

        int maxBagValue = maxBagValue(w, v, 0, bag);
        System.out.println("maxBagValue:" + maxBagValue);

        int maxBagValueDp = maxBagValueDp(w, v, bag);
        System.out.println("maxBagValueDp:" + maxBagValueDp);
    }

    public int maxBagValue(int[] w, int[] v, int i, int bag){
//        if (bag < 0){
//            return -1;
//        }
        if (i == w.length){
            return 0;
        }
        int v1 = 0;
        if (bag - w[i] >= 0){
            v1 = v[i] + maxBagValue(w, v, i+1, bag - w[i]);
        }
        int v2 = maxBagValue(w, v, i+1, bag);

        return Math.max(v1, v2);
    }

    public int maxBagValueDp(int[] w, int[] v, int bag){
        int[][] dp = new int[w.length+1][bag+1];
        for (int i=dp.length-2; i>=0; i--){
            for (int j=0; j<dp[i].length; j++){
                int v1 = 0;
                if (j - w[i] >= 0){
                    v1 = v[i] + dp[i+1][j - w[i]];
                }
                int v2 = dp[i+1][j];
                dp[i][j] = Math.max(v1, v2);
            }
        }

        return dp[0][bag];
    }

    @Test
    public void convertNumberToLetter(){
        //111 -> AAA、AK、KA
        //105 -> JE
        String s = "5830";
//        String s = RandomUtil.randomString("1234567890", 4);
        System.out.println("s:" + s);
        int i = convertNumberToLetterViolent(s.toCharArray(), 0);
        System.out.println("result:" + i);

        int i2 = convertNumberToLetterDP(s.toCharArray());
        System.out.println("result2:" + i2);
    }

    public int convertNumberToLetterViolent(char[] chars , int i){
        //base case
        //到下一个字符去查看字符能否转换成功
        //111 如果越界，说明可以转换成功
        //100
        if (i == chars.length){
            return 1;
        }
        //105: 0不能转换成功，需要去下一个字符5查看
        if (chars[i] == '0'){
            return 0;
        }

        //i单独转换
        int p1 = convertNumberToLetterViolent(chars, i+1);
        //i和i+1一起转换
        int p2 = 0;
        if ((i+1 < chars.length) && ((chars[i] - '0' ) * 10 + (chars[i+1] - '0') <= 26)){
            p2 = convertNumberToLetterViolent(chars, i+2);
        }

        return p1 + p2;
    }

    public int convertNumberToLetterDP(char[] chars){
        int length = chars.length;
        int[] dp = new int[length + 1];

        dp[length] = 1;

        for (int i= dp.length-2; i>=0; i--){
            if (chars[i] != '0'){
                int p1 = dp[i+1];
                int p2 = 0;
                if ((i+1 < chars.length) && ((chars[i] - '0' ) * 10 + (chars[i+1] - '0') <= 26)){
                    p2 = dp[i+2];
                }
                dp[i] = p1 + p2;
            }
        }

        return dp[0];
    }

    @Test
    public void stickTest(){
        String[] stickers = {"abd", "ak", "bc", "bck"};
        String target = "abck";
        int i = stickerToSpellWordV1(stickers, target);
        System.out.println("i:" + i);

        int j = stickerToSpellWordV2(stickers, target);
        System.out.println("j:" + j);

        int k = stickerToSpellWordDp(stickers, target);
        System.out.println("k:" + k);
    }

    public int stickerToSpellWordV1(String[] stickers, String target){
        //base case 单词为空，不需要贴纸
        if (target.length() == 0){
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i=0;i< stickers.length; i++){
            String rest = strMinus(target, stickers[i]);
            if (rest.length() != target.length()){
                //找到一张贴纸可用
                min = Math.min(min, stickerToSpellWordV1(stickers, rest));
            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public String strMinus(String target, String sticker){
        int[] c1 = new int[26];
        char[] chars = target.toCharArray();
        for (char c : chars){
            c1[c - 'a'] ++;
        }

        char[] chars2 = sticker.toCharArray();
        for (char c : chars2){
            c1[c-'a'] --;
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<c1.length; i++){
            if (c1[i] > 0){
                for (int j=0; j<c1[i]; j++){
                    sb.append((char)(i + 'a'));
                }
            }
        }

        return sb.toString();
    }

    public int stickerToSpellWordV2(String[] stickers, String target){
        int[][] stickerCount = new int[stickers.length][26];
        for (int i=0;i <stickers.length; i++){
            char[] chars = stickers[i].toCharArray();
            for (int j=0; j<chars.length; j++){
                stickerCount[i][chars[j] - 'a'] ++;
            }
        }
        return stickerToSpellWordV2(stickerCount, target);
    }

    private int stickerToSpellWordV2(int[][] stickerCount, String target){
        if (target.length() == 0){
            return 0;
        }

        //target词频统计
        char[] chars = target.toCharArray();
        int[] t = new int[26];
        for (char c : chars){
            t[c - 'a'] ++;
        }

        Integer min = Integer.MAX_VALUE;
        for (int i=0; i<stickerCount.length; i++){
            int[] sticker = stickerCount[i];
            //判断贴纸是否包含需要的字符
            //而不是先统计词频，将target减去贴纸之后再判断target长度是否变化，来决定是否递归
            if (sticker[chars[0] - 'a'] > 0){
                StringBuffer sb = new StringBuffer();
                for (int j=0; j<26; j++){
                    if (t[j] > 0){
                        int num = t[j] - sticker[j];
                        for (int k=0; k<num; k++){
                            sb.append((char) (j+'a') );
                        }
                    }
                }
                min = Math.min(min, stickerToSpellWordV2(stickerCount, sb.toString()));
            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public int stickerToSpellWordDp(String[] stickers, String target){
        int[][] stickerCount = new int[stickers.length][26];
        for (int i=0;i <stickers.length; i++){
            char[] chars = stickers[i].toCharArray();
            for (int j=0; j<chars.length; j++){
                stickerCount[i][chars[j] - 'a'] ++;
            }
        }
        return stickerToSpellWordDp(stickerCount, target, Maps.newHashMap());
    }

    private int stickerToSpellWordDp(int[][] stickerCount, String target, Map<String, Integer> dp){
        if (dp.containsKey(target)){
            return dp.get(target);
        }
        if (target.length() == 0){
            return 0;
        }

        //target词频统计
        char[] chars = target.toCharArray();
        int[] t = new int[26];
        for (char c : chars){
            t[c - 'a'] ++;
        }

        Integer min = Integer.MAX_VALUE;
        for (int i=0; i<stickerCount.length; i++){
            int[] sticker = stickerCount[i];
            //判断贴纸是否包含需要的字符
            //而不是先统计词频，将target减去贴纸之后再判断target长度是否变化，来决定是否递归
            if (sticker[chars[0] - 'a'] > 0){
                StringBuffer sb = new StringBuffer();
                for (int j=0; j<26; j++){
                    if (t[j] > 0){
                        int num = t[j] - sticker[j];
                        for (int k=0; k<num; k++){
                            sb.append((char) (j+'a') );
                        }
                    }
                }
                min = Math.min(min, stickerToSpellWordV2(stickerCount, sb.toString()));
            }
        }

        min = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, min);
        return min;
    }

    @Test
    public void longestSubSequenceTest(){
        String s1 = RandomUtil.randomString(smallLetter,10);
        String s2 = RandomUtil.randomString(smallLetter,10);

        int i = longestSubSequence(s1.toCharArray(), s2.toCharArray(), 9, 9);
        System.out.println("i:" + i);

        int j = longestSubSequenceDp(s1, s2);
        System.out.println("j:" + j);
    }

    /**
     * 在chars1[0-i]和chars2[0-j]中找到最长公共子序列
     * @param chars1
     * @param chars2
     * @param i
     * @param j
     * @return
     */
    public int longestSubSequence(char[] chars1, char[] chars2, int i, int j){
        if (i==0 && j==0){
            return chars1[i] == chars2[j]  ? 1 : 0;
        } else if (i==0){
            //i=0 j!= 0
            return chars1[i] == chars2[j] ? 1 : longestSubSequence(chars1, chars2, i, j-1);
        } else if (j==0){
            //i!=0  j=0
            return chars1[i] == chars2[j] ? 1 : longestSubSequence(chars1, chars2, i-1, j);
        } else {
            //i!=0 j!=0

            int p1 = longestSubSequence(chars1, chars2, i, j-1);
            int p2 = longestSubSequence(chars1, chars2, i-1, j);
            int p3 = chars1[i] == chars2[j] ? (1 + longestSubSequence(chars1, chars2, i-1, j-1)) : 0;

            return Math.max(Math.max(p1, p2), p3);
        }
    }

    /**
     * 最长公共子序列
     * @param str1
     * @param str2
     * @return
     */
    public int longestSubSequenceDp(String str1, String str2){
        int M = str1.length();
        int N = str2.length();

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int[][] dp = new int[M][N];

        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;

        for (int j=1; j<N; j++){
            dp[0][j] = chars1[0] == chars2[j] ? 1 : dp[0][j-1];
        }

        for (int i=1; i<M; i++){
            dp[i][0] = chars1[i] == chars2[0] ? 1 : dp[i-1][0];
        }

        for (int i=1; i<M; i++){
            for (int j=1; j<N; j++){
                int p1 = dp[i][j-1];
                int p2 = dp[i-1][j];
                int p3 = chars1[i] == chars2[j] ? (1 + dp[i-1][j-1]) : 0;

                dp[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }

        return dp[M-1][N-1];
    }

}
