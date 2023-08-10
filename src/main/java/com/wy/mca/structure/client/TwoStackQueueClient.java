package com.wy.mca.structure.client;

import com.wy.mca.structure.queue.TwoStackImplementQueue;

/**
 * 两个栈实现队列测试
 */
public class TwoStackQueueClient {

    public static void main(String[] args) {
        simpleUse();
    }

    private static void simpleUse(){
        TwoStackImplementQueue twoStackImplementQueue = new TwoStackImplementQueue();
        for (int i=1; i<=5; i++){
            twoStackImplementQueue.push(i);
        }
        for (int i=0; i<3; i++){
            System.out.println(twoStackImplementQueue.pop());
        }
        twoStackImplementQueue.push(6);
        twoStackImplementQueue.push(7);
        while (!twoStackImplementQueue.isEmpty()){
            System.out.println(twoStackImplementQueue.pop());
        }
    }

}
