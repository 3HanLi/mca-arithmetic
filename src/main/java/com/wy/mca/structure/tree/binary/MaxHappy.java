package com.wy.mca.structure.tree.binary;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @Description 计算最大快乐值
 * 1、给定一棵多叉树，每个节点代表公司组织架构上的一个员工，每个节点有一个快乐值
 * 2、规定上下级节点不能同时来
 * 3、计算整棵多叉树上的快乐最大值
 * @Author wangyong01
 * @Date 2022/7/12 8:25 下午
 * @Version 1.0
 */
public class MaxHappy {

    public static void main(String[] args) {
        /**
         *           6
         *       /   |   \
         *      2    3    27
         *    /  \   |     \
         *   5   12  15    20
         *
         *   result = 5 + 12 + 15 + 27 = 59
         */
        MultiTreeEmployee root = new MultiTreeEmployee(6);
        root.setChildren(Lists.newArrayList(Lists.newArrayList(
                new MultiTreeEmployee(2), new MultiTreeEmployee(3), new MultiTreeEmployee(27))));
        root.getChildren().get(0).setChildren(Lists.newArrayList(new MultiTreeEmployee(5), new MultiTreeEmployee(12)));
        root.getChildren().get(1).setChildren(Lists.newArrayList(new MultiTreeEmployee(15)));
        root.getChildren().get(2).setChildren(Lists.newArrayList(new MultiTreeEmployee(20)));

        int maxHappy = getMaxHappy(root);
        System.out.println("maxHappy:" + maxHappy);
    }

    public static int getMaxHappy(MultiTreeEmployee multiTreeEmployee){
        MaxHappyInfo maxHappyInfo = processInfo(multiTreeEmployee);
        return Math.max(maxHappyInfo.getYes(), maxHappyInfo.getNo());
    }

    /**
     * 1 问题分析
     *   1.1 当前节点来的最大值为 yes = 当前节点的快乐值 + 孩子节点不来的快乐值
     *   1.2 当前节点不来的最大值为 no = Math.max(孩子节点来的快乐值, 孩子节点不来的快乐值)
     *   1.3 整棵树的快乐值 = Math.max(yes, no)
     * 2 定义Info对象
     *
     * 3 递归套路
     * @param multiTreeEmployee
     * @return
     */
    public static MaxHappyInfo processInfo(MultiTreeEmployee multiTreeEmployee){
        if (null == multiTreeEmployee){
            return new MaxHappyInfo(0,0);
        }


        int no = 0;
        int yes = multiTreeEmployee.getHappy();

        if (CollectionUtil.isNotEmpty(multiTreeEmployee.getChildren())){
            for (MultiTreeEmployee child : multiTreeEmployee.getChildren()){
                //2 递归套路
                MaxHappyInfo childHappyInfo = processInfo(child);

                //1.1 当前节点不来的快乐值 = Math.max(孩子节点来的快乐值 , 孩子节点不来的快乐值)
                no += Math.max(childHappyInfo.getYes(), childHappyInfo.getNo());
                //1.2 当前节点来的快乐值 = 当前节点来的快乐值 + 孩子节点不来的快乐值
                yes += childHappyInfo.getNo();
            }
        }

        return new MaxHappyInfo(no, yes);
    }

}

@Data
class MultiTreeEmployee{

    /**
     * 快乐值
     */
    private int happy;

    /**
     * 下级
     */
    private List<MultiTreeEmployee> children;

    public MultiTreeEmployee(int happy) {
        this.happy = happy;
    }
}

@Data
class MaxHappyInfo{

    /**
     * 当前节点不来的情况下，多叉树的最大happy值
     */
    private int no;

    /**
     * 当前节点来的情况下，多叉树的最大happy值
     */
    private int yes;

    public MaxHappyInfo(int no, int yes) {
        this.no = no;
        this.yes = yes;
    }
}