package com.wy.mca.structure.tree.binary;

import lombok.Data;

/**
 * @author wangyong01
 */
@Data
public class BinaryTreeNode {

    private int no;

    private String name;

    private BinaryTreeNode left;

    private BinaryTreeNode right;

    private BinaryTreeNode parent;

    public BinaryTreeNode(){

    }

    public BinaryTreeNode(int no) {
        this.no = no;
    }

    public BinaryTreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if (null != this.left){
            this.left.preOrder();
        }
        if (null != this.right) {
            this.right.preOrder();
        }
    }

    /**
     * 前序查找
     * @param no
     */
    public BinaryTreeNode preOrderSearch(int no){
        System.out.println("进入前序查找");
        if (this.no == no){
            return this;
        }
        BinaryTreeNode binaryTreeNode = null;
        if (null != this.left){
            binaryTreeNode = this.left.preOrderSearch(no);
        }
        if (null != binaryTreeNode){
            return binaryTreeNode;
        }
        if (null != this.right){
            binaryTreeNode = this.right.preOrderSearch(no);
        }
        return binaryTreeNode;
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (null != this.left){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (null != this.right){
            this.right.infixOrder();
        }
    }

    /**
     * 中序查找
     * @param no
     * @return
     */
    public BinaryTreeNode infixOrderSearch(int no){
        BinaryTreeNode binaryTreeNode = null;
        if (null != this.left){
            binaryTreeNode = this.left.infixOrderSearch(no);
        }
        if (null != binaryTreeNode){
            return binaryTreeNode;
        }
        System.out.println("进入中序查找");
        if (this.no == no){
            return this;
        }
        if (null != this.right){
            binaryTreeNode = this.right.right.infixOrderSearch(no);
        }
        return binaryTreeNode;
    }

    /**
     * 后续遍历
     */
    public void postOrder(){
        if (null != this.left){
            this.left.postOrder();
        }
        if (null != this.right){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 后序查找
     *
     * @param no
     */
    public BinaryTreeNode postOrderSearch(int no){
        BinaryTreeNode binaryTreeNode = null;
        if (null != this.left){
            binaryTreeNode = this.left.postOrderSearch(no);
        }
        //如果没有左节点，返回当前节点
        if (null != binaryTreeNode){
            return binaryTreeNode;
        }
        if (null != this.right){
            binaryTreeNode = this.right.postOrderSearch(no);
        }
        //如果没有右节点，返回当前节点
        if (null != binaryTreeNode){
            return binaryTreeNode;
        }
        System.out.println("进入后序查找");
        if (this.no == no){
            return this;
        }
        return binaryTreeNode;
    }

    /**
     * 翻转二叉树
     */
    public void invertTree() {
        if (null != this){
            BinaryTreeNode left = this.left;
            this.left = this.right;
            this.right = left;
            if (null != this.left){
                this.left.invertTree();
            }
            if (null != this.right){
                this.right.invertTree();
            }
        }
    }

    /**
     * 二叉树的最大深度
     * 思路：树的最大深度 = Max(左子树最大深度,又子树最大深度) + 1
     *
     * @return
     */
    public int maxTreeDepth(){
        if (null == this){
            return 0;
        }
        int leftMaxDepth = null == this.left ?  0 : this.left.maxTreeDepth();
        int rightMaxDepth = null == this.right ? 0 : this.right.maxTreeDepth();
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }

    /**
     * 是否是镜像对称的
     *      1
     *     / \
     *    2   2
     *   / \ / \
     *  3  4 4  3
     * 思路：节点1的左孩子和节点2的右孩子相等，节点1的右孩子和节点2的左孩子相等
     * @return
     */
    public boolean isSymmetric(){
        if (null == this){
            return true;
        }
        if (null == this.left && null == this.right){
            return true;
        }
        if (null == this.left || null == this.right){
            return false;
        }

        return this.left.getNo() == this.right.getNo() && this.left.isSymmetric() && this.right.isSymmetric();
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
