package com.wy.mca.structure.client;

import com.wy.mca.structure.stack.MinStack;

/**
 * @author wangyong01
 */
public class MinStackClient {

    public static void main(String[] args) {
        simpleUse();
    }

    private static void simpleUse(){
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(4);
        minStack.push(5);
        minStack.push(1);

        while (!minStack.isEmpty()){
            System.out.println(minStack.getMin() + "--->" + minStack.pop());
        }
    }
}
