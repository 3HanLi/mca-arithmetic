package com.wy.mca.test;

import com.google.common.collect.Lists;

import java.util.List;

public class TestList {

    public static void main(String[] args) {
        List<Integer> list1 = Lists.newArrayList(1,2,3);
        List<Integer> list2 = Lists.newArrayList(1,2,3);
        System.out.println(list1.retainAll(list2));
    }
}
