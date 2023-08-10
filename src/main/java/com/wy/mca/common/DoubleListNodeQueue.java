package com.wy.mca.common;

import lombok.Data;

/**
 * 使用双向链表实现栈和队列
 *
 * @author wangyong01
 */
@Data
public class DoubleListNodeQueue {

    /**
     * 头结点
     */
    public DoubleListNode head;

    /**
     * 尾结点
     */
    public DoubleListNode tail;

    public void addFromHead(int value){
        DoubleListNode doubleListNode = new DoubleListNode(value);

        if (null == head){
            head = doubleListNode;
            tail = doubleListNode;
        }else {
            doubleListNode.next = head;
            head.pre = doubleListNode;
            head = doubleListNode;
        }
    }

    public void addFromTail(int value){
        DoubleListNode doubleListNode = new DoubleListNode(value);

        if (null == head){
            head = doubleListNode;
            tail = doubleListNode;
        }else {
            tail.next = doubleListNode;
            doubleListNode.pre = tail;
            tail = doubleListNode;
        }
    }

    public int popFromHead(){
        if (null == head){
            System.out.println("popFromHead empty");
            //这里不严谨，因为可能存在值为-1的元素
            return -1;
        }else {
            int value = head.val;
            if (head == tail){
                head = null;
                tail = null;
            }else {
                DoubleListNode nextNode = head.next;
                nextNode.pre = null;
                head = nextNode;
            }
            return value;
        }
    }

    public int popFromTail(){
        if (null == tail){
            System.out.println("popFromTail empty");
            return -1;
        }
        int value = tail.val;
        if (head == tail){
            head = null;
            tail = null;
        }else {
            DoubleListNode preNode = tail.pre;
            preNode.next = null;
            tail = preNode;
        }
        return value;
    }

    public boolean isEmpty(){
        return null == head;
    }
}
