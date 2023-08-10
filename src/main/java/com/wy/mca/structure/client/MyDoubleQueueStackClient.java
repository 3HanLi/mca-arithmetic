package com.wy.mca.structure.client;


import com.wy.mca.structure.queue.MyQueueWithDoubleList;
import com.wy.mca.structure.stack.MyStackWithDoubleList;
import com.wy.mca.util.RandUtil;

import java.util.Stack;

public class MyDoubleQueueStackClient {

    public static void main(String[] args) {
//        simpleUse();
        validate();
    }

    /**
     * 简单测试
     */
    private static void simpleUse(){
        System.out.println("Test MyStackWithDoubleList");
        MyStackWithDoubleList myStackWithDoubleList = new MyStackWithDoubleList();
        myStackWithDoubleList.add(1);
        myStackWithDoubleList.add(2);
        System.out.println(myStackWithDoubleList.pop());
        System.out.println(myStackWithDoubleList.pop());
        System.out.println(myStackWithDoubleList.pop());

        System.out.println("Test MyQueueWithDoubleList");
        MyQueueWithDoubleList myQueueWithDoubleList = new MyQueueWithDoubleList();
        myQueueWithDoubleList.add(1);
        myQueueWithDoubleList.add(2);
        System.out.println(myQueueWithDoubleList.pop());
        System.out.println(myQueueWithDoubleList.pop());
        System.out.println(myQueueWithDoubleList.pop());
    }

    /**
     * 对数器验证自定义双端队列和栈
     */
    private static void validate(){
        int maxSize = 100;
        //这里只验证栈，省去队列代码验证
        Stack<Integer> stack = new Stack<>();
        MyStackWithDoubleList myStackWithDoubleList = new MyStackWithDoubleList();
        for (int i=0; i<maxSize; i++){
            int stackEle = RandUtil.generateRandomNum(100);
            if (stack.isEmpty()){
                stack.push(stackEle);
                myStackWithDoubleList.add(stackEle);
            }else {
                if (Math.random() < 0.5){
                    stack.add(stackEle);
                    myStackWithDoubleList.add(stackEle);
                }else {
                    if (stack.pop().intValue() != myStackWithDoubleList.pop()){
                        System.out.println("Stack error");
                    }
                }
            }
        }
    }

}
