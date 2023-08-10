package com.wy.mca.arithmetic.sort;

import com.wy.mca.util.ArrUtil;
import com.wy.mca.util.HeapUtil;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author wangyong01
 */
public class HeapSort {

    public static void main(String[] args) {
        int round = 100;
        int range = 100;
        for (int i=0; i<round; i++){
            int[] arr = ArrUtil.generateRandomArray(10, range);
            int[] copyArr = Arrays.copyOf(arr, arr.length);

            sortSingle(arr);
//        sortBatch(arr);
            Arrays.sort(copyArr);

            System.out.println(Arrays.equals(arr, copyArr));
        }
    }

    /**
     * 堆说明
     * 1) 堆其实就是PriorityQueue，它是一颗完全二叉树，可以用数组表示，主要有大根堆和小根堆
     *    1.1 大根堆：每个节点都大于他的孩子节点，也就是说 根结点的键值是所有堆结点键值中最大者
     *    1.2 小根堆：每个节点都小于孩子节点，也就是说 根结点的键值是所有堆结点键值中最小者
     * 2) 完全二叉树
     *    2.1 如果一棵树是满二叉树，他就是完全二叉树；
     *    2.2 通俗点说：一棵树每一层，从左到右是连续的，依次变满，那么他就是完全二叉树
     * 3) 使用数组来表示完全二叉树（堆），举例说明
     *    3.1 arr={1,2,3,4,5,6,7}
     *    3.2 对应的堆结构为
     *                1
     *            2       3
     *          4  5    6  7
     * 4) 堆的特性：对于任意一个下标为i的元素
     *    4.1 左孩子下表：2 * i + 1
     *    4.2 右孩子下标：2 * i + 2
     *    4.3 父亲下标：i-1 / 2
     *
     * 堆排序解题思路
     * 1) 先将数组调整成大根堆
     *    1.1 依次添加元素，当新添加的元素比父节点大时，元素上浮     ==> HeapUtil.heapInsert
     * 2) 定义变量heapSize，表示堆中的长度，初始值为数组的长度      ==> HeapUtil.heapify
     *    2.1 将大根堆最大元素arr[0]和arr[heapSize-1]交换，然后heapSize--
     *    2.2 由于堆结构发生变化导致arr[0]可能不是最大元素，比较arr[0]和孩子节点，如果比自己大进行交换，此时index = maxChildIndex
     * 3) 继续2.1和2.2将该元素下沉，直到孩子节点都比自己小为止
     *
     * 说明
     * 1.1 如果元素是一个一个过来的，那么我们需要先将它调整成大根堆
     * 1.2 此时时间复杂度是 NLogN + NLogN ==> NLogN
     *
     * @param arr
     */
    public static void sortSingle(int[] arr){
        //1.1 现将数组调整成大根堆    时间复杂度 ==> NLogN
        for (int i=0; i<arr.length; i++){
            HeapUtil.heapInsert(arr, i);
        }

        //1.2 不断的修改arr[0]元素，然后继续保证大根堆 时间复杂度 ==> NLogN
        int heapSize = arr.length;
        ArrUtil.swap(arr, 0, --heapSize);
        while (heapSize > 0){
            HeapUtil.heapify(arr, 0, heapSize--);
            ArrUtil.swap(arr, 0, heapSize);
        }

    }

    /**
     * 1.1 如果元素是一批过来的，那么我们通过逆序heapify将它调整成大根堆
     * 1.2 此时时间复杂度是：N + NLogN ==> NLogN
     * 1.3 总结起来就是：这种方式的效率稍微高一些
     *
     * @param arr
     */
    public static void sortBatch(int[] arr){
        //1.1 逆序调整大根堆   时间复杂度 ==> N，原因分析
        //a) 叶子结点最大个数：N/2，无需调整   1 * N/2
        //b) 倒数第二层最大个数：N/4，调整次数：2 * N/4
        //c) 倒数第三层最大个数：N/8，调整次数：3 * N/8
        //d) 由此推算时间复杂度：N/2 + 2N/4 + 3N/8 + 4N/16 + 5N/32 ==> 2N

        for (int i=arr.length-1; i>=0; i--){
            HeapUtil.heapify(arr,i, arr.length);
        }

        //1.2 不断的修改arr[0]元素，然后继续保证大根堆 时间复杂度 ==> NLogN
        int heapSize = arr.length;
        ArrUtil.swap(arr, 0, --heapSize);
        while (heapSize > 0){
            HeapUtil.heapify(arr, 0, heapSize--);
            ArrUtil.swap(arr, 0, heapSize);
        }
    }

}
