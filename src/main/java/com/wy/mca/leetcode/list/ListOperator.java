package com.wy.mca.leetcode.list;

import com.wy.mca.common.ListNode;

/**
 * @author wangyong01
 */
public class ListOperator {

    public static void main(String[] args) {
        System.out.println("方法一：先计算链表长度，然后进行节点删除");
        ListNode listNode = constructListNode();
        ListNode head = removeNthFromEnd(listNode, 2);
        while (null != head){
            System.out.print(head.getVal() + "->");
            head = head.next;
        }
        System.out.println("方法二：一次遍历，删除节点");
        ListNode listNode2 = constructListNode();
        ListNode head2 = removeNthFromEnd2(listNode2, 2);
        while (null != head2){
            System.out.print(head2.getVal() + "->");
            head2 = head2.next;
        }
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点，给定的 n 保证是有效的
     *
     * 思路：
     *
     * 时间复杂度：
     *
     * 空间复杂度：
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int size = listSize(head);
        //1.1 如果删除首节点，直接返回head.next(目前采用该方案)
        //1.2 创建辅助节点headPre，指向头节点，返回headPre.next(后续采用该方案)
        if (n == size){
            head = head.next;
            return head;
        }

        ListNode temp = head;
        for (int i =1; i<size-n; i++){
            temp = temp.next;
        }

        temp.next = temp.next.next;

        return head;
    }

    private static int listSize(ListNode listNode){
        int i=0;
        ListNode temp = listNode;
        while (null != temp){
            temp = temp.next;
            i++;
        }
        return i;
    }

    /**
     * 尝试使用一趟扫描实现
     * 思路：
     * a) 创建辅助节点pre，令：pre.next = head
     * b) 创建两个辅助指针指向链表的辅助节点，如：
     *    pointer01 = pre;
     *    pointer02 = pre;
     * c) 有限将复制指针pointer02移动n个位置
     * d) 移动pointer01和pointer02，直到pointer02的下一个节点为空，那么pointer01就是待删除的节点的前驱节点
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        //1.1 创建辅助节点
        ListNode pre = new ListNode(0);
        pre.next = head;

        //1.2 创建两个复制指针
        ListNode pointer01 = pre;
        ListNode pointer02 = pre;

        //1.3 辅助指针移动到n的位置
        for (int i=0; i<n; i++){
            pointer02 = pointer02.next;
        }

        //1.4 当辅助指针的下一个节点为空，说明移动到队尾
        while (pointer02.next != null){
            pointer02 = pointer02.next;
            pointer01 = pointer01.next;
        }

        pointer01.next = pointer01.next.next;

        return head;
    }

    private static ListNode constructListNode(){
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);
        listNode4.setNext(listNode5);
        return listNode1;
    }

}

