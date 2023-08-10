package com.wy.mca.structure.queue;

import java.util.Stack;

/**
 * 两个栈实现队列
 * 解题思路：
 * 1) 准备栈pushStack，popStack
 * 2) 在每次弹出元素的时候，检查能否把pushStack导入到popStack，规则是：popStack为空
 *
 * @author wangyong01
 */
public class TwoStackImplementQueue {

    private Stack<Integer> pushStack = new Stack<>();

    private Stack<Integer> popStack = new Stack<>();

    public void push(int ele){
        pushStack.push(ele);
    }

    /**
     * 弹出元素
     * @return
     */
    public int pop(){
        //弹出前尝试迁移
        movePushToPop();
        if (!popStack.isEmpty()){
            int pop = popStack.pop();
            //弹出后再次尝试迁移
            movePushToPop();
            return pop;
        }
        return -1;
    }

    /**
     * 尝试把pushStack迁移到popStack，规则：pushStack != null && popStack==null
     */
    private void movePushToPop(){
        if (popStack.isEmpty() && !pushStack.isEmpty()){
            while (!pushStack.isEmpty()){
                popStack.push(pushStack.pop());
            }
        }
    }

    public boolean isEmpty(){
        return popStack.isEmpty();
    }

}
