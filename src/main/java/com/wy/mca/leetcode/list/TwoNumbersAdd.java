package com.wy.mca.leetcode.list;

import com.wy.mca.common.ListNode;

/**
 * 两数相加
 * 1) 给定两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字
 * 2) 将两个数相加，并以相同形式返回一个表示和的链表
 * 3) 案例：
 *    链表
 *      2 ->4 ->3
 *      5 ->6 ->4
 *    结果
 *      7 ->0 ->8
 *    解释
 *      输入：l1 = [2,4,3], l2 = [5,6,4]
 *      输出：[7,0,8]
 *      解释：342 + 465 = 807
 * @author admin
 */
public class TwoNumbersAdd {

    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(9);
        ListNode listNode12 = new ListNode(9);
        ListNode listNode13 = new ListNode(9);
        ListNode listNode14 = new ListNode(9);
        ListNode listNode15 = new ListNode(9);
        ListNode listNode16 = new ListNode(9);
        ListNode listNode17 = new ListNode(9);
        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = listNode14;
        listNode14.next = listNode15;
        listNode15.next = listNode16;
        listNode16.next = listNode17;

        ListNode listNode21 = new ListNode(9);
        ListNode listNode22 = new ListNode(9);
        ListNode listNode23 = new ListNode(9);
        ListNode listNode24 = new ListNode(9);
        listNode21.next = listNode22;
        listNode22.next = listNode23;
        listNode23.next = listNode24;

        ListNode listNode = myAddTwoNumbers(listNode11, listNode21);
        listNode.print();

    }

    public static ListNode myAddTwoNumbers(ListNode listNode1, ListNode listNode2) {
        int length1 = listNode1.length();
        int length2 = listNode2.length();

        ListNode head = null, pre = null;
        ListNode p = length1 > length2 ? listNode1 : listNode2;
        ListNode p1 = listNode1, p2 = listNode2;
        int i = 0;

        boolean addPlus = false;
        while (p != null){
            int val3 =  (null != p1 ? p1.getVal() : 0) + (null !=  p2 ? p2.getVal() : 0) ;
            val3 = addPlus ? val3 + 1 : val3;
            if (val3 >= 10){
                addPlus = true;
            }else {
                addPlus = false;
            }
            if (i == 0){
                head = new ListNode(val3 >= 10 ? val3 - 10 : val3);
                pre = head;
            }else {
                pre.next = new ListNode(val3 >= 10 ? val3 - 10 : val3);
                pre = pre.next;
            }
            p = p.next;
            p1 = null != p1 ? p1.next : null;
            p2 = null != p2 ? p2.next : null;
            i ++;
        }
        if (addPlus){
            pre.next = new ListNode(1);
        }

        return head;
    }


}
