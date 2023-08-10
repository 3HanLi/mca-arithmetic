package com.wy.mca.arithmetic.dynamic.recursion;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;


/**
 * 问题描述：打印一个字符串的全部子序列
 *
 * 举例说明；
 * 1) 子序列指的是：在字符串中从前往后提取字符组成新的字符串，前后顺序不能乱
 * 2) 比如：
 *    2.1 字符串的abc子序列有 "", a , b ,c ,ac ..
 *    2.2 但是ba不是字符串的子序列
 *
 * 解题思路：
 * 1) 依次遍历字符数组
 * 2) 每个index走有2种选择，要和不要
 *
 * @author wangyong01
 */
public class _02_PrintAllSubSequences {

    public static void main(String[] args) {
        List<String> allSubSequences = getAllSubSequences("abca");
        System.out.println(allSubSequences);
        System.out.println("=====");
        Set<String> noRepeatSubSequences = getNoRepeatSubSequences("abca");
        System.out.println(noRepeatSubSequences);
    }

    /**
     * 不重复的子序列
     *
     * @param str
     * @return
     */
    private static Set<String> getNoRepeatSubSequences(String str){
        List<String> allSubSequences = getAllSubSequences(str);
        return Sets.newHashSet(allSubSequences);
    }

    private static List<String> getAllSubSequences(String str){
        if (StrUtil.isBlank(str)){
            return Lists.newArrayList("");
        }
        List<String> answerList = Lists.newArrayList();
        processAllSequences(str.toCharArray(), 0, "", answerList);
        return answerList;
    }

    /**
     * 处理所有子序列
     *
     * @param chars         字符集合
     * @param index         当前下标
     * @param path          已经做过的决定，也就是便利过程中子序列中存储的值
     * @param answerList    结果集
     */
    private static void processAllSequences(char[] chars, int index, String path, List<String> answerList){
        if (index == chars.length){
            answerList.add(path);
            return;
        }
        //path  不要当前字符
        processAllSequences(chars, index+1, path, answerList);
        //path+chars[index] 要当前字符
        processAllSequences(chars, index+1, path+chars[index], answerList);
    }

}
