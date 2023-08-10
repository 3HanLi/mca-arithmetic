package com.wy.mca.arithmetic.dynamic.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 贴纸拼单词
 *
 * 1）问题描述：有一个贴纸数组arr和一个字符串str，arr中的每个字符串都代表一张贴纸，可以将贴纸剪碎成一个一个字符，贴纸可以无限次使用，请问：至少需要几张贴纸才能拼成字符串str
 *
 * 2）举例说明：
 *    2.1 arr={ba,c,abcd},str=babac，那么至少需要2张贴纸可以完成，也就是[abcd+abcd]和[ba+abcd]这两种方案
 *
 * 3）解题思路：依次使用单词和每张贴纸进行匹配，将（单词 减去 贴纸）后，剩余的字符继续匹配，当剩余字符串为空时，返回0，表示不需要贴纸
 *
 * 4）暴力递归
 *    4.1 base case：
 *        当剩余字符串为空时，说明不需要贴纸：
 *    4.2 其他情况分析
 *        a) 使用min代表匹配需要的贴纸数量，初始值为Integer.Max_value表示没有匹配到结果
 *        b) 使用字符串word减去贴纸sticker，如果字符串长度发生变化，说明当前贴纸有效，继续匹配；否则，继续遍历下一张贴纸
 *        c) 如果匹配到结果，那么就记为1，说明需要一张贴纸
 *
 * 5）暴力递归-贪心算法
 *    5.1 上述暴力递归需要将word和每一张贴纸进行比对，效率较低
 *    5.2 这里才用贪心算法进行剪枝，提高效率，具体如下
 *        a) 对贴纸做词频统计，生成二维数组
 *        b) 对字符串做词频统计，生成一维数组
 *        c) 如果贴纸包含字符串的首字母，则继续递归
 *    5.3 总结起来，该方案主要有两步
 *        a) 先将贴纸做词频统计
 *        b) 判断贴纸是否包含字符串首字母，做剪枝
 *
 * 6）动态规划缓存法
 *    6.1 分析是否有重复调用，假如sticker={aabbc, abbac}，那么word = word-sticker，就会出现重复调用
 *    6.2 分析变量word可变范围，由于word变化范围不固定，因而这里只能使用傻缓存
 *
 * @author wangyong01
 */
public class _09_StickerToSpellWord {

    public static void main(String[] args) {
        String[] str = {"ba","c","abcd"};
//        String[] str = ArrUtil.generateRandomSmallStrings(10, 10);
        String word = "babac";
        int violentCount1 = violentRecurseInvoke1(str, word);
        System.out.println("暴力递归=>" + violentCount1);

        int violentCount2 = violentRecurseInvoke2(str, word);
        System.out.println("暴力递归-剪枝(贪心算法)=>" + violentCount2);

        int dp = dp(str, word);
        System.out.println("暴力递归-傻缓存==>" + dp);

    }

    private static int violentRecurseInvoke1(String[] str, String word){
        int num = violentRecurse1(str, word);
        return num == Integer.MAX_VALUE ? 0 : num;
    }

    private static int violentRecurse1(String[] str, String word){
        //1 base case 当剩余字符串为空时，说明不需要贴纸了
        if (word.length() == 0){
            return 0;
        }
        //2.1 默认一开始没有匹配到结果
        int min = Integer.MAX_VALUE;
        for (String sticker : str){
            //2.2 使用(word-sticker)，返回剩余字符串
            String restWord = getRestWord(sticker, word);
            //2.3 长度发生变化，说明当前贴纸有效，继续匹配；否则，继续遍历下一张贴纸
            if (restWord.length() != word.length()){
                //2.3.1 继续使用剩余字符串，继续匹配；取最小值
                min = Math.min(min, violentRecurse1(str, restWord));
            }
        }

        //2.4 如果没有匹配到结果（即min = Integer.MAX_VALUE），则返回0；否则表示匹配到，需要一张贴纸
        min += (min == Integer.MAX_VALUE ? 0 : 1);
        return min;
    }

    /**
     * 字符串word - sticker，返回剩余字符
     *
     * @param sticker
     * @param word
     * @return
     */
    private static String getRestWord(String sticker, String word){
        char[] stickerCharArray = sticker.toCharArray();
        char[] wordCharArray = word.toCharArray();

        int[] count = new int[26];
        for (char ch : wordCharArray){
            count[ch - 'a'] ++;
        }

        for (char ch : stickerCharArray){
            count[ch -'a'] --;
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<count.length; i++){
            if (count[i] > 0){
                for (int j=0; j<count[i]; j++){
                    sb.append((char)(i+'a'));
                }
            }
        }

        return sb.toString();
    }

    /**
     * 暴力递归：剪枝（贪心算法）
     *
     * @param str
     * @param word
     * @return
     */
    private static int violentRecurseInvoke2(String[] str, String word){
        //1.1 使用二维数组表示贴纸，对贴纸做词频统计
        //str={acc,bbc,aaa} => {1 0 2},{0 2 1},{3,0,0}
        int[][] stickerCount = new int[str.length][26];
        for (int i=0; i<str.length; i++){
            char[] chars = str[i].toCharArray();
            for (int j=0; j<chars.length; j++){
                stickerCount[i][chars[j] - 'a'] ++;
            }
        }

        int num = violentRecurse2(stickerCount, word);
        return num == Integer.MAX_VALUE ? 0 : num;
    }

    /**
     * 使用二维数组表示贴纸
     *
     * @param stickers
     * @param word
     * @return
     */
    private static int violentRecurse2(int[][] stickers, String word){
        if (word.length() == 0){
            return 0;
        }

        //1.2 对字符串做词频统计
        //word="aabbc" => {2 2 1}
        char[] targetChars = word.toCharArray();
        int[] targetCount = new int[26];
        for (int i=0; i<targetChars.length; i++){
            targetCount[targetChars[i]-'a'] ++;
        }

        int min = Integer.MAX_VALUE;
        for (int i=0; i<stickers.length; i++){
            int[] sticker = stickers[i];
            //剪枝 这一行也是贪心策略
            //2剪枝效率高的原因在于：我们在这里可以快速判断当前贴纸是否包含字符串需要的字符（也就是首字母），如果不包含，直接跳过
            //2.1 由于字符串word会越来越短，因而我们只需要判断贴纸sticker是否包含字符串word的首字母，包含则使用字符串减去贴纸
            if (sticker[targetChars[0]-'a'] > 0){
                //2.2 使用字符串 - 贴纸
                StringBuilder sb = new StringBuilder();
                for (int j=0; j<sticker.length; j++){
                    //2.2.1 使用字符串 减去 贴纸 的当前字符 的剩余数量
                    int minus = targetCount[j] - sticker[j];
                    if (minus > 0){
                        //2.2.2 如果还有剩余，则获取到剩余的字符串
                        for (int k=0; k<minus; k++){
                            sb.append((char)('a' + j));
                        }
                    }
                }
                min = Math.min(min, violentRecurse2(stickers, sb.toString()));
            }
        }

        min += (min == Integer.MAX_VALUE ? 0 : 1);
        return min;
    }

    /**
     * 动态规划：缓存法
     *
     * @param str
     * @param word
     * @return
     */
    private static int dp(String[] str, String word){
        int[][] stickerCount = new int[str.length][26];
        for (int i=0; i<str.length; i++){
            char[] chars = str[i].toCharArray();
            for (int j=0; j<chars.length; j++){
                stickerCount[i][chars[j] - 'a'] ++;
            }
        }

        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("", 0);
        int num = dp(stickerCount, word, wordCountMap);

        return num == Integer.MAX_VALUE ? 0 : num;
    }

    private static int dp(int[][] stickers, String word, Map<String, Integer> wordCountMap){
        if (wordCountMap.containsKey(word)){
            return wordCountMap.get(word);
        }

        char[] targetChars = word.toCharArray();
        int[] targetCount = new int[26];
        for (int i=0; i<targetChars.length; i++){
            targetCount[targetChars[i]-'a'] ++;
        }

        int min = Integer.MAX_VALUE;
        for (int i=0; i<stickers.length; i++){
            int[] sticker = stickers[i];
            //剪枝
            if (sticker[targetChars[0]-'a'] > 0){
                StringBuilder sb = new StringBuilder();
                for (int j=0; j<sticker.length; j++){
                    int minus = targetCount[j] - sticker[j];
                    if (minus > 0){
                        for (int k=0; k<minus; k++){
                            sb.append((char)('a' + j));
                        }
                    }
                }
                min = Math.min(min, dp(stickers, sb.toString(), wordCountMap));
            }
        }

        min += (min == Integer.MAX_VALUE ? 0 : 1);
        wordCountMap.put(word, min);
        return min;
    }

}
