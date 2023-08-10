package com.wy.mca.arithmetic.dynamic.recursion;

import java.util.Stack;

/**
 * 问题描述：逆序一个栈，要求：不实用额外数据结构
 *
 * 解题思路：
 * 1) 定义方法getStackBottomEle，作用：返回栈底元素，栈上面的元素压下来
 *    1.1 栈不为空则弹出元素，获取最后一个元素后返回
 *    1.2 将倒数第2-N个元素逆序压入栈中
 * 2) 定义方法reverse，作用：栈不为空则持续调用
 *    2.1 调用方法getStackBottomEle返回最后第一个元素（栈底元素）
 *    2.2 继续调用reverse方法获取最后一个元素（也就是栈底倒数第二个元素）
 *    2.3 总之，栈底元素最后压入
 *
 */
public class _04_ReverseStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        reverse(stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    /**
     * 逆序栈
     *
     * @param stack
     */
    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        //1.1 返回栈底元素
        int stackBottomEle = getStackBottomEle(stack);
        //1.2 只要栈不为空，一直进行递归，那么最终弹出的一定是栈顶元素
        reverse(stack);
        //1.3 逆序压入栈底元素，相当于依次压入：倒数第一个 ~ 倒数第N个
        stack.push(stackBottomEle);
    }

    /**
     * 返回栈底元素，其他元素向下移动，比如：stack={1,2,3}，返回3，stack={1,2}
     *
     * @param stack
     * @return
     */
    private static int getStackBottomEle(Stack<Integer> stack){
        //1.2 获取倒数第N(N>=2)个元素
        Integer result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            //1.1 获取到最后一个元素
            int lastEle = getStackBottomEle(stack);
            //1.3 将倒数第二个元素压入栈中
            stack.push(result);
            //1.4 返回最后一个元素
            return lastEle;
        }
    }
}
