package com.wy.mca.structure.client;


import com.wy.mca.structure.queue.ArrayImplementQueue;
import com.wy.mca.structure.stack.ArrayStack;
import com.wy.mca.util.RandUtil;

import java.util.LinkedList;
import java.util.Queue;

public class MyArrayQueueClient {

    public static void main(String[] args) {
//        simpleUse();
        validate();
    }

    private static void simpleUse(){
        System.out.println("压入栈元素");
        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);

        System.out.println("弹出栈元素");
        while (!arrayStack.isEmpty()){
            System.out.println(arrayStack.pop());
        }

        System.out.println("压入队列元素");
        ArrayImplementQueue arrayImplementQueue = new ArrayImplementQueue(3);
        arrayImplementQueue.push(1);
        arrayImplementQueue.push(2);
        arrayImplementQueue.push(3);
        arrayImplementQueue.push(4);

        System.out.println("弹出队列元素");
        while (!arrayImplementQueue.isEmpty()){
            System.out.println(arrayImplementQueue.pop());
        }

    }

    private static void validate(){
        int maxSize = 100;
        //这里只验证栈，省去队列代码验证
        ArrayImplementQueue arrayImplementQueue = new ArrayImplementQueue(100);
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<maxSize; i++){
            int stackEle = RandUtil.generateRandomNum(100);
            if (arrayImplementQueue.isEmpty()){
                arrayImplementQueue.push(stackEle);
                queue.offer(stackEle);
            }else {
                if (Math.random() < 0.5){
                    arrayImplementQueue.push(stackEle);
                    queue.offer(stackEle);
                }else {
                    if (arrayImplementQueue.pop() != queue.poll()){
                        System.out.println("Queue error");
                    }
                }
            }
        }
        System.out.println("finished well");
    }

}
