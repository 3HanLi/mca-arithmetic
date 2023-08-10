package com.wy.mca.structure.tree.trie;

import com.google.common.collect.Maps;
import com.wy.mca.util.MyStrUtil;
import com.wy.mca.util.PrintUtil;

import java.util.Map;

/**
 * 前缀树测试
 * @author wangyong01
 */
public class TrieTreeClient {

    public static void main(String[] args) {
//        tryTrieTree();
        validateTriTree(100, 10);
    }

    /**
     * 前缀树测试
     */
    private static void tryTrieTree(){
        TrieTree01 trieTree01 = new TrieTree01();
        trieTree01.insert("abc");
        trieTree01.insert("absk");
        PrintUtil.printObject(trieTree01.getWordList());
        System.out.println(trieTree01.search("abc", true));
        System.out.println(trieTree01.search("abs", false));
        System.out.println(trieTree01.wordNum());
        trieTree01.delete("absk");
    }

    /**
     * 前缀树对数器
     */
    private static void validateTriTree(int num, int maxLen){
        Map<String, Integer> strCountMap = Maps.newHashMap();
        TrieTree01 trieTree01 = new TrieTree01();
        TrieTree01 trieTree02 = new TrieTree01();

        //初始化前缀树 和 字符串出现个数
        for (int i=0; i<num; i++){
            String tempStr = MyStrUtil.generateRandomStr(maxLen, 6);
            if (strCountMap.containsKey(tempStr)){
                strCountMap.put(tempStr, strCountMap.get(tempStr) +1);
            }else {
                strCountMap.put(tempStr, 1);
            }
            trieTree01.insert(tempStr);
            trieTree02.insert(tempStr);
        }

        for (String tempStr : strCountMap.keySet()){
            Integer strCount = strCountMap.get(tempStr);
            int trieCount = trieTree01.search(tempStr, false);
            int trieCount02 = trieTree02.search(tempStr, false);

            //测试是否包含字符串
            if (strCount != trieCount && strCount != trieCount02){
                System.out.println("Search error:" + tempStr);
            }

            //测试是否包含前缀
            int strLen = tempStr.length();
            String tempSub = tempStr.substring(0, (int) (Math.random() * strLen + 1));
            if (trieTree01.search(tempSub, true) == 0){
                System.out.println("前缀查找错误:" + tempStr);
            }

            for (int i=0; i<strCount; i++){
                trieTree01.delete(tempStr);
                trieTree02.delete(tempStr);
            }
        }
        System.out.println("TrieTree:" + trieTree01.getRoot());
        System.out.println("TrieTree02:" + trieTree01.getRoot());
    }

}
