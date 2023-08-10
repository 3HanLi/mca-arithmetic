package com.wy.mca.structure.queue;

import com.wy.mca.common.DoubleListNodeQueue;

/**
 * 双向链表实现队列
 * @author wangyong01
 */
public class MyQueueWithDoubleList {

    private DoubleListNodeQueue doubleListNodeQueue = new DoubleListNodeQueue();

    public void add(int value){
        doubleListNodeQueue.addFromHead(value);
    }

    public int pop(){
        return doubleListNodeQueue.popFromTail();
    }

    public boolean isEmpty(){
        return doubleListNodeQueue.isEmpty();
    }

}
