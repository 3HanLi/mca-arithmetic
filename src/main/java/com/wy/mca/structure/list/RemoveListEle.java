package com.wy.mca.structure.list;

import com.wy.mca.common.ListNode;
import com.wy.mca.util.MyListUtil;

import java.util.List;

/**
 * 1）问题描述：
 *
 * 2）案例说明：
 *
 * 3）解题思路：
 *
 * @author wangyong01
 */
public class RemoveListEle {

    public static void main(String[] args) {
        listRemoveValidate();
    }

    public static void listRemoveValidate(){
        System.out.println("链表元素删除简单测试");
        ListNode head = MyListUtil.generateIncrementListNode(10);
        head.print();

        head = removeEle(head, 5);
        head.print();

        head = removeEle(head, 1);
        head.print();

        System.out.println("链表随机测试删除");
        head = MyListUtil.generateRandomListNode(200, 10);

        List<Integer> elementList = head.elements();
        int randomIndex = (int)(Math.random() * elementList.size());
        Integer removeEle = elementList.get(randomIndex);

        removeEle(head, removeEle);
        elementList.remove(removeEle);

        List<Integer> remainEleList = head.elements();
        boolean retainAll = remainEleList.retainAll(elementList);
        if (!retainAll){
            System.out.println("Success");
        }
    }

    /**
     * 删除单链表指定节点
     * 举例说明
     * 1) 链表为：2 -> 2 -> 3 -> 1 -> 2 -> 1
     * 2) 删除节点为2，删除后：3 -> 1 -> 1
     *
     * 解题思路：
     * 1) 让head来到第一个不需要删除的节点，作为新的head
     * 2) 准备pre和cur指针，cur代表要删除的节点，pre代表当前节点的上一个节点
     * @param head
     * @param ele
     * @return 返回新的头节点
     *
     */
    public static ListNode removeEle(ListNode head, int ele){
        while (head != null){
            if (head.val != ele){
                break;
            }
            head = head.next;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null){
            if (cur.val == ele){
                pre.next = cur.next;
            }
            pre = pre.next;
            cur = cur.next;
        }

        return head;
    }

}
