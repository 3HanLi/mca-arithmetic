package com.wy.mca.arithmetic.union;

import java.util.Stack;

/**
 * @Description 岛屿问题 通过数组 实现并查集
 * 目的：使用数组压缩空间、提高效率
 * @Author wangyong01
 * @Date 2022/8/3 8:34 下午
 * @Version 1.0
 */
public class IsLandUnionFindArray {

    private int[] parents;

    private int[] size;

    private int colN;

    private int sets;

    public IsLandUnionFindArray(int[][] matrix) {
        int rowN = matrix.length;
        colN = matrix[0].length;
        //1.1 将二维数组 转为一维数组，按照每一行依次排开
        parents = new int[rowN * colN];
        size = new int[rowN * colN];

        for (int i = 0; i < rowN; i++) {
            for (int j = 0; j < colN; j++) {
                //1.2 只初始化有值的元素
                if (matrix[i][j] == 1) {
                    int index = getIndex(i, j);
                    parents[index] = index;
                    size[index] = 1;
                    sets++;
                }
            }
        }
    }

    public boolean isSameSet(int index1, int index2) {
        return findHead(index1) == findHead(index2);
    }

    private int findHead(int index) {
        Stack<Integer> stack = new Stack<>();

        while (index != parents[index]) {
            index = parents[index];
            stack.push(index);
        }

        while (!stack.isEmpty()) {
            Integer curIndex = stack.pop();
            parents[curIndex] = index;
        }

        return index;
    }

    public void union(int i1, int j1, int i2, int j2) {
        int index1 = getIndex(i1, j1);
        int index2 = getIndex(i2, j2);

        int head1 = findHead(index1);
        int head2 = findHead(index2);

        if (head1 != head2) {
            int len1 = size[head1];
            int len2 = size[head2];

            if (len1 >= len2) {
                parents[head2] = head1;
                size[head1] += size[head2];
                size[head2] = 0;
            } else {
                parents[head1] = head2;
                size[head2] += size[head1];
                size[head1] = 0;
            }
            sets --;
        }
    }

    public int getSets() {
        return sets;
    }

    private int getIndex(int rowIndex, int colIndex) {
        return rowIndex * colN + colIndex;
    }

}
