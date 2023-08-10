package com.wy.mca.structure.tree.binary;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description 二叉树遍历
 *
 * 一个结论：如果对二叉树进行遍历，先序遍历时x前面的集合是A，后序遍历时x后面的集合时B，那么A和B的交集一定是x的祖先节点
 *
 * @Author wangyong01
 * @Date 2022/4/2 2:42 下午
 * @Version 1.0
 */
public class TraverseTree {

    public static void main(String[] args) {
        /**
         *          0
         *      /       \
         *     1        2
         *  /   \
         *  3    4
         */
        BinaryTreeNode root = new BinaryTreeNode(0, "root");
        root.setLeft(new BinaryTreeNode(1, "leftNode01"));
        root.setRight(new BinaryTreeNode(2, "rightNode01"));
        root.getLeft().setLeft(new BinaryTreeNode(3,"leftNode01-leftNode01"));
        root.getLeft().setRight(new BinaryTreeNode(4,"leftNode01-rightNode01"));

        System.out.println("===================递归==========================");
        System.out.println("先序遍历");
        preOrder(root);
        System.out.println();

        System.out.println("中序遍历");
        midOrder(root);
        System.out.println();

        System.out.println("后续遍历");
        postOrder(root);
        System.out.println();

        System.out.println("=====================非递归========================");
        System.out.println("先序遍历");
        preOrderStack(root);
        System.out.println();

        System.out.println("中序遍历");
        midOrderStack(root);
        System.out.println();

        System.out.println("后序遍历");
        postOrderStack(root);
        System.out.println();

        System.out.println("=====================按层遍历========================");
        levelReverse(root);
    }

    /**
     * 二叉树先序遍历：头->左->右
     *
     * @param head
     */
    public static void preOrder(BinaryTreeNode head){
        if (null == head){
            return;
        }
        System.out.print(head.getNo() + "\t");
        preOrder(head.getLeft());
        preOrder(head.getRight());
    }

    /**
     * 二叉树先序遍历：非递归（使用一个栈）
     *
     * @param head
     */
    public static void preOrderStack(BinaryTreeNode head){
        if (null != head){
            //1 准备一个栈
            Stack<BinaryTreeNode> stack = new Stack<>();
            //1.1 先压入头节点
            stack.push(head);
            while (!stack.isEmpty()){
                //1.2 弹出栈中元素
                BinaryTreeNode cur = stack.pop();
                System.out.print(cur.getNo() + "\t");

                //1.3 依次压入右节点和左节点
                if (null != cur.getRight()){
                    stack.push(cur.getRight());
                }
                if (null != cur.getLeft()){
                    stack.push(cur.getLeft());
                }
                //1.4 栈不为空继续--
            }
        }
    }

    /**
     * 中序遍历 左->头->右
     *
     * @param head
     */
    public static void midOrder(BinaryTreeNode head){
        if (null == head){
            return;
        }
        midOrder(head.getLeft());
        System.out.print(head.getNo() + "\t");
        midOrder(head.getRight());
    }

    /**
     * 中序遍历
     *
     * @param head
     */
    public static void midOrderStack(BinaryTreeNode head){
        //1.1 准备一个栈，让头节点到左侧一条线压入栈
        Stack<BinaryTreeNode> stack = new Stack<>();
        push(stack, head);

        while (!stack.isEmpty()){
            //1.2 弹出栈中元素
            BinaryTreeNode cur = stack.pop();
            System.out.print(cur.getNo() + "\t");

            //1.3 如果节点有右孩子，将右孩子最左侧一条线压入栈中
            if (null != cur.getRight()){
                push(stack, cur.getRight());
            }
        }
    }

    private static void push(Stack<BinaryTreeNode> stack, BinaryTreeNode head){
        while (null != head){
            stack.push(head);
            head = head.getLeft();
        }
    }

    /**
     * 后续遍历 左->右->头
     *
     * @param head
     */
    public static void postOrder(BinaryTreeNode head){
        if (null == head){
            return;
        }
        postOrder(head.getLeft());
        postOrder(head.getRight());
        System.out.print(head.getNo() + "\t");
    }

    /**
     * 二叉树后遍历：非递归（使用一个栈）
     *
     * @param head
     */
    public static void postOrderStack(BinaryTreeNode head){
        if (null != head){
            //1 准备2个栈stack1和stack2
            Stack<BinaryTreeNode> stack1 = new Stack<>();
            Stack<BinaryTreeNode> stack2 = new Stack<>();

            //1.1 先压入头节点到stack1
            stack1.push(head);
            while (!stack1.isEmpty()){
                BinaryTreeNode cur = stack1.pop();
                //1.2 然后紧接着弹出来放入stack2
                stack2.push(cur);

                //1.3 然后由左向右压入stack1
                if (null != cur.getLeft()){
                    stack1.push(cur.getLeft());
                }
                if (null != cur.getRight()){
                    stack1.push(cur.getRight());
                }
                //2 继续上述操作
                //总结起来就是：为了遍历左右头，先把头放入stack2，然后在stack1依次放入左右，然后弹出压入stack2，这样stack2弹出来就是左右头
            }
            //2 弹出stack2中元素
            while (!stack2.isEmpty()){
                System.out.print(stack2.pop().getNo() + "\t");
            }
        }
    }

    /**
     * 二叉树层级遍历
     *
     * @param head
     */
    public static void levelReverse(BinaryTreeNode head){
        if (null != head){
            //1.1 准备一个队列，先压入头节点
            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.add(head);

            while (!queue.isEmpty()){
                //1.2 从队列中弹出元素
                BinaryTreeNode curTreeNode = queue.poll();
                System.out.print(curTreeNode.getNo() + "\t");

                //1.3 依次压入左右节点
                if (null != curTreeNode.getLeft()){
                    queue.add(curTreeNode.getLeft());
                }

                if (null != curTreeNode.getRight()){
                    queue.add(curTreeNode.getRight());
                }
            }
        }
    }
}
