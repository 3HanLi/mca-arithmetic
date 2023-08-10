package com.wy.mca.structure.tree.binary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description
 * @Author wangyong01
 * @Date 2022/5/15 7:58 下午
 * @Version 1.0
 */
public class BinaryTreeMaxWidth {

    public static void main(String[] args) {
        /**
         *          0
         *      /       \
         *     1        2
         *  /   \      /
         * 3    4     5
         *      / \
         *     6   7
         */
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.setLeft(new BinaryTreeNode(1));
        root.setRight(new BinaryTreeNode(2));
        root.getLeft().setLeft(new BinaryTreeNode(3));
        root.getLeft().setRight(new BinaryTreeNode(4));
        root.getRight().setLeft(new BinaryTreeNode(5));
        root.getLeft().getRight().setLeft(new BinaryTreeNode(6));
        root.getLeft().getRight().setRight(new BinaryTreeNode(7));

        int maxWidth = getMaxWidth(root);
        System.out.println("二叉树最大宽度：" + maxWidth);
    }

    /**
     * 二叉树最大宽度：指的是每层的节点个数的最大值
     *
     * @param root
     * @return
     */
    public static int getMaxWidth(BinaryTreeNode root){
        //1.1 当前层的最后一个节点
        BinaryTreeNode curEnd = root;
        //1.2 下一层的最后一个节点
        BinaryTreeNode nextEnd = null;
        //1.3 当前层节点个数
        int curLevelNodes = 0;
        //1.4 历史层最大节点个数
        int maxLevelNodes = 0;

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            BinaryTreeNode curNode = queue.poll();
            //2.1 让nextEnd走到最后，作为下一次遍历的curEnd
            if (null != curNode.getLeft()){
                queue.add(curNode.getLeft());
                nextEnd = curNode.getLeft();
            }
            if (null != curNode.getRight()){
                queue.add(curNode.getRight());
                nextEnd = curNode.getRight();
            }
            //2.2 每经过一个节点，当前层节点数++
            curLevelNodes ++;

            //3 如果走到最后一个节点了，当前层遍历完毕，开始新的一层
            if (curNode == curEnd){
                maxLevelNodes = Math.max(curLevelNodes, maxLevelNodes);
                //3.1 新的一层遍历，需要重置当前节点个数为0
                curLevelNodes = 0;
                //3.2 设置curEnd为下一层的最后一个节点
                curEnd = nextEnd;
            }
        }

        return maxLevelNodes;
    }
}
