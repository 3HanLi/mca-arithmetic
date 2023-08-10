package com.wy.mca.leetcode;

/**
 * @author wangyong01
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 18;
        int[] result = twoSum(nums, target);
        for (int index : result){
            System.out.println(index);
        }
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标
     * 假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍
     * @param nums      数组
     * @param target    目标值
     * @return          返回的下标
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[]{-1,-1};
        for (int i=0; i<nums.length-1; i++){
            for (int j=i+1; j<nums.length; j++){
                if (nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

}
