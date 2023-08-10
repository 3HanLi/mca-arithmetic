package com.wy.mca.leetcode;

/**
 * @author boss
 */
public class PeekElement {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        int peekIndex = findPeakElement(nums);
        System.out.println(peekIndex);
    }

    /**
     * 寻找峰值：峰值元素是指其值大于左右相邻值的元素
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        for (int i=0; i<nums.length-1; i++){
            if (nums[i] - nums[i+1] > 0){
                return i;
            }
        }
        return nums.length-1;
    }
}
