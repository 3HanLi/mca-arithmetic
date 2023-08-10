package com.wy.mca.structure.tree.binary;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * 问题描述：查找当前节点x的后继节点
 * 解题思路一：
 * 1）中序遍历二叉树，将节点放入集合中
 * 2）重新遍历数组查找当前节点，那么下一个节点就是后继节点
 * 时间复杂度：O(N)，N是二叉树节点个数
 *
 * 解题思路二：
 * 1）假如x有右子树，那么x的后继节点是x的右子树的最左节点
 * 2）假如x没有右子树，那么x的后继节点就是：沿着x一直向上找，直到【curNode=parent.left】，那么parent就是后继节点
 * 时间复杂度：O(K)，K是当前节点x到后继节点之间的距离
 *
 * @Author wangyong01
 * @Date 2022/5/15 8:32 下午
 * @Version 1.0
 */
public class FindSuccessorNode {

    public static void main(String[] args) {
        /**
         *        1
         *      /   \
         *     2    3
         *    / \
         *   4  5
         *     /
         *    6
         */
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node6 = new BinaryTreeNode(6);

        root.setLeft(node2);
        root.getLeft().setLeft(node4);
        root.getLeft().setRight(node5);
        root.getLeft().getRight().setLeft(node6);
        root.setRight(node3);

        node2.setParent(root);
        node4.setParent(node2);
        node5.setParent(node2);
        node6.setParent(node5);
        node3.setParent(root);

        BinaryTreeNode byIndexNode = findSuccessorNodeByIndex(root, node2);
        System.out.println("通过集合查找节点" +  node2.getNo() +"的后继节点:" + byIndexNode);

        BinaryTreeNode byParentNode = findSuccessorNodeByParent(node2);
        System.out.println("通过父节点查找节点" +  node2.getNo() +"的后继节点:" + byParentNode);
    }

    /**
     * 中序遍历二叉树，将遍历结果放入集合中；
     * 遍历集合查找节点x的下一个节点
     *
     * @param root
     * @param findNode
     * @return
     */
    public static BinaryTreeNode findSuccessorNodeByIndex(BinaryTreeNode root, BinaryTreeNode findNode){
        //1 中序遍历
        Stack<BinaryTreeNode> stack = new Stack<>();
        pushLeft(stack, root);

        List<BinaryTreeNode> nodeList = Lists.newArrayList();
        while (!stack.isEmpty()){
            BinaryTreeNode curNode = stack.pop();
            pushLeft(stack, curNode.getRight());
            //在中序遍历的过程中，将遍历结果加入结果
            nodeList.add(curNode);
        }

        //2 查找当前节点的下一个节点
        for (int i=0; i<nodeList.size()-1; i++){
            if (nodeList.get(i) == findNode){
                return nodeList.get(i+1);
            }
        }

        return null;
    }

    private static void pushLeft(Stack<BinaryTreeNode> stack, BinaryTreeNode head){
        while (null != head){
            stack.push(head);
            head = head.getLeft();
        }
    }

    /**
     * 查找当前节点的后继节点
     *
     * @param root
     * @return
     */
    public static BinaryTreeNode findSuccessorNodeByParent(BinaryTreeNode root){
        //1.1 假如x有右子树，那么x的后继节点是x的右子树的最左节点
        if (null != root.getRight()){
            return getLeftestNode(root.getRight());
        }else {
            //1.2 假如x没有右子树，沿着x一直向上找，直到【curNode=parent.left】，那么parent就是后继节点
            BinaryTreeNode parent = root.getParent();
            while (null != parent && parent.getRight() == root){
                root = parent;
                parent = root.getParent();
            }
            return parent;
        }
    }

    /**
     * 查找最左节点
     * 最左节点：沿着当前节点一直查找左子树，知道节点为空，那么最后一个节点就是最左节点
     *
     * @param root
     * @return
     */
    private static BinaryTreeNode getLeftestNode(BinaryTreeNode root){
        BinaryTreeNode curNode = root;
        while (null != curNode.getLeft()){
            curNode = curNode.getLeft();
        }
        return curNode;
    }

}
