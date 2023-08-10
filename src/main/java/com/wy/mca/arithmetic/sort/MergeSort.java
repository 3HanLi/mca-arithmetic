package com.wy.mca.arithmetic.sort;

import com.wy.mca.util.ArrUtil;

import java.util.Arrays;

/**
 * 归并排序总结
 * 1) 递归方式
 *    1.1 每次都是二分，让左右数组有序
 *    1.2 对有序数组进行合并
 * 2) 非递归方式
 *    1.1 每次都是调整步长，让左右数组有序
 *    1.2 有序对数组进行合并
 * 3) 也就是：合并操作才是我们对核心，真正的有序发生在合并操作
 *
 * @author wangyong01
 */
public class MergeSort {

    public static void main(String[] args) {
        validate(1000);
    }

    private static void validate(int len){
        int[] arr = ArrUtil.generateRandomArray(len, 1000);
        int[] arr2 = Arrays.copyOf(arr, arr.length);

//        recursiveSort(arr, 0, len-1);
        sort(arr);
        Arrays.sort(arr2);
        System.out.println("arr=arr2:" + Arrays.equals(arr, arr2));
    }

    /**
     * 归并排序【递归方式】解题思路
     * 1) 让数组arr的左侧[0-mid]有序
     * 2) 让数组arr的右侧[mid-right]有序
     * 3) 合并merge
     *    3.1 准备辅助数组help，比较左右数组中值的大小，谁小拷贝谁，如果相等，优先拷贝左侧
     *    3.2 左侧和右侧肯定有一个优先拷贝完，将没有拷贝完的数据复制到help数组中
     *    3.3 将help数组中有序数据，复制到arr[l-r]
     *
     * 时间复杂度计算【通过master公式】
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void recursiveSort(int[] arr, int l, int r){
        if (l == r){
            return;
        }
//        int mid = (l + r) / 2;
        int mid = l + ((r -l) >> 1);    //防止越界
        //先让左侧有序
        recursiveSort(arr, l, mid);
        //再让右侧有序
        recursiveSort(arr, mid+1, r);
        //最后合并
        merge(arr, l, mid, r);
    }

    /**
     * 归并排序【非递归方式】解题思路
     * 1) 设置步长
     *    1.1 让步长为1，对左右组排序，然后继续下一组
     *    1.2 让步长为2，对左右组排序，然后继续下一组
     *    1.3 ...让步长为2^n
     * 2) 对每个左右组的数据排序，除非右组没有，否则都可以merge；
     * 3) 合并
     * @param arr
     */
    private static void sort(int[] arr){
        int length = arr.length;

        //1.1 设置步长为1
        int stepSize = 1;
        while (stepSize < length){
            //2.1 左组指针
            int l = 0;
            while (l < length){
                //2.2 左组的右指针
                int m = l + stepSize - 1;
                //2.3 如果左组的右指针已经超过数组长度，说明没有右组了，调整步长
                if (m >= arr.length){
                    break;
                }
                //2.3 右组的右指针
                int r = Math.min(m + stepSize, length -1);
                //3 合并
                merge(arr, l, m ,r);
                //3.1 开始下一组合并
                l = r + 1;
            }
            if (stepSize > length / 2){
                break;
            }
            //1.2 步长翻倍
            stepSize <<= 1;
        }
    }

    /**
     * 合并排序
     * @param arr
     * @param l
     * @param m
     * @param r
     */
    public static void merge(int[] arr, int l, int m, int r){
        //1.1 辅助数组
        int[] help = new int[r -l +1];
        //1.2 辅助指针
        int i=0;
        //2.1 左侧数组的指针
        int p1 = l;
        //2.2 右侧数组的指针
        int p2 = m+1;
        //3 谁小拷贝谁
        while (p1 <= m && p2 <=r){
            help[i++] = arr[p1]<=arr[p2] ? arr[p1++] : arr[p2++];
        }
        //3.1 p1<=m 和 p2<=r只会发生一个，只有一个会越界
        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        while (p2<=r){
            help[i++] = arr[p2++];
        }
        //3.2 将辅助数组中排好序的元素拷贝到arr[l-r]中
        for (int k=0; k<help.length; k++){
            arr[l++] = help[k];
        }
    }
}


