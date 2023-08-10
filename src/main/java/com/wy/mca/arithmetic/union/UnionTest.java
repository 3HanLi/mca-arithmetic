package com.wy.mca.arithmetic.union;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description 并查集测试
 * @Author wangyong01
 * @Date 2022/7/19 5:33 下午
 * @Version 1.0
 */
public class UnionTest {

    public static void main(String[] args) {
        List<Integer> numList = Lists.newArrayList(1, 2, 3, 4, 5);

        UnionFindWithMap<Integer> unionFind = new UnionFindWithMap<>(numList);
        unionFind.unionSet(1,2);
        unionFind.unionSet(3,4);
        unionFind.unionSet(1,5);
        unionFind.unionSet(4,5);
        System.out.println("并查集进行合并");

        boolean sameSet = unionFind.isSameSet(1, 3);
        System.out.println("SameSet:" + sameSet);
    }

}
