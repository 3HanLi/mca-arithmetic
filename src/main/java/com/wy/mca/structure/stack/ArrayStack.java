package com.wy.mca.structure.stack;

/**
 * 用数组实现栈
 * 解题思路：
 * 1) 定义变量
 *    limit：栈空间大小
 *    index：元素压栈后的位置
 * 2) 压入栈元素index++；弹出栈元素index--
 * @author wangyong01
 */
public class ArrayStack {

    private int limit;

    private int index;

    private int[] arr;

    public ArrayStack(int limit) {
        this.limit = limit;
        arr = new int[limit];
    }

    public void push(int ele){
        if (index == limit){
            System.out.println("栈已满");
            return;
        }
        arr[index++] = ele;
    }

    public int pop(){
        if (index == 0){
            System.out.println("栈为空");
            return -1;
        }
        return arr[--index];
    }

    public int getSize(){
        return index;
    }

    public boolean isEmpty(){
        return index == 0;
    }
}
