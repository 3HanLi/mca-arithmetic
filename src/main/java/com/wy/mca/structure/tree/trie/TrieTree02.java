package com.wy.mca.structure.tree.trie;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wangyong01
 */
@Data
public class TrieTree02 {

    /**
     * 根节点
     */
    private TrieNode02 root;

    private List<String> wordList = new LinkedList<>();

    public TrieTree02(){
        root = new TrieNode02();
    }

    /**
     * 添加字符串
     * @param word
     */
    public void insert(String word){
        if (StrUtil.isBlank(word)){
            return;
        }

        //只要添加字符串，根节点pass++
        TrieNode02 node = root;
        node.pass ++;

        char[] charArray = word.toCharArray();
        for (int i=0; i<charArray.length; i++){
            Character c = charArray[i];
            if (!node.pathMap.containsKey(c)){
                node.pathMap.put(c, new TrieNode02());
            }
            node = node.pathMap.get(c);
            node.pass++;
        }
        node.end++;
        wordList.add(word);
    }

    /**
     * 字符串删除
     * @param word
     */
    public void delete(String word){
        if (search(word, false) != 0){
            TrieNode02 node = root;
            node.pass --;

            char[] charArray = word.toCharArray();
            for (int i=0; i<charArray.length; i++){
                Character c = charArray[i];
                if (--node.pathMap.get(c).pass == 0){
                    node.pathMap.remove(c);
                    return;
                }
            }
            node.end--;
        }
    }

    /**
     * 字符串查找
     * @param word      字符串
     * @param isPrefix  是否前缀 true完全匹配 false以前缀开始
     * @return
     */
    public int search(String word, boolean isPrefix){
        if (StrUtil.isBlank(word)){
            return 0;
        }
        TrieNode02 node = root;

        char[] charArray = word.toCharArray();
        for (int i=0; i<charArray.length; i++){
            Character c = charArray[i];
            if (!node.pathMap.containsKey(c)){
                return 0;
            }
            node = node.pathMap.get(c);
        }
        return isPrefix ? node.pass : node.end ;
    }

    public int wordNum(){
        return root.getPass();
    }

    /**
     * 根据前缀树还原
     *
     * @return
     */
    public String[] getWordList(){
        return wordList.toArray(new String[0]);
    }
}
