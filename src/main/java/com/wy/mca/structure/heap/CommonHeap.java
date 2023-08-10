package com.wy.mca.structure.heap;

import com.wy.mca.util.ArrUtil;
import lombok.Data;

/**
 * 普通大根堆结构
 * 1) 添加元素和弹出元素均不调整堆结构
 * 2) 时间复杂度：O(N^2)
 *
 * @author wangyong01
 */
@Data
public class CommonHeap {

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

    public CommonHeap(int limit) {
        this.limit = limit;
        arr = new int[limit];
    }

    /**
     * 添加元素
     * @param ele
     */
    public void push(int ele){
        if (size == limit){
            throw new RuntimeException("大根堆已满");
        }
        arr[size++] = ele;
    }

    /**
     * 弹出最大元素
     * @return
     */
    public int pop(){
        int maxIndex = 0;
        for (int i=1; i<size; i++){
            if (arr[i] > arr[maxIndex]){
                maxIndex = i;
            }
        }
        int res = arr[maxIndex];
        ArrUtil.swap(arr, maxIndex, --size);
        return res;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
