package com.wy.mca.structure.list;


import com.wy.mca.common.DoubleListNode;
import com.wy.mca.common.ListNode;
import com.wy.mca.util.MyListUtil;

/**
 * 1）问题描述：反转链表
 *
 * 2）案例说明：
 *
 * 3) 解题思路：
 * @author wangyong01
 */
public class ReverseList {

    public static void main(String[] args) {
        listNodeValidate();
    }

    /**
     * 对数器
     */
    private static void listNodeValidate(){
        ListNode node01 = MyListUtil.generateIncrementListNode(4);
        node01.print();

        ListNode pre = reverseNode(node01);
        pre.print();

        System.out.println("Test Double LinkedList ");
        DoubleListNode doubleListNode = MyListUtil.generateRandomDoubleListNode(3);
        DoubleListNode doublePre = reverseDoubleNode(doubleListNode);
        doublePre.printFromHead();
    }

    /**
     * 单链表反转
     * 举例说明
     * 1) 链表为：        1 -> 2 -> 3 ->4 ->null
     * 2) 反转为：null <- 1 <- 2 <- 3 <-4
     *
     * 解题思路
     * 1) 准备指针pre，记录当前节点上一个；    初始为null
     * 2) 准备指针next，记录当前节点下一个；   初始为：next = currNode.next
     * 3) 让当前节点指向上一个；              currNode.next = pre;
     *
     * 时间复杂度O(N)
     *
     * @param currNode
     * @return
     */
    public static ListNode reverseNode(ListNode currNode){
        ListNode pre = null;
        ListNode next = null;
        //链表越界了
        while (currNode != null){
            //1.1 next 获取当前元素下一节点
            next = currNode.next;
            //1.2 当前节点指向上一个
            currNode.next = pre;

            //2.1 pre向后移动
            pre = currNode;
            //2.2 当前元素向后移动
            currNode = next;
        }

        return pre;
    }

    /**
     * 双向链表反转
     * 举例说明
     * 1) 链表为
     *           1 -> 2 -> 3 -> 4 -> null
     *   null <- 1 <- 2 <- 3 <- 4
     * 2) 反转为
     *          4 -> 3 -> 2 -> 1 -> null
     *   null<- 4 <- 3 <- 2 <- 1
     *
     * @param currNode
     * @return
     */
    public static DoubleListNode reverseDoubleNode(DoubleListNode currNode){
        DoubleListNode pre = null;
        DoubleListNode next = null;
        while (currNode != null){
            //1.1 获取下一个节点
            next = currNode.next;

            //1.2 当前节点反转前后指针
            currNode.next = pre;
            currNode.pre = next;
            pre = currNode;

            //1.3 当前节点来到下个节点
            currNode = next;
        }

        return pre ;
    }
}
