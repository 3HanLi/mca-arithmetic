package com.wy.mca.arithmetic.union;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 岛屿问题
 * @Author wangyong01
 * @Date 2022/8/1 8:51 下午
 * @Version 1.0
 */
public class IsLands {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1,0,0,1,1},
                {0,0,1,1,1,0,1},
                {1,0,0,1,0,0,1},
                {0,0,0,1,0,1,1}
        };

        int[][] cloneMatrix = {
                {1,1,1,0,0,1,1},
                {0,0,1,1,1,0,1},
                {1,0,0,1,0,0,1},
                {0,0,0,1,0,1,1}
        };

        int islands = getIslands(matrix);
        System.out.println("Islands :" + islands);

        int unionIslands = getUnionIslands(cloneMatrix);
        System.out.println("unionIslands:" + unionIslands);

        int unionIslandsOneDimension = getUnionIslandsOneDimension(cloneMatrix);
        System.out.println("unionIslandsOneDimension:" + unionIslandsOneDimension);
    }

    /**
     * 1、岛屿问题描述
     *    给定一个二维数组matrix，里面的值不是1就是0，上、下、左、右相邻的1认为是一片岛，计算岛屿的数量
     * 2、案例说明
     *    代码中的岛屿数量为3个
     *
     * 方法一：暴力递归解决岛屿问题
     *
     * @param matrix
     * @return
     */
    public static int getIslands(int[][] matrix){
        int islands = 0;
        int rowL = matrix.length;
        int colL = matrix[0].length;

        //1 这里的时间复杂度是：M * N，原因分析：当前元素 会被遍历1次 + 被上下左右元素碰一次，那么所有元素被访问的次数就是：M * N + 4 * M * N = 5 * M * N；
        //  那么时间复杂度就是：O(M * N)
        for (int i=0; i<rowL; i++){
            for (int j=0; j<colL; j++){
                if (matrix[i][j] == 1){
                    //1.1 碰到1，说明有一个岛屿
                    islands ++;
                    //1.2 将当前元素相连的元素1都感染为其他值（如：0 或者 2）
                    infect(matrix, i, j);
                }
            }
        }

        return islands;
    }

    /**
     * 感染(i,j)相连的所有元素1，感染方式是：上下左右
     *
     * @param matrix
     * @param i
     * @param j
     */
    private static void infect(int[][] matrix, int i, int j){
        //1.1 如果坐标越界 或者 当前元素不是1，说明相连的元素都已经被感染
        if (i< 0 || i == matrix.length || j < 0 || j == matrix[0].length || matrix[i][j] != 1){
            return;
        }

        //1.2 如果当前元素为1，将其感染成0，然后继续感染
        matrix[i][j] = 0;
        infect(matrix, i, j-1);
        infect(matrix, i, j+1);
        infect(matrix, i-1, j);
        infect(matrix, i+1, j);
    }

    /**
     * 方法二：通过[Map]并查集解决岛屿联通问题
     *
     * @param matrix
     * @return
     */
    public static int getUnionIslands(int[][] matrix){
        //1.1 复合合并条件的元素集合
        List<Object> objectList = new ArrayList<>();

        //1.2 这里先将基本数据类型的二维数组 转为 对象类型的二维数组，因为我们前面的并查集合并时不支持基本数据类型
        Object[][] objects = new Object[matrix.length][matrix[0].length];
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                if (matrix[i][j] == 1){
                    objects[i][j] = new Object();
                    objectList.add(objects[i][j]);
                }
            }
        }

        //1.3 初始化集合
        UnionFindWithMap<Object> unionFind = new UnionFindWithMap(objectList);

        //2 遍历数组中的每个元素，如果 当前元素和左边元素均不为空 或者 当前元素和上面元素均不为空，可以进行合并；
        //2.1 下面为了防止边界判断，先遍历第一行
        for (int j=1; j<objects[0].length; j++){
            if (objects[0][j] != null && objects[0][j-1] != null){
                unionFind.unionSet(objects[0][j], objects[0][j-1]);
            }
        }

        //2.2 在遍历第一列
        for (int i=1; i<objects.length; i++){
            if (objects[i][0] != null && objects[i-1][0] != null){
                unionFind.unionSet(objects[i][0], objects[i-1][0]);
            }
        }

        //2.3 从(1,1)开始遍历后续元素
        for (int i=1; i<objects.length; i++){
            for (int j=1; j<objects[0].length; j++){
                //当前元素和上面元素不为空，进行合并
                if (objects[i][j] != null && objects[i-1][j] != null){
                    unionFind.unionSet(objects[i][j], objects[i-1][j]);
                }
                //当前元素和左边元素不为空，进行合并
                if (objects[i][j] != null && objects[i][j-1] != null){
                    unionFind.unionSet(objects[i][j], objects[i][j-1]);
                }
            }
        }

        return unionFind.getUnionSize();
    }

    /**
     * 方法三：通过[数组]并查集解决
     *
     * @param matrix
     * @return
     */
    public static int getUnionIslandsOneDimension(int[][] matrix){
        //1 将二维数组 转为 一维数组，使用一维数组来解决并查集问题
        IsLandUnionFindArray unionFindArray = new IsLandUnionFindArray(matrix);

        int rowN = matrix.length;
        int colN = matrix[0].length;
        //2.1 下面为了防止边界判断，先遍历第一行
        for (int j=1; j<colN; j++){
            if (matrix[0][j] == 1 && matrix[0][j-1] == 1){
                unionFindArray.union(0, j, 0, j-1);
            }
        }

        //2.2 在遍历第一列
        for (int i=1; i<rowN; i++){
            if (matrix[i][0] == 1 && matrix[i-1][0] == 1){
                unionFindArray.union(i,0, i-1, 0);
            }
        }

        //2.3 从(1,1)开始遍历后续元素
        for (int i=1; i<rowN; i++){
            for (int j=1; j<colN; j++){
                //当前元素和上面元素不为空，进行合并
                if (matrix[i][j] == 1 && matrix[i][j-1] == 1){
                    //根据(i,j) 和 (i,j-1)定位到需要合并的元素，然后进行合并
                    unionFindArray.union(i, j, i, j-1);
                }
                if (matrix[i][j] == 1 && matrix[i-1][j] == 1){
                    unionFindArray.union(i,j, i-1, j);
                }
            }
        }

        return unionFindArray.getSets();
    }

}

