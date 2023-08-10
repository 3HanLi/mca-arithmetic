package com.wy.mca.structure.heap;

import com.wy.mca.util.ArrUtil;
import com.wy.mca.util.HeapUtil;
import lombok.Data;

/**
 * 大根堆实现
 * 1) 添加和删除元素都会调整堆结构
 * 2) 时间复杂度为：O(NlogN)
 *
 * @author wangyong01
 */
@Data
public class MaxHeap {

    /**
     * 底层数组
     */
    private int[] arr;

    /**
     * 数组长度->大根堆元素个数
     */
    private int size;

    /**
     * 数组最大长度
     */
    private final int limit;

    public MaxHeap(int limit) {
        this.limit = limit;
        arr = new int[limit];
    }

    /**
     * 添加元素的同时，保持大根堆结构
     * @param ele
     */
    public void push(int ele){
        if (size == limit){
            throw new RuntimeException("大根堆已满");
        }
        arr[size] = ele;
        HeapUtil.heapInsert(arr,size++);
    }

    /**
     * 弹出元素之后，保持大根堆
     * @return
     */
    public int pop(){
        if (size == 0){
            throw new RuntimeException("大根堆为空");
        }
        int res = arr[0];
        ArrUtil.swap(arr, 0 , --size);
        HeapUtil.heapify(arr, 0, size);
        return res;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
