package com.wy.mca.structure.tree.trie;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 前缀树
 * 问题描述
 * 1) 根据字符串构建一棵树，树的节点上不存储元素，路径上存储元素
 * 2) 节点主要有两个属性
 *    pass  代表添加字符串通过节点的次数
 *    end   代表有多少个字符串在该节点结束
 *
 * 解题思路
 * 1) insert
 *    1.1 遍历字符数组，如果字符在路径上不存在，则创建，存在继续向下查找
 *    1.2 没经过一个节点，pass++
 *    1.3 如果字符数组遍历完毕，则对应的节点 end++
 * 2) search
 *    1.1 完全匹配：遍历字符数组，获取最后一个字符对应的节点的end
 *    1.2 不完全匹配：遍历字符数组，获取最后一个字符对应的节点的pass
 * 3) delete
 *    1.1 先判断字符串是否存在，存在则节点pass--
 *    1.2 如果节点的下一个节点的pass=1，则删除下一个节点对应的整个分支并结束
 *    1.3 如果字符数组遍历完毕，则说明该字符串存在多个，最后一个字符对应的end--
 *
 * @author wangyong01
 */
@Data
public class TrieTree01 {

    /**
     * 根节点
     */
    private TrieNode01 root;

    private List<String> wordList = new LinkedList<>();

    public TrieTree01(){
        root = new TrieNode01();
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
        TrieNode01 node = root;
        node.pass ++;

        char[] charArray = word.toCharArray();
        for (int i=0; i<charArray.length; i++){
            int pathIndex = charArray[i] - 'a';
            if (null == node.paths[pathIndex]){
                node.paths[pathIndex] = new TrieNode01();
            }
            node = node.paths[pathIndex];
            node.pass ++;
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
            TrieNode01 node = root;
            node.pass --;

            char[] charArray = word.toCharArray();
            for (int i=0; i<charArray.length; i++){
                int pathIndex = charArray[i] - 'a';
                if (--node.paths[pathIndex].pass == 0){
                    node.paths[pathIndex] = null;
                    return;
                }
                node = node.paths[pathIndex];
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
        TrieNode01 node = root;

        char[] charArray = word.toCharArray();
        for (int i=0; i<charArray.length; i++){
            int pathIndex = charArray[i] - 'a';
            if (null == node.paths[pathIndex]){
                return 0;
            }
            node = node.paths[pathIndex];
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
