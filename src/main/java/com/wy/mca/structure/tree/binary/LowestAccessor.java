package com.wy.mca.structure.tree.binary;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @Description 给定一颗二叉树树的头结点head，和另外两个节点a和b，返回a和b的最小公共祖先
 * @Author wangyong01
 * @Date 2022/7/10 9:52 下午
 * @Version 1.0
 */
public class LowestAccessor {

    public static void main(String[] args) {
        /**
         *          0
         *      /       \
         *     1        2
         *  /   \      /
         * 3    4     5
         */
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.setLeft(new BinaryTreeNode(1));
        root.setRight(new BinaryTreeNode(2));
        root.getLeft().setLeft(new BinaryTreeNode(3));
        root.getLeft().setRight(new BinaryTreeNode(4));
        root.getRight().setLeft(new BinaryTreeNode(5));

        //node 3
        BinaryTreeNode a = root.getLeft().getLeft();
        //node 1
        BinaryTreeNode b = root.getLeft();
        System.out.println("普通遍历查找a 和 b的公共祖先：" + findLowestAccessor(root ,a , b));
        System.out.println("递归遍历查找a 和 b的公共祖先：" + findLowestAccessorDp(root ,a , b));
    }

    /**
     * 解决思路
     * 1）遍历整棵二叉树，存储每个节点和父节点关系；
     * 2）查找节点a的所有父级节点，放入集合aParentSet；
     * 3）遍历节点b的所有父级节点，如果aParentSet包含，则该节点即是最小公共祖先
     * @param head
     * @param a
     * @param b
     * @return
     */
    public static BinaryTreeNode findLowestAccessor(BinaryTreeNode head, BinaryTreeNode a, BinaryTreeNode b){
        //1 遍历整棵二叉树，维护每个节点和父节点关心
        Map<BinaryTreeNode, BinaryTreeNode> nodeParentMap = Maps.newHashMap();
        initParentMap(head, nodeParentMap);

        //2 将a的所有祖先节点存入集合aParentSet中
        Set<BinaryTreeNode> aParentSet = Sets.newHashSet();
        BinaryTreeNode cur = a;
        while (null != nodeParentMap.get(cur)){
            aParentSet.add(nodeParentMap.get(cur));
            cur = nodeParentMap.get(cur);
        }

        //3 如果aParentSet包含b的祖先节点，则说明找到公共祖先，遍历终止
        cur = b;
        if (!aParentSet.contains(cur)){
            cur = nodeParentMap.get(cur);
        }

        return cur;
    }

    private static void initParentMap(BinaryTreeNode head, Map<BinaryTreeNode, BinaryTreeNode> nodeParentMap){
        if (null != head.getLeft()){
            nodeParentMap.put(head.getLeft(), head);
            initParentMap(head.getLeft(), nodeParentMap);
        }
        if (null != head.getRight()){
            nodeParentMap.put(head.getRight(), head);
            initParentMap(head.getRight(), nodeParentMap);
        }
    }

    /**
     * 二叉树递归套路解决
     * 1 问题分析
     *   1.1 在左子树上找到公共祖先
     *   1.2 在右子树找到公共祖先
     *   1.3 左子树和右子树都没找到公共祖先，是否找到了a，是否找到了b，如果a和b都找到了，那么当前节点就是最小公共祖先
     * 2 定义Info对象
     *
     * 3 递归套路
     *
     * @param head
     * @param a
     * @param b
     * @return
     */
    public static BinaryTreeNode findLowestAccessorDp(BinaryTreeNode head, BinaryTreeNode a, BinaryTreeNode b){
        LowestAccessorInfo processInfo = processInfo(head, a, b);
        return processInfo.getFindNode();
    }

    private static LowestAccessorInfo processInfo(BinaryTreeNode head, BinaryTreeNode a, BinaryTreeNode b){
        if (null == head){
            return new LowestAccessorInfo(false, false, null);
        }

        //1 递归套路
        LowestAccessorInfo leftNode = processInfo(head.getLeft(), a, b);
        LowestAccessorInfo rightNode = processInfo(head.getRight(), a, b);

        //2.1 是否找到了A
        boolean findA = (head == a) || leftNode.isFindA() || rightNode.isFindA() ;
        //2.2 是否找到了B
        boolean findB = (head == b) || leftNode.isFindB() || rightNode.isFindB();

        BinaryTreeNode findNode = null;
        //2.3 在左子树 或者 右子树上找到最小公共祖先
        if (leftNode.getFindNode() != null){
            findNode = leftNode.getFindNode();
        } else if (rightNode.getFindNode() != null){
            findNode = rightNode.getFindNode();
        } else {
            //2.4 如果左子树 和 右子树 都没找到，但是以当前节点head为根的树，找到了A 和 B，那么 当前节点 head就是最小公共祖先
            if (findA && findB){
                findNode = head;
            }
        }

        return new LowestAccessorInfo(findA, findB, findNode);
    }

}

@Data
@AllArgsConstructor
class LowestAccessorInfo{

    /**
     * 是否找到了A
     */
    private boolean findA;

    /**
     * 是否找到了B
     */
    private boolean findB;

    /**
     * 最小公共祖先
     */
    private BinaryTreeNode findNode;

}
