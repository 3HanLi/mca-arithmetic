package com.wy.mca.leetcode.string;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 串联所有单词的子串
 * 1) 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 2) 子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序
 * 3) 案例1
 *    输入：s = "barfoothefoobarman",words = ["foo","bar"]
 *    输出：[0,9]
 *    解释：从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 4) 案例2
 *    输入：s = "wordgoodgoodgoodbestword",words = ["word","good","best","word"]
 *    输出：输出：[]
 *
 * 题目来源：leetcode[30]
 */
public class FindSubstring {

    public static void main(String[] args) {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","good"};
        List<Integer> indexList = findSubstring(s, words);
        System.out.println(indexList);
    }

    //解题思路
    //1 words 转 wordMap
    //2 遍历s，index i, 截取wordLen得到wordSub，
    //3 如果wordMap.contains(wordSub)，则截取concatSize，和wordMap匹配，匹配规则如下：
    //3.1 wordMap.get(wordSub)-->count
    //3.2 count--,count=0时，remove
    //3.3 !wordMap.contains(wordSub), break,
    //3.4 符合结果, indexList.add(i)
    //4 初始化wordMap ,continue step 3
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> indexList = Lists.newArrayList();

        if (ArrayUtil.isEmpty(words) || StrUtil.isBlank(s)){
            return Lists.newArrayList();
        }
        int wordLen = words[0].length();
        int wordSize = words.length;
        int concatSize = wordLen * wordSize;
        if (s.length() < concatSize){
            return Lists.newArrayList();
        }

        //s = "barfoothefoobarman", words = ["foo","bar"]
        Map<String, Integer> wordMap = initWordCountMap(words);
        boolean needInitWordMap = false;
        for (int i=0; i<s.length() - concatSize + 1; i++){
            String currentSubWord = s.substring(i, i + wordLen);
            if (wordMap.containsKey(currentSubWord)){
                String tempSub = s.substring(i, i + concatSize);
                for (int j=0; j<tempSub.length(); j=j+wordLen){
                    String tempSub2 = tempSub.substring(j, j + wordLen);
                    Integer tempCount = wordMap.get(tempSub2);
                    if (tempCount == null || tempCount == 0){
                        break;
                    }
                    tempCount--;
                    needInitWordMap = true;
                    if (tempCount == 0){
                        wordMap.remove(tempSub2);
                    }else {
                        wordMap.put(tempSub2, tempCount);
                    }
                }
                if (wordMap.isEmpty()){
                    indexList.add(i);
                }
                if (needInitWordMap){
                    wordMap =initWordCountMap(words);
                }
            }
        }

        return indexList;
    }

    /**
     * 初始化每个word的数量
     * @param words
     * @return
     */
    private static Map<String, Integer> initWordCountMap(String[] words){
        Map<String, Integer> wordMap = Maps.newHashMap();
        for (String word : words){
            if (wordMap.containsKey(word)){
                Integer count = wordMap.get(word);
                wordMap.put(word, ++count);
            }else {
                wordMap.put(word, 1);
            }
        }
        return wordMap;
    }
}
