package com.wy.mca.structure.tree.binary;

import lombok.Data;

/**
 * 二叉树
 * 1 特性：
 * 	 1.1 每个节点最多有两个儿子
 *   1.2 平均深度O(√N)，二叉树的平均深度O(LogN)
 *   
 * 2 遍历：
 *   2.1 前序遍历：根节点 →左子树 → 右子树，如：[6.0, 3.0, 4.0, 14.0, 10.0, 9.0, 13.0, 11.0, 12.0, 16.0, 20.0, 18.0]
 *   2.2 中序遍历：左子树 → 根节点 → 右子树，如：[3.0, 4.0, 6.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 16.0, 18.0, 20.0]
 *   2.3 后序遍历：左子树 → 右子树 → 根节点
 *   
 *   2.n 二叉树
 * 			   6
 * 		    /     \		   	 
 * 		   3       14		
 * 		    \     /	  \	
 * 	       4	 10	 16
 * 		 		/ \		\	
 * 	 		   9  13	20
 * 				  /		/
 * 				 11	   18
 * 				  \
 * 				  12
 * 	3 节点的高度和深度
 *    深度：从根节点到该节点的长度
 *    高度：从叶子节点到该节点的长度
 *
 * 	4 树的深度和高度
 * 	  深度：从根节点数到它的叶节点
 * 	  高度：从叶节点数到它的根节点
 *
 * 	5 案例说明（以上面二叉树距离）
 * 	  5.1 二叉树的高度是6
 * 	  5.2 节点10的深度是3，节点10的高度是4
 * @author WangyongR
 */
@Data
public class BinaryTree {

    private BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }

    /**
     * 前序遍历 [6 3 4 14 10 9 13 11 12 16 20 18]
     */
    public void preOrder(){
        System.out.println("前序遍历");
        if (null == root){
            System.out.println("Tree is empty");
        }else {
            root.preOrder();
        }
    }

    public BinaryTreeNode preOrderSearch(int no){
        System.out.println("前序遍历查找");
        if (null == root){
            System.out.println("Tree is empty");
            return root;
        }else {
            return root.preOrderSearch(no);
        }
    }

    public void infixOrder(){
        System.out.println("中序遍历查找");
        if (null == root){
            System.out.println("Tree is empty");
        }else {
            root.infixOrder();
        }
    }

    public BinaryTreeNode infixOrderSearch(int no){
        System.out.println("中序查找");
        if (null == root){
            System.out.println("Tree is empty");
            return root;
        }else {
            return root.infixOrderSearch(no);
        }
    }

    public void postOrder(){
        System.out.println("后序遍历");
        if (null == root){
            System.out.println("Tree is empty");
        }else {
            root.postOrder();
        }
    }

    public BinaryTreeNode postOrderSearch(int no){
        System.out.println("后序遍历查找");
        if (null == root){
            System.out.println("Tree is empty");
            return root;
        }else {
            return root.postOrderSearch(no);
        }
    }

}
