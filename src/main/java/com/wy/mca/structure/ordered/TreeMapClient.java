package com.wy.mca.structure.ordered;

import com.google.common.collect.Maps;

import java.util.TreeMap;

/**
 * 有序表简介
 * 1) 有序表TreeMap
 *    1.1 时间复杂度是O(logN)，可以像树一样查找节点和元素，也可以查找临近的元素
 * @author wangyong01
 */
public class TreeMapClient {

    public static void main(String[] args) {
        //TreeMap是有序表，时间复杂度是O(logN)
        TreeMap<Integer, String> treeMap = Maps.newTreeMap();
        treeMap.put(1, "I am 1");
        treeMap.put(7, "I am 7");
        treeMap.put(2, "I am 2");
        treeMap.put(10, "I am 10");
        treeMap.put(12, "I am 12");
        treeMap.put(8, "I am 8");

        //1.1 查看第一个Key
        System.out.println("firstKey:" + treeMap.firstKey());
        //1.2 查看最后一个Key
        System.out.println("lastKey:" + treeMap.lastKey());
        //1.3 最接近当前key的key是多少
        System.out.println("floorKey:" + treeMap.floorKey(11));
    }

}
