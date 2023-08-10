package com.wy.mca.structure.tree.binary;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否是搜索二叉树
 * 1）搜索二叉树特性：对于任意一颗子树，左子树上面的节点小于根节点，右子树上面的节点大于根节点
 *
 * 2）常规思路解决
 *    2.1 中序遍历，如果元素一直在上升，就是搜索二叉树
 *
 * 3）递归套路解决
 *    3.1 问题分析
 *        x的左侧是搜索二叉树
 *        x的右侧是搜索二叉树
 *        x左侧的最大值比自己小
 *        x右侧的最小值比自己大
 *    3.2 要求
 *        x向左子树要如下信息：是否二叉搜索树 以及 最大值
 *        x向右子树要如下信息：是否二叉搜索树 以及 最小值
 *
 *        x向左右要的信息不一致，取并集
 *
 * @Author wangyong01
 * @Date 2022/5/18 2:32 下午
 * @Version 1.0
 */
public class IsSearchBinaryTree {

    public static void main(String[] args) {
        /**
         *              10
         *          /       \
         *         5        15
         *       /   \
         *      3    7
         *     /
         *    1
         */
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.setLeft(new BinaryTreeNode(5));
        root.setRight(new BinaryTreeNode(15));
        root.getLeft().setLeft(new BinaryTreeNode(3));
        root.getLeft().setRight(new BinaryTreeNode(7));
        root.getLeft().getLeft().setLeft(new BinaryTreeNode(1));

        boolean searchTree = isSearchTree(root);
        System.out.println("通过中序遍历查看是否搜索二叉树:" + searchTree);

        boolean searchTreeRecursive = isSearchTreeRecursive(root);
        System.out.println("通过递归套路查看是否是搜索二叉树:" + searchTreeRecursive);
    }

    /**
     * 中序遍历判断是否是搜索二叉树
     *
     * 解决方案：中序遍历，如果元素一直在上升，就是搜索二叉树
     *
     * @param binaryTreeNode
     * @return
     */
    public static boolean isSearchTree(BinaryTreeNode binaryTreeNode){
        if (null == binaryTreeNode){
            return true;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        push(queue, binaryTreeNode);

        int min = Integer.MIN_VALUE;
        while (!queue.isEmpty()){
            BinaryTreeNode curNode = queue.poll();
            if (curNode.getNo() < min){
                return false;
            }
            min = curNode.getNo();
            if (null != curNode.getRight()){
                queue.add(curNode.getRight());
            }
        }

        return true;
    }

    private static void push(Queue<BinaryTreeNode> queue, BinaryTreeNode head){
        if (null != head){
            queue.add(head);
            head = head.getLeft();
        }
    }

    public static boolean isSearchTreeRecursive(BinaryTreeNode binaryTreeNode){
        if (null == binaryTreeNode){
            return true;
        }
        SearchInfo searchInfo = processInfo(binaryTreeNode);
        return searchInfo.isSearchBinaryTree();
    }

    /**
     * 通过递归套路判断是否搜索二叉树
     *
     * @param binaryTreeNode
     * @return
     */
    private static SearchInfo processInfo(BinaryTreeNode binaryTreeNode){
        //不知道怎么处理时，返回空，交给上游调用 判空处理
        if (null == binaryTreeNode){
            return null;
        }
        //1.1 向左子树要如下信息：是否是搜索二叉树 以及 左子树最大值
        SearchInfo leftInfo = processInfo(binaryTreeNode.getLeft());
        //1.2 向右子树要如下信息：是否是搜索二叉树 以及 右子树最小值
        SearchInfo rightInfo = processInfo(binaryTreeNode.getRight());
        //1.3 此时向左右子树要的信息不一致，取并集

        //2 当前节点 X 要求左右子树返回相关信息，那么 X 自身也要返回这些信息，才能继续递归
        //2.1 当前子树是否是搜索二叉树
        boolean isSearchBinaryTree = true;
        if (null != leftInfo && !leftInfo.isSearchBinaryTree()){
            isSearchBinaryTree = false;
        }
        if (null != rightInfo && !rightInfo.isSearchBinaryTree()){
            isSearchBinaryTree = false;
        }
        if (null != leftInfo && leftInfo.getMax() >= binaryTreeNode.getNo()){
            isSearchBinaryTree = false;
        }
        if (null != rightInfo && rightInfo.getMin() <= binaryTreeNode.getNo()){
            isSearchBinaryTree = false;
        }

        //2.2 当前二叉树的最大值和最小值
        int max = binaryTreeNode.getNo();
        int min = binaryTreeNode.getNo();
        if (null != leftInfo){
            max = Math.max(max, leftInfo.getMax());
            min = Math.min(min, leftInfo.getMin());
        }
        if (null != rightInfo){
            max = Math.max(max, rightInfo.getMax());
            min = Math.min(min, rightInfo.getMin());
        }

        //3 返回以 X 为根节点的子树的相关信息
        return new SearchInfo(isSearchBinaryTree, max, min);
    }
}

@Data
@AllArgsConstructor
class SearchInfo{

    private boolean isSearchBinaryTree;

    private int max;

    private int min;

}
