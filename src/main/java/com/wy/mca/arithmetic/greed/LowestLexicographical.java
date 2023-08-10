package com.wy.mca.arithmetic.greed;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.wy.mca.util.ArrUtil;
import com.wy.mca.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * @Description 最小字典序：给定一个字符串数组，需要把所有的字符串拼接起来，返回所有拼接结果中，字典序最小的拼接结果
 * @Author wangyong01
 * @Date 2022/7/13 4:46 下午
 * @Version 1.0
 */
public class LowestLexicographical {

    public static void main(String[] args) {
        String[] randomStrs = ArrUtil.generateRandomSmallStrings(5, 5);
        PrintUtil.printObject(randomStrs);

        String greedStr = greed(randomStrs);
        String recursiveInvokeStr = violentRecursiveInvoke(randomStrs);
        if (StrUtil.equals(greedStr, recursiveInvokeStr)){
            System.out.println("最小字典序:" + greedStr);
        }
    }

    /**
     * 贪心算法 解决 最小字典序
     * 1 解题思路：
     *   1.1 贪心算法没有固定套路，需要自己提出假设，找出对的假设后实现即可；
     *   1.2 大部分情况下，局部最优解即可以得到全局最优解；
     * 2 本题思路分析
     *   2.1 提出假设
     *       字符串 a+b < b+a，那么a + b 即是最优解
     *   2.2 案例说明
     *       arr = {"abc","cks","ft"}，最优解是拼接出来的结果：abccksft
     *       arr = {"b","ba"}，由于 ba +b < b + ba，那么baa就是最优解
     *   2.2 验证假设
     *
     * @param strs
     * @return
     */
    public static String greed(String[] strs){
        if (ArrayUtil.isEmpty(strs)){
            return "";
        }

        //字典序贪心策略核心逻辑：按照前后两个字符拼接结果进行排序，a + b < b + a
        Arrays.sort(strs, (o1 , o2) -> (o1 + o2).compareTo(o2 + o1));

        String conStr = "";
        for (String str : strs){
            conStr += str;
        }
        return conStr;
    }

    /**
     * 暴力递归
     *
     * @param strs
     * @return
     */
    public static String violentRecursiveInvoke(String[] strs){
        if (ArrayUtil.isEmpty(strs)){
            return "";
        }

        //1 获取所有排序结果
        TreeSet<String> treeSet = violentRecursive(strs);
        for (String concat : treeSet){
            return concat;
        }
        return "";
    }

    /**
     * 暴力递归：获取所有排序结果，取出最小的即可
     * @param strs
     * @return
     */
    private static TreeSet<String> violentRecursive(String[] strs){
        TreeSet<String> treeSet = Sets.newTreeSet();
        if (ArrayUtil.isEmpty(strs)){
            treeSet.add("");
            return treeSet;
        }

        for (int i=0; i<strs.length; i++){
            //1.1 尝试将每个字符串当做 字典序的第一个字符串
            String first = strs[i];
            //1.2 将剩下的字符串继续递归，获取排序结果
            String[] remainStrs = removeIndex(strs, i);
            TreeSet<String> remainTreeSet = violentRecursive(remainStrs);
            for (String str : remainTreeSet){
                treeSet.add(first + str);
            }
        }
        return treeSet;
    }

    private static String[] removeIndex(String[] strs, int index){
        List<String> strList = new ArrayList<>();
        for (int i=0; i<strs.length; i++){
            if (i != index){
                strList.add(strs[i]);
            }
        }

        return strList.toArray(new String[0]);
    }

}
