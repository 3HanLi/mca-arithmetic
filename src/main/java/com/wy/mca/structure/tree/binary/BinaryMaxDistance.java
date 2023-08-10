package com.wy.mca.structure.tree.binary;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回整颗二叉树的最大距离
 * 1）最大距离：指的是两个节点之间的节点数
 *
 * @Author wangyong01
 * @Date 2022/5/19 8:29 下午
 * @Version 1.0
 */
public class BinaryMaxDistance {

    public static void main(String[] args) {
        /**
         *              0
         *          /       \
         *         1        2
         *       /   \
         *      3    4
         *     /      \
         *    5       6
         *             \
         *             7
         */
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.setLeft(new BinaryTreeNode(1));
        root.setRight(new BinaryTreeNode(2));
        //root.getRight().setRight(new BinaryTreeNode(21));
        //root.getRight().getRight().setRight(new BinaryTreeNode(211));

        root.getLeft().setLeft(new BinaryTreeNode(3));
        root.getLeft().getLeft().setLeft(new BinaryTreeNode(5));

        root.getLeft().setRight(new BinaryTreeNode(4));
        root.getLeft().getRight().setRight(new BinaryTreeNode(6));
        root.getLeft().getRight().getRight().setRight(new BinaryTreeNode(7));

        int maxDistance = getMaxDistance(root);
        System.out.println("MaxDistance:" + maxDistance);
    }

    public static int getMaxDistance(BinaryTreeNode head){
        NodeDistanceInfo nodeDistanceInfo = processInfo(head);
        return nodeDistanceInfo.getMaxInstance();
    }

    /**
     * 解题思路：二叉树的递归套路
     * 1）情况分析
     *    1.1 最大距离不经过x节点
     *    1.2 最大距离经过x节点
     * 2）要求
     *    2.1 向左子树要如下信息：左子树的最大距离 和 高度
     *    2.2 向右子树要如下信息：右子树的最大距离 和 高度
     *    2.3 节点自身也要返回如下信息：以节点为根节点的树的最大距离 和 高度
     *
     * @param curNode
     * @return
     */
    public static NodeDistanceInfo processInfo(BinaryTreeNode curNode){
        if (null == curNode){
            return new NodeDistanceInfo(0, 0);
        }

        //1.1 向做右子树要如下信息：树的最大距离 和 高度
        NodeDistanceInfo leftNodeDistanceInfo = processInfo(curNode.getLeft());
        NodeDistanceInfo rightNodeDistanceInfo = processInfo(curNode.getRight());

        //2 当前节点也需要返回如下信息
        //2.1 以x节点为根节点的树的高度
        int height = Math.max(leftNodeDistanceInfo.getHeight(), rightNodeDistanceInfo.getHeight()) + 1;

        //2.2 左子树的最大距离
        int leftMaxDistance = leftNodeDistanceInfo.getMaxInstance();
        //右子树的最大距离
        int rightMaxDistance = rightNodeDistanceInfo.getMaxInstance();
        //经过x节点时最大距离
        int wholeMaxDistance = leftNodeDistanceInfo.getHeight() + rightNodeDistanceInfo.getHeight() + 1;
        //取三者最大距离
        int maxDistance = Math.max(Math.max(leftMaxDistance, rightMaxDistance), wholeMaxDistance);

        return new NodeDistanceInfo(maxDistance, height);
    }

}

@Data
@AllArgsConstructor
class NodeDistanceInfo{

    /**
     * 以当前节点为根节点的最大距离
     */
    private int maxInstance;

    /**
     * 以当前节点为根节点的树的高度
     */
    private int height;

}
