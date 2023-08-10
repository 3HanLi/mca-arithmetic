package com.wy.mca.test;

import cn.hutool.core.util.RandomUtil;

public class TestRandom {

    public static void main(String[] args) {

        int randomInt = RandomUtil.randomInt(1, 3);
        System.out.println(randomInt);

    }
}
