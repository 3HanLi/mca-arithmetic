package com.wy.mca.structure.tree.binary;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 最大二叉搜索树的节点个数
 * @Author wangyong01
 * @Date 2022/5/26 11:44 上午
 * @Version 1.0
 */
public class MaxSubBSTSize {

    public static void main(String[] args) {
        /**
         *              10
         *          /       \
         *         5        15
         *       /   \     /
         *      3    7    22
         *     /
         *    1
         */
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.setLeft(new BinaryTreeNode(5));
        root.setRight(new BinaryTreeNode(15));
        root.getRight().setLeft(new BinaryTreeNode(22));
        root.getLeft().setLeft(new BinaryTreeNode(3));
        root.getLeft().setRight(new BinaryTreeNode(7));
        root.getLeft().getLeft().setLeft(new BinaryTreeNode(1));

        int maxSubBSTNodes = getMaxSubBSTNodes(root);
        System.out.println("最大二叉搜索树的节点个数:" + maxSubBSTNodes);
    }

    private static int getMaxSubBSTNodes(BinaryTreeNode root){
        if (null == root){
            return 0;
        }
        MaxSubBSTInfo maxSubBSTInfo = processInfo(root);
        return maxSubBSTInfo.getMaxBSTTreeNodes();
    }

    /**
     * 最大二叉搜索树的节点个数
     * 1 定义：整棵树可能不是二叉搜索树，但是子树可能存在二叉搜索树，计算这棵树上左右二叉搜索树的最大节点数量
     * 2 解题思路
     *   2.1 情况分析
     *       a) x的左子树存在搜索二叉树
     *       b) x的右子树存在搜索二叉树
     *       c) x自身就是搜索二叉树
     *   2.2 要求
     *       a) 向x的左子树要如下信息
     *          a.1 节点个数                 nodes
     *          a.2 最大搜索二叉树的节点个数    leftMaxSubBSTNodes
     *          a.3 树的最大值
     *
     *          判断左子树是否搜索二叉树：通过比较 nodes == leftMaxSubBSTNodes，相等的话说明左子树自身就是一颗二叉搜索子树
 *          b) 向x的右子树要如下信息
     *          a.1 节点个数
     *          a.2 最大搜索二叉树的节点个数
     *          a.3 树的最最小值
     *
     * 3 套路总结
     *   3.1 假设以x为根节点，向左右子树要信息 Info leftInfo = process(x.left) ; Info rightInfo = process(x.right)
     *   3.2 分析向左右子树要什么信息，能够解决我们的问题；如果向左右子树要的信息不一致，需要进行合并；梳理信息对象Info
     *   3.3 以x节点为跟的树，也要满足上述条件，才能保证继续递归；
     *
     *   代码体现：
     *   Info leftInfo = process(left)
     *   Info rightInfo = process(right)
     *   ..
     *   return new Info(xx,xx);
     *
     * @param root
     * @return
     */
    private static MaxSubBSTInfo processInfo(BinaryTreeNode root){
        if (root == null){
            return null;
        }

        MaxSubBSTInfo leftInfo = processInfo(root.getLeft());
        MaxSubBSTInfo rightInfo = processInfo(root.getRight());

        //以 X 为根节点的二叉树，获取如下信息；

        //1 二叉树的节点个数
        int nodes = 1;
        //2 二叉树的最大值
        int max = root.getNo();
        //3 二叉树的最小值
        int min = root.getNo();
        if (null != leftInfo){
            nodes += leftInfo.getNodes();
            max = Math.max(max, leftInfo.getMax());
            min = Math.min(min, leftInfo.getMin());
        }
        if (null != rightInfo){
            nodes += rightInfo.getNodes();
            max = Math.max(max, rightInfo.getMax());
            min = Math.min(min, rightInfo.getMin());
        }

        //3.1 左（右）子树是否是搜索二叉树
        boolean isLeftBST = null == leftInfo ? true : (leftInfo.getMaxBSTTreeNodes() == leftInfo.getNodes());
        boolean isRightBST = null == rightInfo ? true : (rightInfo.getMaxBSTTreeNodes() == rightInfo.getNodes());

        //3.2 如果左（右）子树是搜索二叉树，那么最大搜索二叉树的节点个数
        int leftMaxSubBSTNodes = isLeftBST ? (null == leftInfo ? 0 : leftInfo.getMaxBSTTreeNodes()) : 0;
        int rightMaxSubBSTNodes = isRightBST ? (null == rightInfo ? 0 : rightInfo.getMaxBSTTreeNodes()) : 0;
        int maxBSTTreeNodes = 0;
        //3.3 如果左右子树都是搜索二叉树，判断以x问根节点是否是搜索二叉树，如果是的话，计算最大节点个数
        if (isLeftBST && isRightBST){
            boolean isLeftMaxLessRoot = null == leftInfo ? true : leftInfo.getMax() < root.getNo();
            boolean isRightMinMoreRoot = null == rightInfo ? true : rightInfo.getMin() > root.getNo();
            if (isLeftMaxLessRoot && isRightMinMoreRoot){
                maxBSTTreeNodes += (null == leftInfo ? 0 : leftInfo.getNodes());
                maxBSTTreeNodes += (null == rightInfo ? 0 : rightInfo.getNodes());
                maxBSTTreeNodes += 1;
            }
        }
        //3.4 最大二叉搜索子树节点个数：左子树为搜索二茬子树的最大节点个数  | 右子树为搜索二茬子树的最大节点个数 | 以X为根节点的搜索二茬子树的最大节点个数
        maxBSTTreeNodes = Math.max(maxBSTTreeNodes, Math.max(leftMaxSubBSTNodes, rightMaxSubBSTNodes));

        return new MaxSubBSTInfo(nodes, maxBSTTreeNodes, max, min);
    }

}

@Data
@AllArgsConstructor
class MaxSubBSTInfo{

    /**
     * 节点数量
     */
    private int nodes;

    /**
     * 最大搜索二叉树的节点个数
     */
    private int maxBSTTreeNodes;

    /**
     * 树的最大值
     */
    private int max;

    /**
     * 树的最小值
     */
    private int min;

}