package com.wy.mca.structure.list;

import com.wy.mca.common.ListNode;

/**
 * 1）问题描述：给定两个可能有环也可能无环的链表，如果他们相交，返回第一个相交节点，否则返回空
 *    1.1 条件：两个链表的长度之和是N
 *    1.2 要求：时间复杂度O(N)，空间复杂度O(1)
 * 2）解题思路
 *
 * @author wangyong01
 */
public class FindFirstIntersectNode {

    public static void main(String[] args) {
        //1 链表无环不相交
        //1->2
        //11->12->13->14

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);

        ListNode head2 = new ListNode(11);
        head2.next = new ListNode(12);
        head2.next.next = new ListNode(13);
        head2.next.next.next = new ListNode(14);

        ListNode firstIntersectNode = getFirstIntersectNode(head1, head2);
        System.out.println("无环链表1:" + head1.elements());
        System.out.println("无环链表2:" + head2.elements());
        System.out.println("无环链表相交节点为" + firstIntersectNode);

        //2 链表无环相交
        /**
         * 1    ->2        ->3
         *                /
         * 11->12->13->14
         */
        ListNode intersectNode = new ListNode(3);
        intersectNode.next = new ListNode(4);

        head1.next.next = intersectNode;
        head2.next.next.next.next = intersectNode;

        ListNode firstIntersectNode2 = getFirstIntersectNode(head1, head2);
        System.out.println("无环链表1:" + head1.elements());
        System.out.println("无环链表2:" + head2.elements());
        System.out.println("无环链表相交节点为" + firstIntersectNode2);

        //3 有环相交，节点为同一个
        intersectNode.next.next = new ListNode(5);
        intersectNode.next.next.next = new ListNode(6);
        intersectNode.next.next.next.next = new ListNode(7);
        intersectNode.next.next.next.next.next = new ListNode(4);

        ListNode firstIntersectNode3 = getFirstIntersectNode(head1, head2);
        System.out.println("无环链表1:" + head1.elements());
        System.out.println("无环链表2:" + head2.elements());
        System.out.println("无环链表相交节点为" + firstIntersectNode3);

        //4 有环相交于不同节点 就不测试了

    }

    public static ListNode getFirstIntersectNode(ListNode head1, ListNode head2){
        //1 查找链表的入环节点，如果没有返回空
        ListNode loopNode1 = getLoopNode(head1);
        ListNode loopNode2 = getLoopNode(head2);

        //2 场景分析
        //2.1 两个链表都没有入环节点
        if (null == loopNode1 && null == loopNode2){
            return noLoopIntersect(head1, head2);
        }

        //2.2 两个链表都有环
        if (null != loopNode1 && null != loopNode2){
            return bothLoopIntersect(head1, loopNode1, head2, loopNode2);
        }

        //2.3 两个链表，一个有环，一个无环，那么一定不相交
        return null;
    }

    /**
     * 无环相交
     *
     * @param head1
     * @param head2
     * @return
     */
    private static ListNode noLoopIntersect(ListNode head1, ListNode head2){
        //1 遍历两个链表到末尾，判断尾节点是否相等，相等说明相交，并记录哪个是长链表

        //1.1 场景模拟：假设head1是长链表，head2是短链表
        ListNode longNode = head1;
        ListNode shortNode = head2;

        //1.2 使用n表示链表长度的同时，记录哪个链表长，两个链表长度差值是多少
        int n = 1;
        while (null != longNode.next){
            longNode = longNode.next;
            n++;
        }

        n--;
        while (null != shortNode.next){
            shortNode = shortNode.next;
            n--;
        }

        //2 如果尾节点相等，说明相交
        if (longNode == shortNode){
            //2.1 判断长链表
            longNode = n > 0 ? head1 : head2;
            shortNode = n > 0 ? head2 : head1;

            //2.2 让长链表先走差值步
            n = Math.abs(n);
            while (n > 0){
                longNode = longNode.next;
                n--;
            }
            //2.3 两个链表一块走，当节点相等时，说明相交
            while (longNode != shortNode){
                longNode = longNode.next;
                shortNode = shortNode.next;
            }
            return longNode;
        }

        //3 如果尾节点不想等，说明不相交
        return null;
    }

    /**
     * 有环相交
     * @param head1     链表1的头节点
     * @param loopNode1 链表1的入环节点
     * @param head2     链表2的头节点
     * @param loopNode2 链表2的入环节点
     * @return
     */
    private static ListNode bothLoopIntersect(ListNode head1, ListNode loopNode1, ListNode head2, ListNode loopNode2){
        //1 如果两个链表的入环节相等，那么就是计算以入环节点为终点的相交节点
        if (loopNode1 == loopNode2){
            ListNode longNode = head1;
            ListNode shortNode = head2;

            int n = 0;
            while (longNode != loopNode1){
                longNode = longNode.next;
                n ++;
            }
            while (shortNode != loopNode1){
                shortNode = shortNode.next;
                n --;
            }
            longNode = n > 0 ? head1 : head2;
            shortNode = n > 0 ? head2 : head1;
            n = Math.abs(n);

            while (n > 0){
                longNode = longNode.next;
                n--;
            }

            while (longNode != shortNode){
                longNode = longNode.next;
                shortNode = shortNode.next;
            }

            return longNode;
        }else {
            //2 如果两个链表的入环节点不相等，
            //2.1 那么就让其中一个链表head1从自己的入环节点loopNode1转圈，能碰到loopNode2说明链表相交，loopNode1和loopNode2都是相交节点
            ListNode next = head1.next;
            while (next != loopNode1){
                if (next == loopNode2){
                    return loopNode1;
                }
                next = next.next;
            }

            //2.2 否则，如果转了一圈都没碰到loopNode2，那么说明不相交
            return null;
        }
    }

    /**
     * 返回链表的入环节点，没有返回空
     *
     * @param head
     * @return
     */
    public static ListNode getLoopNode(ListNode head){
        if (null == head || null == head.next){
            return null;
        }
        //1.1 使用快慢指针
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        //1.2 慢指针走一步，快指针走两步，如果快指针和慢指针相撞，说明有环
        while (slow != fast){
            if (null == fast || null == fast.next){
                return null;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        //1.3 让快指针回到头节点，快指针和慢指针各走一步，当他们相撞时，就是入环节点
        fast = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

}
