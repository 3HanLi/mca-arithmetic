package com.wy.mca.arithmetic.sort;


import com.wy.mca.util.ArrUtil;
import com.wy.mca.util.PrintUtil;

import java.util.Arrays;

/**
 * 随即快排序
 * @author wangyong01
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = ArrUtil.generateRandomArray(10, 100);
        //1.1 荷兰国旗问题一
        System.out.println("荷兰国旗问题V：小于等于<10的元素放在左边，大于>10的元素放在右边");
        PrintUtil.printOneDimensionArr(arr);
        netherLandV1(arr, 10);
        PrintUtil.printOneDimensionArr(arr);

        //1.2 荷兰国旗问题延伸
        arr = ArrUtil.generateRandomArray(10, 100);
        System.out.println("荷兰国旗问题V1_1：将arr最后一个元素"+arr[9]+"作为目标值，返回<=目标值的索引");
        PrintUtil.printOneDimensionArr(arr);
        int index = netherLandV1_1(arr, 0, arr.length - 1);
        PrintUtil.printOneDimensionArr(arr);
        System.out.println("Index==>" + index);

        //2 荷兰国旗问题二
        arr = ArrUtil.generateRandomArray(10, 100);
        System.out.println("荷兰国旗问题V2：将arr最后一个元素"+arr[9]+"作为目标值，返回=目标值的索引区间");
        PrintUtil.printOneDimensionArr(arr);
        int[] indexRange = netherLandV2(arr, 0, arr.length - 1);
        PrintUtil.printOneDimensionArr(arr);
        System.out.print("IndexRange==>");
        PrintUtil.printOneDimensionArr(indexRange);

        //3 使用荷兰国旗问题一解决快排序
        System.out.println("荷兰国旗问题一解决排序");
        quickSortV1(arr);
        PrintUtil.printOneDimensionArr(arr);

        //4 使用荷兰国旗问题二解决快排序
        System.out.println("荷兰国旗问题二解决排序");
        quickSortV2(arr);
        PrintUtil.printOneDimensionArr(arr);

        //5 对数器验证快速排序是否正确
        System.out.println("对数器验证快排序");
        validate(1000, 1000);
    }

    /**
     * 对数器验证快速排序是否正确
     *
     * @param length
     * @param range
     */
    private static void validate(int length, int range){
        int[] arr = ArrUtil.generateRandomArray(length, range);
        int[] copyArr = Arrays.copyOf(arr, arr.length);

        quickSortV1(arr);
        Arrays.sort(copyArr);

        System.out.println("Equals:" + Arrays.equals(arr, copyArr));
    }

    /**
     * 荷兰国旗问题一
     * 问题描述：<= num的排在数组左边，>num的数字排在右边
     * 解决思路
     * 1) 定义变量L，表示<=num的下标，初始值为-1；定义变量i，表示当前下标，初始值为0
     * 2) 如果arr[i]<=num，
     *    2.1 交换arr[L+1]和arr[i]，
     *    2.2 然后L++,i++，
     *    2.3 L+1代表的是>num的元素
     * 3) 如果arr[i]>num，则i++
     * 4) 原因说明
     *    4.1 L的下一个元素一开始是i，也就是只要当前数arr[i]<num，那么L和i必定紧挨着，即：L推着i向右走
     *    4.2 如果arr[i]>num，那么i++，需要继续查找<num的元素和arr[L+1]交换，此时L和i产生了间隙
     * 举例说明：
     * 1) 数组arr={2,1,5,4,2,6,7,0},目标数num=4
     * 2) 推演步骤
     *    2.1 L=-1, i=0   ==>arr[0]和arr[0]交换     {2,1,5,4,2,6,7,0}
     *    2.2 L=0,  i=1   ==>arr[1]和arr[1]交换     {2,1,5,4,2,6,7,0}
     *    2.3 L=1,  i=2   i++，
     *    2.4 L=1,  i=3   ==>arr[1]和arr[3]交换     {2,1,4,5,2,6,7,0}  此时i=3左侧的数据<=num
     *    ...
     * 思考
     * 当前区域的初始值时-1，0可以么？
     * @param arr
     * @param num
     */
    private static void netherLandV1(int[] arr, int num){
        int L = -1;
        int i = 0;
        int R = arr.length;
        while (i < R){
            if (arr[i] <= num){
                ArrUtil.swap(arr,++L, i);
            }
            i++;
        }
    }

    /**
     * 荷兰国旗问题一扩展
     * 1) 以arr[R]为目标元素num，<=arr[R]的放在左边，大于arr[R]的放在右边
     * 2) 返回<=区域的下标
     * @param arr
     * @param L     数组左边下标
     * @param R     数组右边下标
     * @return
     */
    private static int netherLandV1_1(int[] arr, int L, int R){
        if (L > R){
            return -1;
        }
        if (L == R){
            return L;
        }
        //<= 边界索引
        int less = L-1;
        while (L < R){
//            if (arr[L] <= arr[R]){
//                less++;
//                ArrUtil.swap(arr, less, L);
//                L++;
//            }else {
//                L++;
//            }
            //等价于上面的代码
            if (arr[L] <= arr[R]){
                ArrUtil.swap(arr, ++less, L);
            }
            L++;
        }
        ArrUtil.swap(arr, ++less, R);

        return less;
    }

    /**
     * 荷兰国旗问题二
     * 问题描述：以arr的最后一个元素作为num，<num的放在左边，>num的放在右边, =num的放在中间，返回=num的区间坐标
     * 解决思路
     * 1) 目标值num=arr[R]
     * 2) 定义变量less，表示小于num下标；定义变量more，表示>num下标；index为当前元素下标
     * 3) arr[index] < num  交换当前元素和最小区域下一个元素
     *    3.1 交换arr[less+1]和arr[index],
     *    3.2 less++; index ++
     * 4) arr[index] = num
     *    index++;
     * 5) arr[index] > num  交换当前元素和最大区域前一个元素，然后最大区域向左移动；当前指针保持不变，因为需要判断交换过来的元素是不是还满足条件
     *    5.1 交换arr[more-1]和arr[index]
     *    5.2 more--;
     * 6) 当前数index=more时，停止比较
     * @param arr
     * @param L     数组左边界
     * @param R     数组右边姐
     * @return      =arr[R]的区间
     */
    private static int[] netherLandV2(int[] arr, int L, int R){
        if (L > R){
            return new int[]{-1,-1};
        }
        if (L == R){
            return new int[]{L, L};
        }
        int less = L-1;
        int more = R ;
        int index = L;
        while (index < more){
            if (arr[index] < arr[R]){
                ArrUtil.swap(arr, ++ less, index++);
            }else if (arr[index] == arr[R]){
                index ++;
            }else {
                ArrUtil.swap(arr, --more, index);
            }
        }
        //当index和more碰撞时，交换arr[more]和arr[R]
        ArrUtil.swap(arr, index, R);
        return new int[]{++less, index};
    }

    /**
     * 基于荷兰国旗问题一 实现快排序
     * @param arr
     */
    private static void quickSortV1(int[] arr){
        if (null == arr || arr.length < 2){
            return;
        }
        quickSortProcessV1(arr, 0, arr.length -1);
    }

    private static void quickSortProcessV1(int[] arr, int L, int R){
        if (L >= R){
            return;
        }
        ArrUtil.swap(arr, L+(int)(Math.random() * (R-L+1)), R);

        int index = netherLandV1_1(arr, L, R);
        //边界问题：注意这里是index-1
        //原因：假如arr={3,7}，那么index=1，会导致死递归
        quickSortProcessV1(arr, L, index-1);
        quickSortProcessV1(arr, index +1, R);
    }

    /**
     * 基于荷兰国旗问题二 实现快速排序
     * 解题思路：
     * 1) 将arr最后一个元素作为目标值x，让左边区域的值<x,右边区域的值>x，中间的数=x
     * 2) 继续拿左边区域的最后一个值作为x，在左边递归；
     * 3) 拿右边区域的最后一个值作为x，在右边递归
     *
     * 4) 在递归的过程中随机调整x的值，避免出现O(N^2)的情况，原因解释见代码描述
     *
     * @param arr
     */
    private static void quickSortV2(int[] arr){
        if (null == arr || arr.length < 2){
            return;
        }
        quickSortProcessV2(arr, 0, arr.length - 1);
    }

    private static void quickSortProcessV2(int[] arr, int L, int R){
        if (L >= R){
            return;
        }
        //最坏情况下荷兰国旗问题的排序时间复杂度是O(N^2)，比如：arr={1,2,3,4,5,6,7}，原因说明
        //1.1 第一次需要遍历N次
        //1.2 第二次需要遍历N-1次
        //..
        //最终的时间复杂度是O(N^2)
        // 这里将R元素随即打乱，避免最坏情况的发生
        ArrUtil.swap(arr, L+(int)(Math.random() * (R-L+1)), R);

        int[] index = netherLandV2(arr, L, R);
        //注意这里是index[0]-1
        quickSortProcessV2(arr, L, index[0] -1);
        quickSortProcessV2(arr, index[1] +1, R);
    }

}
