package com.wy.mca.structure.heap;


import com.wy.mca.common.Student;
import com.wy.mca.util.RandUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author wangyong01
 */
public class PriorityQueueClient {

    public static void main(String[] args) {
//        commonUse();
//        validate(100, 1000);
//        heapStudent();
        heapGreaterStudent();
    }

    /**
     * 优先队列（小根堆）使用
     */
    private static void commonUse(){
        //1.1 PriorityQueue默认是小根堆，可以通过指定比较器构建大根堆，如下：
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a,b)->b-a);
        priorityQueue.add(10);
        priorityQueue.add(8);
        priorityQueue.add(8);
        priorityQueue.add(12);

        System.out.println("PriorityQueue ===>");
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }

        //1.2 大根堆，时间复杂度:O(NlogN)
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.push(10);
        maxHeap.push(8);
        maxHeap.push(8);
        maxHeap.push(12);

        System.out.println("MaxHeap ===>");
        while (!maxHeap.isEmpty()){
            System.out.println(maxHeap.pop());
        }

        //1.3 普通大根堆，时间复杂度:O(N^2)
        CommonHeap commonHeap = new CommonHeap(10);
        commonHeap.push(10);
        commonHeap.push(8);
        commonHeap.push(8);
        commonHeap.push(12);

        System.out.println("CommonHeap ===>");
        while (!commonHeap.isEmpty()){
            System.out.println(commonHeap.pop());
        }
    }

    /***
     * 对数器验证 自定义大根堆和自定义普通堆结构
     *
     * @param size
     * @param range
     */
    private static void validate(int size, int range){
        MaxHeap maxHeap = new MaxHeap(size);
        CommonHeap commonHeap = new CommonHeap(size);

        for (int i=0; i<size; i++){
            int randomNum = RandUtil.generateRandomNum(range);
            maxHeap.push(randomNum);
            commonHeap.push(randomNum);
        }

        while (!maxHeap.isEmpty() && !commonHeap.isEmpty()){
            int maxHeapEle = maxHeap.pop();
            int maxCommonEle = commonHeap.pop();
            if (maxHeapEle != maxCommonEle){
                System.out.println("heap error");
            }else {
                System.out.println("maxHeapEle:" + maxHeapEle + ";maxCommonEle:" + maxCommonEle);
            }
        }
    }

    /**
     * 普通堆 对象属性发生变化时，无法及时调整堆结构
     */
    private static void heapStudent(){
        PriorityQueue<Student> studentPriorityQueue = new PriorityQueue<>((Comparator.comparingInt(Student::getAge)));

        Student student01 = new Student(1, 12, "name01");
        Student student02 = new Student(2, 8, "name02");
        Student student03 = new Student(3, 16, "name03");

        studentPriorityQueue.add(student01);
        studentPriorityQueue.add(student02);
        studentPriorityQueue.add(student03);

        while (!studentPriorityQueue.isEmpty()){
            System.out.println("===>" + studentPriorityQueue.poll().getId());
        }

        System.out.println("调整student02.age=18");
        PriorityQueue<Student> studentPriorityQueue2 = new PriorityQueue<>((Comparator.comparingInt(Student::getAge)));
        studentPriorityQueue2.add(student01);
        studentPriorityQueue2.add(student02);
        studentPriorityQueue2.add(student03);

        student02.setAge(18);
        while (!studentPriorityQueue2.isEmpty()){
            System.out.println("===>" + studentPriorityQueue2.poll().getId());
        }

    }

    private static void heapGreaterStudent(){
        HeapGreater<Student> studentPriorityQueue = new HeapGreater<>((Comparator.comparingInt(Student::getAge)));

        Student student01 = new Student(1, 12, "name01");
        Student student02 = new Student(2, 8, "name02");
        Student student03 = new Student(3, 16, "name03");

        studentPriorityQueue.push(student01);
        studentPriorityQueue.push(student02);
        studentPriorityQueue.push(student03);

        while (!studentPriorityQueue.isEmpty()){
            System.out.println("===>" + studentPriorityQueue.pop().getId());
        }

        System.out.println("调整student02.age=18");
        HeapGreater<Student> studentPriorityQueue2 = new HeapGreater<>((Comparator.comparingInt(Student::getAge)));
        studentPriorityQueue2.push(student01);
        studentPriorityQueue2.push(student02);
        studentPriorityQueue2.push(student03);

        student02.setAge(18);
        studentPriorityQueue2.resign(student02);
        while (!studentPriorityQueue2.isEmpty()){
            System.out.println("===>" + studentPriorityQueue2.pop().getId());
        }
    }

}
