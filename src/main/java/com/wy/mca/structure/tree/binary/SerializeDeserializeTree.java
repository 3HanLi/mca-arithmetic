package com.wy.mca.structure.tree.binary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description 序列化和反序列化二叉树
 * @Author wangyong01
 * @Date 2022/4/13 6:01 下午
 * @Version 1.0
 */
public class SerializeDeserializeTree {

    public static void main(String[] args) {
        /**
         *      1
         *    /  \
         *   2    3              二叉树先序遍历序列化 ==》      1	2	-1	-1	3	-1	-1
         */

        BinaryTreeNode root = new BinaryTreeNode(1, "1");
        root.setLeft(new BinaryTreeNode(2, "2"));
        root.setRight(new BinaryTreeNode(3, "3"));

        System.out.println("==============先序遍历序列化结果==============");
        Queue<Integer> preSerializeResult = preSerialize(root);
        System.out.println();

        System.out.println("==============先序遍历反序列化结果==============");
        BinaryTreeNode binaryTreeNode = preDeserialize(preSerializeResult);
        binaryTreeNode.preOrder();

        /**
         *      2
         *    /     ==> 中序序列化结果  -1 1 -1 2 -1
         *   1
         *
         *   1
         *    \     ==> 中序序列化结果 -1 1 -1 2 -1
         *     2
         *   两颗完全不同的二叉树，中序序列化的结果可能一样
         */
        System.out.println("==============中序遍历后不一定能反序列化==============");
        System.out.println("...");

        System.out.println("==============按层遍历后序列化==============");
        Queue<Integer> levelSerializeQueue = levelSerialize(root);
        Iterator<Integer> iterator = levelSerializeQueue.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();

        System.out.println("==============按层遍历后反序列化==============");
        BinaryTreeNode levelRootNode = levelDeserialize(levelSerializeQueue);
        TraverseTree.levelReverse(levelRootNode);
    }

    /**
     * 二叉树先序遍历序列化，如果节点为空，则使用-1表示
     *
     * @param head
     */
    public static Queue<Integer> preSerialize(BinaryTreeNode head){
        Queue<Integer> queue = new LinkedList<>();
        preReserve(head, queue);

        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + "\t");
        }

        return queue;
    }

    public static void preReserve(BinaryTreeNode head, Queue<Integer> queue){
        if (null == head){
            queue.add(-1);
        }else {
            queue.add(head.getNo());
            preReserve(head.getLeft(), queue);
            preReserve(head.getRight(), queue);
        }
    }

    /**
     * 先序遍历反序列化
     *
     * @param queue
     * @return
     */
    public static BinaryTreeNode preDeserialize(Queue<Integer> queue){
        Integer curEle = queue.poll();
        if (curEle == -1){
            return null;
        }

        BinaryTreeNode binaryTreeNode = new BinaryTreeNode(curEle, curEle + "");
        binaryTreeNode.setLeft(preDeserialize(queue));
        binaryTreeNode.setRight(preDeserialize(queue));

        return binaryTreeNode;
    }

    /**
     * 二叉树按层序列化
     *
     * @return
     */
    public static Queue<Integer> levelSerialize(BinaryTreeNode head){
        //1 准备2个队列 s1 和 s2，
        //1.1 s1中存储层级遍历的节点
        //1.2 s2中存储层级遍历的序列化字符串，如果节点为空使用-1表示
        Queue<BinaryTreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> nodeValueQueue = new LinkedList<>();

        //2 二叉树层级遍历
        nodeQueue.add(head);
        nodeValueQueue.add(head.getNo());
        while (!nodeQueue.isEmpty()){
            //2.1 如果节点不为空，则向s1压入节点，向s2压入节点值；如果节点为空，则向s2压入空值
            BinaryTreeNode curNode = nodeQueue.poll();

            //repeat left 如果节点不为空，则向s1压入节点，向s2压入节点值；如果节点为空，则向s2压入空值
            if (null != curNode.getLeft()){
                nodeQueue.add(curNode.getLeft());
                nodeValueQueue.add(curNode.getLeft().getNo());
            }else {
                nodeValueQueue.add(-1);
            }

            //repeat right 如果节点不为空，则向s1压入节点，向s2压入节点值；如果节点为空，则向s2压入空值
            if (null != curNode.getRight()){
                nodeQueue.add(curNode.getRight());
                nodeValueQueue.add(curNode.getRight().getNo());
            }else {
                nodeValueQueue.add(-1);
            }
        }

        return nodeValueQueue;
    }

    /**
     * 二叉树按层反序列化
     *
     *     1
     *   /  \       ==> 序列化的结果为：1 2 3 -1 -1 -1 -1
     *  2    3
     * @param queue
     * @return
     */
    public static BinaryTreeNode levelDeserialize(Queue<Integer> queue){
        BinaryTreeNode root = new BinaryTreeNode(queue.poll());

        //1.1 准备一个队列s，存放节点信息，先压入根节点
        Queue<BinaryTreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            //1.2 弹出节点，设置左右孩子节点
            BinaryTreeNode curRoot = nodeQueue.poll();
            curRoot.setLeft(generateNode(queue));
            curRoot.setRight(generateNode(queue));

            //1.3 如果左节点 和 右节点不为空，压入队列
            if (null != curRoot.getLeft()){
                nodeQueue.add(curRoot.getLeft());
            }
            if (null != curRoot.getRight()){
                nodeQueue.add(curRoot.getRight());
            }
        }

        return root;
    }

    private static BinaryTreeNode generateNode(Queue<Integer> queue){
        if (!queue.isEmpty()){
            Integer poll = queue.poll();
            return poll != -1 ? new BinaryTreeNode(poll) : null;
        }
        return null;
    }

}
