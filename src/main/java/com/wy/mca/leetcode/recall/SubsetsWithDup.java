package com.wy.mca.leetcode.recall;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）
 * 输入: [1,2,2]
 * 输出:
 * [
 *   []
 *   [1],
 *   [1,2],
 *   [1,2,2],
 *   [2],
 *   [2,2],
 * ]
 * 输入: [1,2,3]
 * 输出:
 * [
 *   []
 *   [1],
 *   [1,2],
 *   [1,2,3],
 *   [2],
 *   [2,3],
 *   [3]
 * ]
 * 思想：回溯算法【题目：90】
 *
 */
public class SubsetsWithDup {

    public static void main(String[] args) {

    }

    /**
     * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        resultList.add(new ArrayList<>());

        return resultList;
    }

}
