package com.wy.mca.structure.tree.multi;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.wy.mca.structure.tree.binary.BinaryTreeNode;
import com.wy.mca.structure.tree.binary.TraverseTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 二叉树和多叉树互转
 * @Author wangyong01
 * @Date 2022/4/15 3:58 下午
 * @Version 1.0
 */
public class MultiBinaryTreeConvert {

    public static void main(String[] args) {
        MultiTreeNode multiTreeNode = new MultiTreeNode(1);
        multiTreeNode.setChildren(Lists.newArrayList(new MultiTreeNode(2), new MultiTreeNode(3), new MultiTreeNode(4)));
        multiTreeNode.getChildren().get(0).setChildren(Lists.newArrayList(new MultiTreeNode(5), new MultiTreeNode(6), new MultiTreeNode(7)));
        multiTreeNode.getChildren().get(1).setChildren(Lists.newArrayList(new MultiTreeNode(8), new MultiTreeNode(9)));

        System.out.println("================将多叉树转换成二叉树================");
        BinaryTreeNode binaryTreeNode = convertToBinaryTree(multiTreeNode);
        TraverseTree.levelReverse(binaryTreeNode);

        System.out.println("================将二叉树还原回多叉树================");
        MultiTreeNode multiTreeNodeResult = convertBinaryToMultiTree(binaryTreeNode);
        System.out.println(multiTreeNodeResult);
    }

    /**
     * 将多叉树转成二叉树
     * 解题思路：
     *
     * 对于每一棵子树
     * 1）将所有孩子节点挂在最左节点的右分支上
     * 2）在此之前，需要将最左节点的所有孩子节点挂在左分支上
     *
     *              1                           1
     *         /    |    \                     /
     *       2       3   4      ==>           2
     *     / | \    / \                     /   \
     *    5  6  7  8  9                    5     3
     *                                      \   / \
     *                                      6  8   4
     *                                       \  \
     *                                       7   9
     * @return
     */
    public static BinaryTreeNode convertToBinaryTree(MultiTreeNode multiRoot){
        BinaryTreeNode binaryRoot = new BinaryTreeNode(multiRoot.getNo());
        binaryRoot.setLeft(constructBranch(multiRoot.getChildren()));
        return binaryRoot;
    }

    private static BinaryTreeNode constructBranch(List<MultiTreeNode> children){
        // 第一个孩子节点
        BinaryTreeNode root = null;
        BinaryTreeNode cur = null;
        if (CollectionUtil.isNotEmpty(children)){
            for (MultiTreeNode multiTreeNode : children){
                BinaryTreeNode binaryTreeNode = new BinaryTreeNode(multiTreeNode.getNo());
                if (null == root){
                    root = binaryTreeNode;
                }else {
                    //1.1 将所有孩子节点挂在最左节点的右分支上
                    cur.setRight(binaryTreeNode);
                }
                cur = binaryTreeNode;
                //1.2 将最左节点的所有孩子节点挂在左分支上
                binaryTreeNode.setLeft(constructBranch(multiTreeNode.getChildren()));
            }
        }

        return root;
    }

    /**
     * 将多叉树还原成二叉树
     *
     * 解题思路：将自己和兄弟节点构建成链表返回父节点
     *
     * @param root
     * @return
     */
    public static MultiTreeNode convertBinaryToMultiTree(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        return new MultiTreeNode(root.getNo(), constructChildren(root.getLeft()));
    }

    private static List<MultiTreeNode> constructChildren(BinaryTreeNode root) {
        List<MultiTreeNode> children = new ArrayList<>();
        while (root != null) {
            //1.2 对经过的每个节点的左分支做同样的操作
            MultiTreeNode cur = new MultiTreeNode(root.getNo(), constructChildren(root.getLeft()));
            children.add(cur);
            //1.1 一直走右侧分支，找到所有兄弟节点
            root = root.getRight();
        }
        return children;
    }

}
