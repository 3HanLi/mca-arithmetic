package com.wy.mca.structure.tree.trie;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

@Data
public class TrieNode02 {

    /**
     * 添加字符串中字符元素时，该节点通过了几次
     */
    public int pass;

    /**
     * 表示有多少个字符串以该节点结束，0表示没有字符串以该节点结束
     */
    public int end;

    /**
     * 前缀树构建出来的路径和节点的映射关系
     */
    public Map<Character,TrieNode02> pathMap;

    public TrieNode02(){
        pathMap = Maps.newHashMap();
    }

}
