package com.wy.mca.structure.tree.binary;

/**
 * 			   1
 * 		    /     \
 * 		   2       3
 * 		     	    \
 * 		     	    4
 * @author WangyongR
 */
public class BinaryTreeClient {

    public static void main(String[] args) {
        treeOrder();
    }

    public static void treeOrder(){
        BinaryTreeNode treeNode1 = new BinaryTreeNode(1, "宋江");
        BinaryTreeNode treeNode2 = new BinaryTreeNode(2, "吴用");
        BinaryTreeNode treeNode3 = new BinaryTreeNode(3, "卢俊义");
        BinaryTreeNode treeNode4 = new BinaryTreeNode(4, "林冲");

        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode3.setRight(treeNode4);

        BinaryTree binaryTree = new BinaryTree(treeNode1);
        //前序遍历  [1 2 3 4]
        binaryTree.preOrder();
        //中序遍历  [2 1 3 4]
        binaryTree.infixOrder();
        //后序遍历  [2 4 3 1]
        binaryTree.postOrder();

        //前序遍历查找
        BinaryTreeNode binaryTreeNode1 = binaryTree.preOrderSearch(2);
        System.out.println(binaryTreeNode1);
        //中序遍历查找
        BinaryTreeNode binaryTreeNode2 = binaryTree.infixOrderSearch(2);
        System.out.println(binaryTreeNode2);
        //后序遍历查找
        BinaryTreeNode binaryTreeNode3 = binaryTree.postOrderSearch(2);
        System.out.println(binaryTreeNode3);

        //翻转二叉树
//        treeNode1.invertTree();
//        binaryTree.preOrder();

        //树的最大深度
        int maxTreeDepth = treeNode1.maxTreeDepth();
        System.out.println("树的最大深度==>" + maxTreeDepth);

        //树是否对称
        boolean symmetric = treeNode1.isSymmetric();
        System.out.println("树是否对称==>" + symmetric);
    }

}
