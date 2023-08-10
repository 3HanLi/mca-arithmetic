package com.wy.mca.test;

import java.util.Stack;

/**
 * @Description
 * @Author wangyong01
 * @Date 2023/3/21 9:17 下午
 * @Version 1.0
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);

//        print(stack);

        int bottom = getBottom(stack);
        System.out.println("bottom==>" + bottom);

        print(stack);
    }

    private static void print(Stack<Integer> stack){
        while (!stack.isEmpty()){
            System.out.println("==>" + stack.pop());
        }
    }

    /**
     * 返回栈顶元素，上面元素落下来
     * @param stack
     * @return
     */
    public static int getBottom(Stack<Integer> stack){
        Integer pop = stack.pop();
        if (stack.isEmpty()){
            return pop;
        } else {
            int cur = getBottom(stack);
            stack.push(pop);
            return cur;
        }
    }
}
