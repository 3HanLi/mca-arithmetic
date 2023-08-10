package com.wy.mca.structure.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现栈
 * 解题思路：
 * 1) 准备两个队列 popQueue 和 pushQueue
 * 2) 每次弹出前，将popQueue中size-1个元素移入到pushQueue中，然后弹出popQueue中元素
 * 3) 交换popQueue 和 pushQueue索引
 * @author wangyong01
 */
public class TwoQueueImplementStack {

    /**
     * 用于弹出元素的队列（或者说是存储元素的队列）
     */
    private Queue<Integer> popQueue = new LinkedList<>();

    /**
     * 用于临时转移的队列
     */
    private Queue<Integer> pushQueue = new LinkedList<>();

    public void push(int ele){
        popQueue.offer(ele);
    }

    public int pop(){
        int size = popQueue.size();
        if (size >= 1){
            //元素转移
            for (int i=0; i<size -1; i++){
                pushQueue.offer(popQueue.poll());
            }
            int pop = popQueue.poll();
            //交换索引
            swapIndex();
            return pop;
        }
        return -1;
    }

    private void swapIndex(){
        Queue<Integer> tempIndex = pushQueue;
        pushQueue = popQueue;
        popQueue = tempIndex;
    }

    public boolean isEmpty(){
        return popQueue.isEmpty();
    }

}
