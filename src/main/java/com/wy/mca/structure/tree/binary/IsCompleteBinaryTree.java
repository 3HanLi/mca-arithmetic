package com.wy.mca.structure.tree.binary;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description 判断是否为完全二叉树
 * @Author wangyong01
 * @Date 2022/5/17 9:30 下午
 * @Version 1.0
 */
public class IsCompleteBinaryTree {

    public static void main(String[] args) {
        /**
         *          0
         *      /       \
         *     1        2
         *  /   \      /
         * 3    4     5
         */
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.setLeft(new BinaryTreeNode(1));
        root.setRight(new BinaryTreeNode(2));
        root.getLeft().setLeft(new BinaryTreeNode(3));
        root.getLeft().setRight(new BinaryTreeNode(4));
        root.getRight().setLeft(new BinaryTreeNode(5));

        System.out.println("按层遍历判断是否完全二叉树:" + isCompleteBinaryTree(root));
        System.out.println("递归遍历判断是否完全二叉树:" + isCompleteBinaryTreeDp(root));
    }

    /**
     * 判断是否完全二叉树
     * 解题思路：
     * 1 按照层遍历，如果节点有右孩子，没有左孩子，说明不是完全二叉树
     * 2 当第一次遇到左右孩子不全的节点时，剩下的节点必须是叶子，否则不是完全二叉树
     * @param head
     * @return
     */
    public static boolean isCompleteBinaryTree(BinaryTreeNode head){
        if (null == head){
            return true;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(head);

        //1.1 是否遇见过孩子节点不全的节点
        boolean hasMetNotFullNode = false;
        while (!queue.isEmpty()){
            BinaryTreeNode curNode = queue.poll();
            //1.2 如果有右孩子，没有左孩子，说明不是完全二叉树
            if (null == curNode.getLeft() && null != curNode.getRight()){
                return false;
            }
            //1.3 如果遇到了孩子节点不全的节点 且 当前节点有孩子节点，说明不是完全二叉树
            if (hasMetNotFullNode && (null != curNode.getLeft() || null != curNode.getRight())){
                return false;
            }

            if (null != curNode.getLeft()){
                queue.add(curNode.getLeft());
            }
            if (null != curNode.getRight()){
                queue.add(curNode.getRight());
            }

            //1.4 孩子节点只要有一个为空，说明遇到了孩子节点不全的节点
            if (null == curNode.getLeft() || null == curNode.getRight()){
                hasMetNotFullNode = true;
            }
        }

        return true;
    }

    public static boolean isCompleteBinaryTreeDp(BinaryTreeNode root){
        CBInfo cbInfo = processInfo(root);
        return cbInfo.isCB();
    }

    /**
     * 1 问题分析：列可能性
     *   a)	x的左树是满二叉树，右树也是满二叉树，且高度一样
     *   b)	x的左树是满二叉树，右树也是满二叉树，左边高度 = 右边高度 + 1
     *   c) x的左树是完全二叉树，右树是满二叉树，左边高度 = 右边高度 + 1
     *   d)	x的左树是满二叉树，右树是完全二叉树，且高度一样
     *
     * 2 定义Info对象
     *   class Info {
     *       boolean isFull;
     *       boolean isCB;
     *       int height;
     *   }
     *
     * @param xNode
     * @return
     */
    private static CBInfo processInfo(BinaryTreeNode xNode){
        if (null == xNode){
            return new CBInfo(true, true, 0);
        }
        //1 processInfo
        CBInfo leftInfo = processInfo(xNode.getLeft());
        CBInfo rightInfo = processInfo(xNode.getRight());

        //2 根据可能性分析 ==> 属性赋值
        boolean isFull = leftInfo.isFull() && rightInfo.isFull() && leftInfo.getHeight() == rightInfo.getHeight();
        boolean isCB = false;
        if (leftInfo.isFull() && rightInfo.isFull() && (rightInfo.getHeight() - leftInfo.getHeight() == 1)){
            isCB = true;
        }
        if (leftInfo.isFull() && rightInfo.isFull() && (rightInfo.getHeight() == leftInfo.getHeight())){
            isCB = true;
        }
        if (leftInfo.isCB() && rightInfo.isFull() && (leftInfo.getHeight() - rightInfo.getHeight() == 1)){
            isCB = true;
        }
        if (leftInfo.isFull() && leftInfo.isCB() && (leftInfo.getHeight() == rightInfo.getHeight())){
            isCB = true;
        }
        int height = Math.max(leftInfo.getHeight(), rightInfo.getHeight()) + 1;

        //3 返回自身
        return new CBInfo(isFull, isCB, height);
    }
}

@Data
@AllArgsConstructor
class CBInfo{

    /**
     * 是否满二叉树
     */
    private boolean isFull;

    /**
     * 是否完全二叉树
     */
    private boolean isCB;

    /**
     * 高度
     */
    private int height;

}
