package com.wy.mca.structure.list;

import com.google.common.collect.Lists;
import com.wy.mca.common.ListNode;

import java.util.List;

/**
 * 1）问题描述：给定链表头结点，判断是否是回文
 *
 * 2）案例说明
 *
 * 3）解题思路：
 */
public class ListPalindrome {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next = new ListNode(0);

        boolean palindromeWithCollection = isPalindromeWithCollection(head);
        System.out.println("通过集合判断，查看是否是回文:" + palindromeWithCollection);

        boolean palindrome = isPalindrome(head);
        System.out.println("直接通过链表判断，查看是否是回文:" + palindrome);
    }

    /**
     * 通过集合判断是否是回文
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeWithCollection(ListNode head){
        if (null == head){
            return true;
        }
        List<Integer> nodeValueList = Lists.newArrayList();
        while (null != head){
            nodeValueList.add(head.getVal());
            head = head.next;
        }
        int size = nodeValueList.size();
        for (int i = 0; i< size /2; i++){
            int curVal = nodeValueList.get(i);
            int reverseVal = nodeValueList.get(size-i-1);
            if (curVal != reverseVal){
                return false;
            }
        }

        return true;
    }

    /**
     * 判断链表是否是回文（不借助其他数据结构）
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head){
        if (null == head || null == head.next){
            return true;
        }

        //1.1 找到链表的中点，如果是奇数个，找到中点，然后让中点右侧的链表向左指
        //a->b->c->b->a ==> a->b->c<-b<-a
        //1.2 如果是偶数个，先找到上中点，让链表右侧的指针向左指
        //a->b->c->c->b->a  ==>a->b->c c<-b<-a
        //慢指针-上中点
        ListNode sPointer = head;
        //慢指针-下中点
        ListNode sLowerPointer = null;
        //快指针
        ListNode fPointer = head;
        //是否是偶数
        boolean isEven = false;
        while (null != fPointer){
            if (null == fPointer.next){
                break;
            }
            if (null == fPointer.next.next){
                isEven = true;
                break;
            }
            sPointer = sPointer.next;
            fPointer = fPointer.next.next;
        }
        if (isEven){
            sLowerPointer = sPointer.next;
            sPointer.next = null;
        }else {
            sLowerPointer = sPointer;
        }

        //2.1 反转右侧链表
        //最终回来到尾节点
        ListNode tailHead = reverseList(sLowerPointer);
        //2.2 分别从头尾指针开始向中间遍历
        boolean isPalindrome = true;
        ListNode headTraverse = head;
        ListNode tailTraverse = tailHead;
        while (null != headTraverse && null != tailTraverse){
            if (headTraverse.val != tailTraverse.val){
                isPalindrome = false;
                break;
            }
            headTraverse = headTraverse.next;
            tailTraverse = tailTraverse.next;
        }

        //3.1 链表还原
        sLowerPointer = reverseList(tailHead);
        sPointer.next = isEven ? sLowerPointer : sLowerPointer.next;

        return isPalindrome;
    }

    /**
     * 反转链表，并返回头结点
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head){
        ListNode reversePre = null;
        ListNode reverseCur = head;
        while (null != reverseCur){
            ListNode next = reverseCur.next;

            reverseCur.next = reversePre;
            reversePre = reverseCur;
            reverseCur = next;
        }
        return reversePre;
    }

}
