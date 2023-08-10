package com.wy.mca.arithmetic.union;

/**
 * @Description 动态岛屿问题
 * 1、问题描述
 *    现在有一个m * n矩阵的数组，初始值都是0，现在空降k个元素，每空降一个元素，就记录下当前的联通区域
 * 2、时间复杂度：
 *    O(M * N) + O(k)
 *
 * @Author wangyong01
 * @Date 2022/8/4 6:23 下午
 * @Version 1.0
 */
public class DynamicIslandsUnionFindArray {

    /**
     * 节点 和 父节点的关系
     */
    private int[] parents;

    /**
     * sizes[i]：只有i是头部节点时，size[i]才有意义，表示该头部节点下元素数量
     */
    private int[] size;

    /**
     * 集合数量
     */
    private int sets;

    /**
     * 矩阵大小
     */
    private int rows;
    private int cols;

    public DynamicIslandsUnionFindArray(int rowN, int colN) {
        parents = new int[rowN * colN];
        size = new int[rowN * colN];
        rows = rowN;
        cols = colN;
    }

    public void union(int i1, int j1, int i2, int j2) {
        //1.1 检查越界
        if (i1 < 0 || i1 == rows || j1 < 0 || j1 == cols || i2 < 0 || i2 == rows || j2 < 0 || j2 == cols) {
            return;
        }

        int index2 = getIndex(i2, j2);
        //1.2 当前节点下的元素数量不为空时，才可进行合并
        if (size[index2] == 0) {
            return;
        }

        //1.3 合并逻辑
        int index1 = getIndex(i1, j1);
        int head1 = findHead(index1);
        int head2 = findHead(index2);

        if (head1 != head2){
            int len1 = size[head1];
            int len2 = size[head2];

            if (len1 >= len2) {
                parents[head2] = head1;
                size[head1] += size[head2];
                //注意：合并时不要清空小集合下的元素数量，因为：1.2初要用做判断
            } else {
                parents[head1] = head2;
                size[head2] += size[head1];
            }
            sets --;
        }
    }

    private int findHead(int index) {
        while (index != parents[index]) {
            index = parents[index];
        }
        return index;
    }

    /**
     * 计算 (i,j)对应的一维数组的下标
     * @param i
     * @param j
     * @return
     */
    private int getIndex(int i, int j) {
        return i * cols + j;
    }

    /**
     * 在(i,j)空降一个元素
     * @param i
     * @param j
     * @return
     */
    public int land(int i, int j) {
        int index = getIndex(i, j);

        //如果当前节点下面没有挂元素，说明可以空降，然后在空降后尝试合并
        if (size[index] == 0) {
            //1.1 当前节点 指向 自己
            parents[index] = index;
            //1.2 当前节点为头部节点下的元素数量
            size[index] = 1;
            //1.3 集合数量 +1，然后在合并时 -1
            sets++;

            //1.4 依次尝试和上下左右的元素进行合并
            union(i, j, i - 1, j);
            union(i, j, i + 1, j);
            union(i, j, i, j - 1);
            union(i, j, i, j + 1);
        }

        return sets;
    }

}
