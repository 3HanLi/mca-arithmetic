package com.wy.mca.structure.recursion;

import com.wy.mca.util.ArrUtil;

import java.util.Arrays;

/**
 * 递归方式计算数组最大值
 * 1) 任何递归都可以改成非递归
 * 2) 递归的时间复杂度可以使用Master公式来计算
 *    Master公式：T(N) = a * T(N/b) + O(N^d)，其中a b d为常数，N为集合大小
 *    2.1 log(b,a) < d	==>	时间复杂度	O(N^d)
 *    2.2 log(b,a) > d	==>	时间复杂度	O(N ^ log(b,a))
 *    2.3 log(b,a) = d	==>	时间复杂度	O(N	^ d * logN)
 * 3) 递归排序举例说明：
 *    3.1 对数组分为左右两部分进行递归，问题规模分别为：T(n/2)，那么左右两个就是：2 * T(n/2)
 *    3.2 时间复杂度只需要拆分一次，后面的记为O(1)
 *    3.3 推导出该排序对应的Master公式为：T(n) = 2 * T(n/2) + O(1)
 *        那么 a=2, b=2, d=0，满足公式2.2，即：O(N ^ log(b,a)) == > O(N ^ log(2,2)) ==> O(N)
 *        因而时间复杂度为：O(N)
 *
 * @author wangyong01
 */
public class RecurMax {

    public static void main(String[] args) {
        int[] arr = ArrUtil.generateRandomArray(10, 10);
        int max = max(arr, 0, arr.length-1);
        Arrays.sort(arr);
        System.out.println(max == arr[arr.length-1]);
    }

    /**
     * 递归方式计算数组中最大值
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int max(int[] arr, int l, int r){
        if (l==r){
            return arr[l];
        }
        //避免踩坑:((r - l) >> 1)
        int mid = l + ((r - l) >> 1);
        int leftMax = max(arr, l, mid);
        int rightMax = max(arr, mid+1, r);
        return Math.max(leftMax, rightMax);
    }

}
