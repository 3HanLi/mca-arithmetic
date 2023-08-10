package com.wy.mca.structure.stack;

import com.wy.mca.common.DoubleListNodeQueue;

public class MyStackWithDoubleList {

    private DoubleListNodeQueue doubleListNodeQueue = new DoubleListNodeQueue();

    public void add(int value){
        doubleListNodeQueue.addFromHead(value);
    }

    public int pop(){
        return doubleListNodeQueue.popFromHead();
    }

    public boolean isEmpty(){
        return doubleListNodeQueue.isEmpty();
    }

}
