package com.wy.mca.structure.stack;

import java.util.Stack;

/**
 * 返回一个特殊的栈，在基本功能的基础上，返回栈中最小元素，要求时间复杂度是O(1)
 * 解题思路：
 * 1) 准备2个栈，数据栈dataStack 和 最小数栈minStack
 * 2) 压入数据栈dataStack的时候，同步压入最小值栈minStack；如果压入元素比minStack栈顶元素小，直接压入
 *    否则，复制minStack栈顶元素压入
 * @author wangyong01
 */
public class MinStack {

    /**
     * 数据栈
     */
    private Stack<Integer> dataStack = new Stack<>();

    /**
     * 最小值栈
     */
    private Stack<Integer> minStack = new Stack<>();

    public void push(int ele){
        dataStack.push(ele);
        if (!minStack.isEmpty()){
            ele = ele < minStack.peek() ? ele : minStack.peek();
        }
        minStack.push(ele);
    }

    public int pop(){
        if (dataStack.isEmpty()){
            return -1;
        }
        Integer pop = dataStack.pop();
        minStack.pop();
        return pop;
    }

    public int getMin(){
        if (dataStack.isEmpty()){
            return -1;
        }
        return minStack.peek();
    }

    public boolean isEmpty(){
        return dataStack.isEmpty();
    }
}
