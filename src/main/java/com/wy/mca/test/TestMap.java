package com.wy.mca.test;

import java.util.HashMap;
import java.util.Map;

public class TestMap {

    public static void main(String[] args) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("", 1);

        Integer integer = wordCountMap.get(new StringBuilder().toString());
        System.out.println("Count:" + integer);
    }
}
