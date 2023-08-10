package com.wy.mca.arithmetic.union;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 动态岛屿问题
 * @Author wangyong01
 * @Date 2022/8/4 6:23 下午
 * @Version 1.0
 */
public class DynamicIslands {

    public static void main(String[] args) {
        //假设最后的矩阵如图，其中1是我们需要依次加入的K个元素
        int[][] matrix = {
                {1,1,1,0,0,1,1},
                {0,0,1,1,1,0,1},
                {1,0,0,1,0,0,1},
                {0,0,0,1,0,1,1}
        };

        int rows = matrix.length;
        int cols = matrix[0].length;
//        DynamicIslandsUnionFindArray dynamicIslandsUnionFind = new DynamicIslandsUnionFindArray(rows, cols);
        DynamicIslandsUnionFindMap dynamicIslandsUnionFind = new DynamicIslandsUnionFindMap();

        List<Integer> allIslands = new ArrayList<>();
        for (int i = 0; i< rows; i++){
            for (int j=0; j<cols; j++){
                //这里空降K个元素，直接基于数组
                if (matrix[i][j] == 1){
                    allIslands.add(dynamicIslandsUnionFind.land(i, j));
                }
            }
        }
        System.out.println(allIslands);
    }
}
