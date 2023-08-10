package com.wy.mca.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历
 * @author wangyong01
 */
public class BinaryTree {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;

        //二叉树中序遍历
        List<Integer> inorderList = inorderTraversal(treeNode1);
        System.out.println(inorderList);

        //翻转二叉树
//        invertTree(treeNode1);
//        inorderList = inorderTraversal(treeNode1);
//        System.out.println(inorderList);

        //二叉树的最大深度
        int maxDepth = maxDepth(treeNode1);
        System.out.println(maxDepth);

        //二叉树是否镜像对称
        TreeNode treeNode11 = new TreeNode(1);
        TreeNode treeNode21 = new TreeNode(2);
        TreeNode treeNode31 = new TreeNode(2);
        TreeNode treeNode41 = new TreeNode(3);
        TreeNode treeNode51 = new TreeNode(4);
        TreeNode treeNode61 = new TreeNode(4);
        TreeNode treeNode71 = new TreeNode(3);
        treeNode11.left = treeNode21;
        treeNode11.right = treeNode31;
        treeNode21.left = treeNode41;
        treeNode21.right = treeNode51;
        treeNode31.left = treeNode61;
        treeNode31.right = treeNode71;
        boolean symmetric = isSymmetric(treeNode11);
        System.out.println("二叉树是否对称" + symmetric);

        //判断二叉树是否相等
//        [1,2]
//        [1,null,2]
        TreeNode p = new TreeNode(1);
        p.setLeft(new TreeNode(2));

        TreeNode q = new TreeNode(1);
        q.setLeft(new TreeNode(2));

        System.out.println("SameTree-->" +isSameTree(p,q));
    }

    /**
     * 二叉树中序遍历
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        if (null == root){
            return inorderList;
        }
        inorderTraversal(root, inorderList);
        return inorderList;
    }

    /**
     * 二叉树中序遍历
     * @param root
     * @param inorderList
     * @return
     */
    private static void inorderTraversal(TreeNode root, List<Integer> inorderList){
        if (null != root.left){
            inorderTraversal(root.left, inorderList);
        }
        inorderList.add(root.val);
        if (null != root.right){
            inorderTraversal(root.right, inorderList);
        }
    }

    /**
     * 翻转二叉树
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        invertTreeBranch(root);
        return root;
    }

    private static void invertTreeBranch(TreeNode root){
        if (null != root){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTreeBranch(root.left);
            invertTreeBranch(root.right);
        }
    }

    /**
     * 二叉树的最大深度
     * 思路：树的最大深度 = Max(左子树最大深度,又子树最大深度) + 1
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (null == root){
            return 0;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的
     *      1
     *     / \
     *    2   2
     *   / \ / \
     *  3  4 4  3
     * 思路：节点1的左孩子和节点2的右孩子相等，节点1的右孩子和节点2的左孩子相等
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (null == root){
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode left, TreeNode right){
        if (null == left && null == right){
            return true;
        }
        if (null == left || null == right){
            return false;
        }
        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * 树是否相同
     *
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (null == p && q == null){
            return true;
        }
        if (null == p || null == q){
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
