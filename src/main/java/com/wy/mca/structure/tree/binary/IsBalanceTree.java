package com.wy.mca.structure.tree.binary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 平衡二叉树
 * @Author wangyong01
 * @Date 2022/5/18 11:30 上午
 * @Version 1.0
 */
public class IsBalanceTree {

    public static void main(String[] args) {
        /**
         *              0
         *          /       \
         *         1        2
         *       /   \
         *      3    4
         *     /
         *    6
         */
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.setLeft(new BinaryTreeNode(1));
        root.setRight(new BinaryTreeNode(2));
        root.getLeft().setLeft(new BinaryTreeNode(3));
        root.getLeft().setRight(new BinaryTreeNode(4));
        root.getLeft().getLeft().setLeft(new BinaryTreeNode(6));

        boolean balanceTree = isBalanceTree(root);
        System.out.println("是否是平衡二叉树:" + balanceTree);
    }

    /**
     * 是否是平衡二叉树
     * 1、平衡二叉树定义：定任意一个节点，两颗子树的高度之差不超过1
     * 2、解题思路：使用二叉树递归套路
     *    2.1 情况分析
     *        a) X的左树是平衡二叉树
     *        b) X的右树是平衡二叉树
     *        c) X左树的高度和右树的高度之差不超过2
     *    2.2 要求
     *        a) X需要向左右节点获取2点信息
     *           x的左侧是否平衡二叉树，以及高度
     *           x的右侧是否平衡二叉树，以及高度
     *        b) X自身也要做到返回这两点信息，这样我们的递归才能继续
     *
     * @param binaryTreeNode
     * @return
     */
    public static boolean isBalanceTree(BinaryTreeNode binaryTreeNode){
        Info info = processInfo(binaryTreeNode);
        return info.isBalance();
    }

    public static Info processInfo(BinaryTreeNode binaryTreeNode){
        if (null == binaryTreeNode){
            return new Info(true, 0);
        }

        //1.1 向左子树要两点信息：是否平衡二叉树  以及 树的高度
        Info leftNode = processInfo(binaryTreeNode.getLeft());
        //1.2 向右子树要两点信息：是否平衡二叉树  以及 树的高度
        Info rightNode = processInfo(binaryTreeNode.getRight());

        //2.1 是否是平衡二叉树
        boolean isBalanceTree = leftNode.isBalance() && rightNode.isBalance() && Math.abs(leftNode.getHeight() - rightNode.getHeight()) <= 1;
        //2.2 树的高度
        int height = Math.max(leftNode.getHeight(), rightNode.getHeight()) + 1;

        //2 向左子树和右子树要两点信息，那么节点自身也要提供这两点信息，保证递归可以继续
        return new Info(isBalanceTree, height);
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Info{

    private boolean isBalance;

    private int height;
}
