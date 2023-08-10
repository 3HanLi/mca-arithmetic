package com.wy.mca.structure.client;

import com.wy.mca.structure.stack.TwoQueueImplementStack;

/**
 * 两个队列实现栈
 * @author wangyong01
 */
public class TwoQueueStackClient {

    public static void main(String[] args) {
        simpleUse();
    }

    private static void simpleUse(){
        TwoQueueImplementStack twoQueueImplementStack = new TwoQueueImplementStack();
        for (int i=1; i<=5; i++){
            twoQueueImplementStack.push(i);
        }

        System.out.println(twoQueueImplementStack.pop());

        twoQueueImplementStack.push(6);
        twoQueueImplementStack.push(7);
        while (!twoQueueImplementStack.isEmpty()){
            System.out.println(twoQueueImplementStack.pop());
        }
    }

}
