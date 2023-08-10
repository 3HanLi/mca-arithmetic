package com.wy.mca.arithmetic.dynamic.recursion;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 获取字符串的全排列
 * 1) 全排列是选取所有字符，且可以打乱顺序
 *
 * 暴力递归-移除元素法
 * 1) 解题思路
 *    1.1 定义变量path用于存放当前字符串排列结果，初始值为""
 *    1.2 假如字符串为rest，依次选择每个字符加入path中，并从字符串中rest移除当前字符（继续做递归）
 *    1.3 当字符串rest为空时，说明选取了所有字符，将排列结果加入结果集中
 *    1.4 将移除的变量还原，进行下一轮递归
 *
 * 2) 举例说明
 *    2.1 假如变量为abc
 *    2.2 第一轮：选取字符a加入path并移除，此时 ==> path="a", rest="bc"
 *        a) 选取字符b继续作递归     ==> abc
 *        b) 选取c字符继续作递归     ==> acb
 *    2.3 第二轮：选取字符b加入path并移除，此时 ==> path="b", rest="ac"
 *        a) 选取字符a继续作递归     ==> bac
 *        b) 选取字符c继续作递归     ==> bca
 *    ...
 *
 * 暴力递归-交换法
 * 1) 解题思路
 *    2.1 当前元素i和每个元素交换
 *    2.2 下个元素(i+1)也做同样的步骤
 *    2.3 当下标越界的时候，将字符数组加入字符串中
 *    2.4 将交换的变量还原
 *
 * 2) 举例说明
 *    2.1 假如字符串为abc
 *    2.2 a和a交换，此时依然为abc
 *        a) 下个字符b和b交换      ==>abc
 *        b) 下个字符b和c交换      ==>acb
 *    2.3 a和b交换，此时结果为bac
 *        a) 下个字符a和a交换      ==>bac
 *        b) 下个字符a和c交换      ==>bac
 *    ...
 * 3) 全排列去重-使用Set集合
 *
 * 4) 全排列去重-使用标记法
 *    4.1 由于当前元素下标i和每个元素都进行交换，当i下标元素arr[i]被访问过就记为true
 *    4.2 如果来到当前下标元素j发现被访问过，那么无需进行后续操作
 *    4.3 也就是：以某个字符为开头的元素只能出现一次，如果出现多次，说明存在重复排列
 *
 * 5) 举例说明 abca
 *    5.1 a依次和每个元素交换
 *    5.2 当i=0位置的a和i=3位置的a交换时说明存在重复排列
 *
 * @author wangyong01
 */
public class _03_PrintAllPermutations {

    public static void main(String[] args) {
        String str = "abca";
        List<String> allPermutations = getAllPermutations(str);
        System.out.println(allPermutations);

        System.out.println(getAllPermutationsWithSwap(str));

        System.out.println(getAllDistinctPermutationsWithSwap(str));
    }

    public static List<String> getAllPermutations(String str){
        List<String> answerList = Lists.newArrayList();
        char[] chars = str.toCharArray();
        List<Character> restList = Lists.newArrayList();
        for (Character c : chars){
            restList.add(c);
        }
        processAllPermutations(restList, "", answerList);
        return answerList;
    }

    /**
     * 暴力递归1（元素移除法）
     * @param restList      剩余的字符
     * @param path          当前已经选择的字符
     * @param answerList    结果集
     */
    private static void processAllPermutations(List<Character> restList, String path, List<String> answerList){
        if (restList.isEmpty()){
            //1.0 当剩余的字符串为空时，说明已经选去了排列结果，加入集合即可
            answerList.add(path);
        }else {
            for (int i=0; i<restList.size(); i++){
                //1.1 选取当前字符并移除
                Character curChar = restList.get(i);
                restList.remove(i);
                //1.2 继续向下递归
                processAllPermutations(restList, path + curChar, answerList);
                //1.3 将移除的字符还原
                restList.add(i, curChar);
            }
        }
    }

    public static List<String> getAllPermutationsWithSwap(String str){
        List<String> answerList = Lists.newArrayList();
        char[] chars = str.toCharArray();
        List<Character> restList = Lists.newArrayList();
        for (Character c : chars){
            restList.add(c);
        }
        processAllPermutationsWithSwap(chars, 0, answerList);
        return answerList;
    }

    public static List<String> getAllDistinctPermutationsWithSwap(String str){
        List<String> answerList = Lists.newArrayList();
        char[] chars = str.toCharArray();
        List<Character> restList = Lists.newArrayList();
        for (Character c : chars){
            restList.add(c);
        }
        processAllDistinctPermutationsWithSwap(chars, 0, answerList);
        return answerList;
    }

    /**
     * 暴力递归2（元素交换法）
     * @param chars
     * @param index
     * @param answerList
     */
    private static void processAllPermutationsWithSwap(char[] chars, int index, List<String> answerList){
        if (index == chars.length){
            //1.3 当数组越界时，加入结果集
            answerList.add(String.valueOf(chars));
        }else {
            for (int i=index; i<chars.length; i++){
                //1.1 当前位置i和每个位置交换
                swap(chars, i, index);
                //1.2 下个位置（i+1）也做同样的操作
                processAllPermutationsWithSwap(chars, index+1, answerList);
                //1.4 元素还原回去吧
                swap(chars, i, index);
            }
        }
    }

    private static void swap(char[] chars, int i, int j){
        char temp = chars[j];
        chars[j] = chars[i];
        chars[i] = temp;
    }

    /**
     * 全排列去重
     *
     * @param chars
     * @param index
     * @param answerList
     */
    private static void processAllDistinctPermutationsWithSwap(char[] chars, int index, List<String> answerList){
        if (index == chars.length){
            //1.3 当数组越界时，加入结果集
            answerList.add(String.valueOf(chars));
        }else {
            boolean[] visited = new boolean[256];
            for (int i=index; i<chars.length; i++){
                if (!visited[chars[i]]){
                    //1.1 当前位置i和每个位置交换
                    swap(chars, i, index);
                    //1.2 下个位置（i+1）也做同样的操作
                    processAllDistinctPermutationsWithSwap(chars, index+1, answerList);
                    //1.4 元素还原回去吧
                    swap(chars, i, index);
                    //1.5 当前元素被访问过
                    visited[chars[i]] = true;
                }
            }
        }
    }

}
