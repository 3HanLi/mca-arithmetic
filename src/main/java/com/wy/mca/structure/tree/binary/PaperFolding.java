package com.wy.mca.structure.tree.binary;

/**
 * @Description 折纸痕问题
 * @Author wangyong01
 * @Date 2022/5/16 10:30 下午
 * @Version 1.0
 */
public class PaperFolding {

    public static void main(String[] args) {
        paperFoldingPrint(1, 3, true);
    }

    /**
     * 折纸痕问题
     * 1）问题描述
     *   1  将一张纸条竖直，面上自己的这一面是正面
     *   2  将纸条从下向上折，折的方向面向自己，此时有一个凹折痕，记为1凹
     *      2.1 继续向上折，此时1凹上侧出现2凹，下册出现2凸
     *      2.2 继续向上折，此时2凹上面是3凹，下面是3凸；2凸上面是3凹，下面是3凸
     *   3 从上到下输出折痕：
     *     3凹 2凹 3凸 1凹 3凹 2凸 3凸
     *
     * 2）解题思路
     *    2.1 使用二叉树表示折痕，如图
     *              1凹
     *             /   \
     *          2凹     2凸
     *         /  \    /  \
     *       3凹  3凸  3凹  3凸
     *    2.2 通过输出结果，发现其实就是二叉树的中序遍历
     * @param level
     * @param deptDepth
     * @param down
     * @return
     */
    public static void paperFoldingPrint(int level, int deptDepth, boolean down){
        //1.1 二叉树判断节点是否为空 ==> 判断遍历是否超过了最大层
        if (level > deptDepth){
            return;
        }
        //1.2 向左遍历：level+1 表示向下一层 ；down表示凹或者凸，这里左为凹；
        paperFoldingPrint(level+1, deptDepth, true);
        System.out.print(down ? "凹 " : "凸 ");
        //1.3 向右遍历：level+1 表示向下一层 ；down表示凹或者凸，这里右为凸
        paperFoldingPrint(level+1, deptDepth, false);
    }
}
