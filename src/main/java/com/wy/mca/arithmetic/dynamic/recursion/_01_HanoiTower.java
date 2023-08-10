package com.wy.mca.arithmetic.dynamic.recursion;

/**
 * 汉诺塔问题
 *
 * 问题描述：有三根柱子，需要将左侧的N个盘子移动到最右侧，不能违反小压大原则，也就是不能出现大的压小的
 *
 * 解题思路：
 * 1) 先写出base case，当 n=1时，直接从左到右
 * 2) 当 n>1时，主要分为3步
 *    2.1 上面的n-1【从左到中】
 *    2.2 把n【从左到右】
 *    2.3 把n-1个元素【从中到右】
 * 3) 查看步骤2)依赖于那些操作，补全即可
 *
 * 递归合并
 *
 * 递归套路总结：
 * 1）将base case 考虑清除，让他们相互关联
 * 2）通过增加参数，来实现更多的功能（递归合并）
 *
 * @author wangyong01
 */
public class _01_HanoiTower {

    public static void main(String[] args) {
        move(3);
        System.out.println("递归聚合");
        move(3, "Left", "Right", "Mid");
    }

    public static void move(int n){
        moveFromLeftToRight(n);
    }

    /**
     * 递归合并
     * @param n
     * @param from
     * @param to
     * @param other
     */
    public static void move(int n, String from, String to, String other){
        if (n==1){
            System.out.println("Move 1 From Left To Right");
            return;
        }
        move(n-1, from, other, to);
        System.out.println("Move " + n + " From " + from + " to" + to);
        move(n-1, other, to, from);
    }

    /**
     * 把N个圆盘从左移动到右
     *
     * @param n
     */
    private static void moveFromLeftToRight(int n){
        if (n == 1){
            System.out.println("Move 1 From Left To Right");
            return;
        }
        moveFromLeftToMid(n-1);
        System.out.println("Move " + n + " From Left To Right");
        moveFromMidToRight(n-1);
    }

    /**
     * 把N个圆盘从左移动到中
     *
     * @param n
     */
    private static void moveFromLeftToMid(int n){
        if (n == 1){
            System.out.println("Move 1 From Left To Mid");
            return;
        }
        moveFromLeftToRight(n-1);
        System.out.println("Move " + n + " From Left To Mid");
        moveFromRightToMid(n-1);
    }

    private static void moveFromMidToRight(int n){
        if (n==1){
            System.out.println("Move 1 From Mid To Right");
            return;
        }
        moveFromMidToLeft(n-1);
        System.out.println("Move " + n + " From Mid To Right");
        moveFromLeftToRight(n-1);
    }

    private static void moveFromRightToMid(int n){
        if (n==1){
            System.out.println("Move 1 From Right To Mid");
            return;
        }
        moveFromRightToLeft(n-1);
        System.out.println("Move " + n + " From Right To Mid");
        moveFromLeftToMid(n-1);
    }

    private static void moveFromRightToLeft(int n) {
        if (n==1){
            System.out.println("Move 1 From Right To Left");
            return;
        }
        moveFromRightToMid(n-1);
        System.out.println("Move " + n + " From Right To Left");
        moveFromMidToLeft(n-1);
    }

    private static void moveFromMidToLeft(int n) {
        if (n==1){
            System.out.println("Move 1 From Mid To Left");
            return;
        }
        moveFromMidToRight(n-1);
        System.out.println("Move " + n + " From Mid To Left");
        moveFromRightToLeft(n-1);
    }

}
