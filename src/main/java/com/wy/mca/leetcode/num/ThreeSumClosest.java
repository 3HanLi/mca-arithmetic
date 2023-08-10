package com.wy.mca.leetcode.num;

import java.util.Arrays;

/**
 * 最接近的3数之和
 * 1) 给定一个包括n个整数的数组nums 和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近，返回这三个数的和
 * 2) 假定每组输入只存在唯一答案
 * 3) 案例
 *    输入：nums = [-1,2,1,-4], target = 1
 *    输出：2
 *    解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)
 * @author admin
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
//        int[] nums = {3,7,-1,5,8,4,2,9,8};
        int target = 30;
        int[] nums = {-1,2,1,-4};
//        int[] nums = {0,2,1,-3};
//        int target = 1;
        int minus = threeSumClosest(nums, target);
        System.out.println(minus);
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int lastAbsMinus = Integer.MAX_VALUE;
        int currentMinus = 0;
        int[] minIndex = new int[3];

        for (int i=0; i<nums.length-2; i++){
            for (int j=i+1; j<nums.length; j++){
                for (int k=j+1; k<nums.length; k++){
                    currentMinus = Math.abs(nums[i] + nums[j] + nums[k] - target);
                    if (currentMinus <= lastAbsMinus){
                        lastAbsMinus = currentMinus;
                        minIndex[0] = nums[i];
                        minIndex[1] = nums[j];
                        minIndex[2] = nums[k];
                    }
                }
            }
        }
        return minIndex[0] + minIndex[1] + minIndex[2];
    }

}
