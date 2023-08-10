package com.wy.mca.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyong01
 */
public class HuiWen {

    public static void main(String[] args) {
        String[] words = { "abcd", "dcba", "lls", "s", "sssll" };
        List<List<Integer>> palindromePairs = palindromePairs(words);
        for	(List<Integer> list : palindromePairs){
            System.out.println(list);
        }
    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> posList = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j) {
                    if (isHuiWen(words[i] + words[j])) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(j);
                        posList.add(list);
                    }
                }
            }
        }
        return posList;
    }

    /**
     * 判断是否回文
     * @param str
     * @return
     */
    private static boolean isHuiWen(String str) {
        int length = str.length();
        for (int i = 0; i < length/2; i++) {
            if (str.charAt(i) != str.charAt(length - i -1)) {
                return false;
            }
        }
        return true;
    }
}
