package com.wy.mca.structure.list;

import com.google.common.collect.Lists;
import com.wy.mca.common.ListNode;

import java.util.List;

/**
 * 1）问题描述：链表首尾相连
 *
 * 2）按理说明：依次将链表的首尾相连，第2个和倒数第二个相连，以此类推，比如：
 *    奇数：1->2->3->4->3->2->1，返回中点4，连接起来为：1->1->2->2-3->3->4
 *    偶数：1->2->3->3->2->1，返回上中点3，连接起来为：1->1->2->2-3->3
 */
public class HeadTailLink {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(1);

        System.out.println("原始链表:" + head.elements());

        System.out.println("通过集合将链表首尾相连:" + headTailLinkWithCollection(head));

        headTailLink(head);
        System.out.printf("直接操作将链表首尾相连:" + head.elements());
    }

    public static List<Integer> headTailLinkWithCollection(ListNode head){
        if (null == head){
            return Lists.newArrayList();
        }
        List<Integer> elements = head.elements();
        List<Integer> newElements = Lists.newArrayList();

        for (int i=0, j=elements.size()-1; i<elements.size(); i++, j--){
            if (i > j){
                break;
            }
            newElements.add(elements.get(i));
            newElements.add(elements.get(j));
        }
        return newElements;
    }

    /**
     * 1) 依次将链表的首尾，第二个和倒数第二个，第三个和倒数第三个..依次相连，形成一个新的链表，举例说明:
     *    假如链表显示l1->l2-l3->l4->l1->l2->l3->l4，请首尾相连形成新链表，如l1->l4->l2->l3->l3->l2->l4->l1
     *
     * @param head
     */
    public static ListNode headTailLink(ListNode head){
        if (null == head || null == head.next || null == head.next.next){
            return head;
        }

        //1.1 返回中点或者上中点
        //奇数：1->2->3->4->3->2->1，返回中点4，连接起来为：1->1->2->2-3->3->4
        //偶数：1->2->3->3->2->1，返回上中点3，连接起来为：1->1->2->2-3->3
        ListNode midOrUpperNode = ListMidWithFastSlowPointer.midOrUpperNode(head);

        //1.2 头节点
        ListNode leftHead = head;
        //1.3 头节点下一个节点
        ListNode leftHeadNext = leftHead.next;

        //2.1 获取中点下一个节点，进行反转
        ListNode midNext = midOrUpperNode.next;
        ListNode rightHead = ReverseList.reverseNode(midNext);
        midOrUpperNode.next = null;

        //2 尾节点自右向左遍历
        while (null != rightHead){
            //2.1 倒数节点
            ListNode curReverseNode = new ListNode(rightHead.val);

            //2.2 首尾相连
            leftHead.next = curReverseNode;

            //边界条件：leftHeadNext为空时，说明连接完毕
            if (leftHeadNext == null){
                break;
            }
            //2.3 curHead右移动，rightHead向左指
            leftHead = leftHeadNext;
            leftHeadNext = leftHeadNext.next;
            rightHead = rightHead.next;

            //2.4 rightHead和curHead相连
            curReverseNode.next = leftHead;
        }

        return head;
    }

}
