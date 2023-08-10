package com.wy.mca.structure.tree.binary;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 是否满二叉树
 *
 * 满二叉树定义：树的每一层从左到右依次遍满，满足如下公式：树的结点数 = 2 ^ 高度 -1
 *
 * @Author wangyong01
 * @Date 2022/5/19 9:20 下午
 * @Version 1.0
 */
public class IsFullBinaryTree {

    public static void main(String[] args) {
        /**
         *              0
         *          /       \
         *         1        2
         */
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.setLeft(new BinaryTreeNode(1));
        root.setRight(new BinaryTreeNode(2));

        boolean fullBinaryTree = isFullBinaryTree(root);
        System.out.println("isFullBinaryTree:" + fullBinaryTree);
    }

    /**
     * 二叉树的递归套路调用
     * 1）列出主函数
     *
     * @param binaryTreeNode
     * @return
     */
    public static boolean isFullBinaryTree(BinaryTreeNode binaryTreeNode){
        if (null == binaryTreeNode){
            return true;
        }

        NodesHeightInfo nodesHeightInfo = processInfo(binaryTreeNode);
        return (nodesHeightInfo.getNodes() + 1) == (1 << nodesHeightInfo.getHeight());
    }

    /**
     * 2）处理汇总信息
     *
     * @return
     */
    public static NodesHeightInfo processInfo(BinaryTreeNode binaryTreeNode){
        if (null == binaryTreeNode){
            return new NodesHeightInfo(0, 0);
        }
        //1.1 向左右子树要如下信息：节点个数 和  树的高度
        NodesHeightInfo leftInfo = processInfo(binaryTreeNode.getLeft());
        NodesHeightInfo rightInfo = processInfo(binaryTreeNode.getRight());

        //2.1 以当前节点为根节点的二叉树的节点总数
        int nodes = leftInfo.getNodes() + rightInfo.getNodes() + 1;
        //2.2 以当前节点为根节点的二叉树的高度
        int height = Math.max(leftInfo.getHeight(), rightInfo.getHeight()) + 1;

        return new NodesHeightInfo(nodes, height);
    }

}

@Data
@AllArgsConstructor
class NodesHeightInfo{

    private int nodes;

    private int height;

}
