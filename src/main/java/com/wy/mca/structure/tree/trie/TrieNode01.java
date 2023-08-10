package com.wy.mca.structure.tree.trie;

import lombok.Data;

@Data
public class TrieNode01 {

    /**
     * 添加字符串中字符元素时，该节点通过了几次
     */
    public int pass;

    /**
     * 表示有多少个字符串以该节点结束，0表示没有字符串以该节点结束
     */
    public int end;

    /**
     * 前缀树构建出来的路径，这里假设路径上全是字母：a-z
     */
    public TrieNode01[] paths;

    public TrieNode01(){
        paths = new TrieNode01[26];
    }

}
