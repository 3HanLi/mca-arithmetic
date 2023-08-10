package com.wy.mca.util;

/**
 * 堆元素的上浮和下沉
 *
 * @author wangyong01
 */
public class HeapUtil {

    /**
     * 元素上浮，举例说明
     * 1) 依次添加元素 10 8 6，此时数组为：arr = {10,8,6}，对应的堆结构为
     *       10
     *     8    6
     * 2) 继续添加元素12，此时堆结构为
     *       10
     *     8    6
     *   12
     * 3) 此时大根堆结构被破坏，需要向上调整，步骤如下
     *    3.1 比较当前元素和父元素的大小，如果比父节点大，向上调整，此时结构为
     *              10
     *           12   6
     *         8
     *    3.2 此时依然不满足大根堆结构，继续调整
     *
     * @param arr
     * @param index
     */
    public static void heapInsert(int[] arr, int index){
        while (arr[index] > arr[(index-1) / 2]){
            ArrUtil.swap(arr, index, (index-1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 元素下沉，举例说明
     *
     * 1) 现在我们有一个大顶堆，对应的数组为：arr={8,7,0,5,4}，堆结构为
     *          8
     *        7   0
     *      5   4
     * 2) 交换8和最后一个元素4，并逻辑删除8，此时堆结构为
     *          4
     *       7    0
     *     5  [8]
     * 3) 由于元素大根堆最大元素被交换，此时arr[0]可能不是最大元素，需要向下查找最大元素并交换，交换4和7，此时堆结构为
     *         7
     *       4   0
     *     5
     * 4) 以此类推，最终堆结构为
     *         7
     *       5  0
     *     4
     * @param arr   堆结构
     * @param index 一般传0即可
     * @param size  堆的数组元素个数
     */
    public static void heapify(int[] arr, int index, int size){
        int leftIndex = 2 * index +1;
        while (leftIndex < size){
            //1.1 获取孩子节点中较大元素的下标
            //1.2 leftIndex+1 == size，说明只有左孩子
            int largestIndex = leftIndex + 1 < size ? (arr[leftIndex] > arr[leftIndex + 1] ? leftIndex : leftIndex + 1) : leftIndex;
            if (arr[index] > arr[largestIndex]){
                break;
            }
            ArrUtil.swap(arr, index, largestIndex);
            index = largestIndex;
            leftIndex = 2 * index + 1;
        }
    }
}
