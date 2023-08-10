package com.wy.mca.structure.queue;

import lombok.Data;

/**
 * 用数组实现队列
 * 解题思路
 * 1) 准备变量
 *    begin：代表元素出队列后的下标
 *    end：代表元素添加后的下标
 *    limit：数组容量
 *    size：代表队列大小 size=0表示为空，不可出队列；size=limit，表示队列慢，不可加入元素
 * 2) 始终让end追赶begin，认为数组是个环形的
 * @author wangyong01
 */
@Data
public class ArrayImplementQueue {

    private int begin;

    private int end;

    private int limit;

    private int size;

    private int[] arr;

    public ArrayImplementQueue(int limit) {
        this.limit = limit;
        arr = new int[limit];
    }

    public void push(int ele){
        if (size == limit){
            System.out.println("队列已满");
            return;
        }
        size ++;
        arr[end] = ele;
        end = end == limit -1 ? 0 : end + 1;
    }

    public int pop(){
        if (size == 0){
            System.out.println("队列为空");
            return -1;
        }
        size -- ;
        int pop = arr[begin];
        begin = begin == limit -1 ? 0 : begin + 1;
        return pop;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
