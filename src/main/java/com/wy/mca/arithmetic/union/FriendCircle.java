package com.wy.mca.arithmetic.union;

/**
 * @Description 朋友圈问题
 * @Author wangyong01
 * @Date 2022/7/22 6:43 下午
 * @Version 1.0
 */
public class FriendCircle {

    public static void main(String[] args) {
        int[][] circles = {
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1}
        };

        int friendCircles = getFriendCircles(circles);
        System.out.println("FriendCircles:" + friendCircles);
    }

    /**
     * 1 问题描述
     *   1.1 现在有一个长度为N的正方形矩阵（二维数组），元素为1代表互相认识，对角线都为1，因为自己肯定认识自己
     *   1.2 计算这个矩阵中有多少个朋友圈
     *
     * 2 案例说明
     *   2.1 矩阵中[0,2]、[0,4]、[1,3] 之间相互认识，那么朋友圈为：{0,2,4} 和 {1,3}，一共两个
     *
     * 3 解决思路
     *   3.1 先假设每个元素都是一个单独的小集合，即：{0} {1} {2} {3} {4}
     *   3.2 遍历矩阵的右上角，如果circles[i][j] = 1，说明元素互相认识，进行合并
     *   3.3 具体步骤
     *       a) parents[i]=k，表示i的父节点是k
     *       b) size[i]=k，表示当i是头部节点时，集合的数量
     *       c) sets，表示集合的数量，初始值是5
     *
     * @param circles
     * @return
     */
    public static int getFriendCircles(int[][] circles) {
        FriendUnionFindWithArray friendUnionFindWithArray = new FriendUnionFindWithArray(circles.length);

        for (int i = 0; i < circles.length; i++) {
            //1.1 只遍历右上部分，因为对角线表示自己认识自己，而两个元素互相认识是对称的
            for (int j = i + 1; j < circles.length; j++) {
                //1.2 i和j认识时，进行合并
                if (circles[i][j] == 1) {
                    friendUnionFindWithArray.union(i, j);
                }
            }
        }

        return friendUnionFindWithArray.getCircles();
    }

}


