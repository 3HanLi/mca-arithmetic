package com.wy.mca.test;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @Description 字符串相关的测试
 * @Author wangyong01
 * @Date 2023/3/21 12:40 下午
 * @Version 1.0
 */
public class TestStr {

    public static void main(String[] args) {
        String s = RandomUtil.randomString(4);
        System.out.println("s:" + s);

        List<String> answerList = Lists.newArrayList();

        List<Character> rest = Lists.newArrayList();
        for (int i=0; i<s.length(); i++){
            rest.add(s.charAt(i));
        }

        permutationWithRemove(rest , "", answerList);
        System.out.println("permutationWithRemove:" + answerList);
    }

    /**
     * 获取全排列(移除元素)
     * @param rest
     * @param path
     * @param answerList
     */
    public static void permutationWithRemove(List<Character> rest, String path, List<String> answerList){
        if (rest.isEmpty()){
            answerList.add(path);
        } else {
            for (int i=0; i<rest.size(); i++){
                Character character = rest.get(i);
                rest.remove(i);
                permutationWithRemove(rest, path + character, answerList);
                rest.add(i, character);
            }
        }
    }

    /**
     * 获取全排列（交换元素法）
     * @param chars
     * @param index
     * @param answerList
     */
    public static void permutationWithSwap(char[] chars, int index, List<String> answerList){
        if (index == chars.length){
            answerList.add(new String(chars));
        } else {
            for (int i=index; i<chars.length; i++){
                swap(chars, i, index);
                permutationWithSwap(chars, index+1, answerList);
                swap(chars, index, i);
            }
        }
    }

    private static void swap(char[] chars, int i, int j){
        char t = chars[i] ;
        chars[i] = chars[j];
        chars[j] = t;
    }

    @Test
    public void testChar(){
        int j= 10;
        System.out.println(j + 'a');

        StringBuffer sb = new StringBuffer();
        sb.append((char)(j + 'a'));
        System.out.println(sb.toString());
    }

}
