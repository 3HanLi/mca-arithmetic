package com.wy.mca.structure.list;

import com.google.common.collect.Lists;
import com.wy.mca.common.ListNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 链表问题解决方案：
 * 1）使用容器（数组、哈希表等）
 * 2）使用快慢指针
 *
 * 求链表中点
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 *
 */
public class ListMidWithFastSlowPointer {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next.next = new ListNode(8);

        List<ListNode> allListNodeList = getAllListNodeList(head);
        List<Integer> nodeValList = allListNodeList.stream().map(ListNode::getVal).collect(Collectors.toList());
        System.out.println("链表:" + nodeValList );

        ListNode node01 = null;
        ListNode node02 = null;
        node01 = midOrUpperNode(head);
        node02 = midOrUpperNodeWithCollection(head);
        System.out.println("输入链表头节点，奇数长度返回中点，偶数长度返回上中点");
        System.out.println(null != node01 ? node01.val : "无");
        System.out.println(null != node02 ? node02.val : "无");

        node01 = midOrLowerNode(head);
        node02 = midOrLowerNodeWithCollection(head);
        System.out.println("输入链表头节点，奇数长度返回中点，偶数长度返回下中点");
        System.out.println(null != node01 ? node01.val : "无");
        System.out.println(null != node02 ? node02.val : "无");

        node01 = midOrUpperNodePre(head);
        node02 = midOrUpperNodePreWithCollection(head);
        System.out.println("输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个");
        System.out.println(null != node01 ? node01.val : "无");
        System.out.println(null != node02 ? node02.val : "无");

        node01 = midOrLowerNodePre(head);
        node02 = midOrLowerNodePreWithCollection(head);
        System.out.println("输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个");
        System.out.println(null != node01 ? node01.val : "无");
        System.out.println(null != node02 ? node02.val : "无");
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * 1->2->3->4->5        ==>3
     * 1->2->3->4->5->6     ==>3
     *
     * @param head
     * @return
     */
    public static ListNode midOrUpperNode(ListNode head){
        if (null == head){
            return null;
        }

        ListNode sPointer = head;
        ListNode fPointer = head;
        while (null != fPointer){
            if (null == fPointer.next || null == fPointer.next.next){
                break;
            }
            sPointer = sPointer.next;
            fPointer = fPointer.next.next;
        }

        return sPointer;
    }

    public static ListNode midOrUpperNodeWithCollection(ListNode head){
        List<ListNode> nodeList = getAllListNodeList(head);
        int size = nodeList.size();

        return size > 0 ? nodeList.get((size-1)/2) : null;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * 1->2->3->4->5        3
     * 1->2->3->4->5->6     4
     *
     * @param head
     * @return
     */
    public static ListNode midOrLowerNode(ListNode head){
        ListNode sPointer = head;
        ListNode fPointer = head;
        while (null != fPointer){
            if (null == fPointer.next){
                break;
            }
            sPointer = sPointer.next;
            fPointer = fPointer.next.next;
        }

        return sPointer;
    }

    public static ListNode midOrLowerNodeWithCollection(ListNode head){
        List<ListNode> nodeList = getAllListNodeList(head);
        int size = nodeList.size();
        return size > 0 ? nodeList.get(size/2) : null;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * 1->2->3->4->5        ==>2
     * 1->2->3->4->5->6     ==>2
     *
     * @param head
     * @return
     */
    public static ListNode midOrUpperNodePre(ListNode head){
        if (null == head || null == head.next || null == head.next.next){
            return null;
        }

        ListNode sPointer = head;
        ListNode fPointer = head.next.next;
        while (null != fPointer){
            if (null == fPointer.next || null == fPointer.next.next){
                break;
            }
            sPointer = sPointer.next;
            fPointer = fPointer.next.next;
        }

        return sPointer;
    }

    public static ListNode midOrUpperNodePreWithCollection(ListNode head){
        List<ListNode> allListNodeList = getAllListNodeList(head);
        int size = allListNodeList.size();
        if (size <= 2){
            return null;
        }
        return allListNodeList.get((size-1)/2 -1);
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     * 1->2->3->4->5        ==>2
     * 1->2->3->4->5->6     ==>3
     * @param head
     * @return
     */
    public static ListNode midOrLowerNodePre(ListNode head){
        if (null == head || null == head.next){
            return null;
        }

        ListNode sPointer = head;
        ListNode fPointer = head.next;
        while (null != fPointer){
            if (null == fPointer.next || null == fPointer.next.next){
                break;
            }
            sPointer = sPointer.next;
            fPointer = fPointer.next.next;
        }

        return sPointer;
    }

    public static ListNode midOrLowerNodePreWithCollection(ListNode head){
        List<ListNode> allListNodeList = getAllListNodeList(head);
        int size = allListNodeList.size();

        return size >=2 ? allListNodeList.get(size/2 -1) : null;
    }

    private static List<ListNode> getAllListNodeList(ListNode head){
        List<ListNode> nodeList = Lists.newArrayList();
        ListNode cur = head;
        while (null != cur){
            nodeList.add(cur);
            cur = cur.next;
        }

        return nodeList;
    }

}
