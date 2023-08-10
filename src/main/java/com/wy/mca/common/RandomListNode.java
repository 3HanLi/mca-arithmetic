package com.wy.mca.common;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 单向链表定义
 * 1）由于random可能往回指，重写toString和equals时要注意，否则可能导致死循环，从而抛出异常
 * @author boss
 */
public class RandomListNode {

    public int val;

    public RandomListNode next;

    public RandomListNode random;

    public RandomListNode(){}

    public RandomListNode(int x) { this.val = x; }

    public int length(){
        int i= 1;
        RandomListNode curr = this.next;
        while (curr != null){
            i ++;
            curr = curr.next;
        }
        return i;
    }

    public void print(){
        RandomListNode curr = this;
        System.out.println("Print value:\t");
        while (null != curr){
            System.out.print(curr.val + "\t");
            curr = curr.next;
        }

        System.out.println();

        curr = this;
        System.out.println("Print random value:\t");
        while (null != curr){
            System.out.print((null != curr.random ? curr.random.val : "--") + "\t");
            curr = curr.next;
        }
        System.out.println();
    }

    public List<Integer> elements(){
        List<Integer> eleList = Lists.newArrayList();
        RandomListNode curr = this.next;
        eleList.add(this.val);
        while (curr != null){
            eleList.add(curr.val);
            curr = curr.next;
        }
        return eleList;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public RandomListNode getNext() {
        return next;
    }

    public void setNext(RandomListNode next) {
        this.next = next;
    }

    public RandomListNode getRandom() {
        return random;
    }

    public void setRandom(RandomListNode random) {
        this.random = random;
    }
}
