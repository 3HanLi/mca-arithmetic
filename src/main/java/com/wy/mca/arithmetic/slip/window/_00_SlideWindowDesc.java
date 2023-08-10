package com.wy.mca.arithmetic.slip.window;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description 滑动窗口
 * 1、滑动窗口描述
 * 2、案例说明
 * 3、如何实现
 * @Author wangyong01
 * @Date 2023/5/8 8:54 PM
 * @Version 1.0
 */
public class _00_SlideWindowDesc {

    /**
     * 活动窗口简单案例模拟
     */
    @Test
    public void slipWindow() {
        int[] arr = {6, 5, 0, 2, 4, 7, 5};
        Deque<Integer> deque = new LinkedList();

        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && deque.getLast() <= arr[i]){
                deque.removeLast();
            }
            deque.addLast(arr[i]);
        }
        System.out.println("Window R in:" + deque);

    }
}
