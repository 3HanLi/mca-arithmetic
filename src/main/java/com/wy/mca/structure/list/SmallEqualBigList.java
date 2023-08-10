package com.wy.mca.structure.list;

import com.google.common.collect.Lists;
import com.wy.mca.common.ListNode;
import com.wy.mca.util.MyListUtil;

import java.util.List;

/**
 * 1) 问题描述：将单链表按照指定值划分为为左中右三部分
 *
 * 2) 举例说明：6->4->5->1->8->7->5 ，小于5的放左边，大于5的放右边，等于5的放中间，得到链表：4->1->5->5->6->8->7
 *
 * 3) 解题思路
 *
 */
public class SmallEqualBigList {

    public static void main(String[] args) {
        int[] nums = {6,4,5,1,8,7,5};
        int partVal = 5;
        ListNode head = MyListUtil.generateList(nums);

        System.out.println("通过集合将链表划分为3部分");
        ListNode newHead = partitionListWithCollection(head, 5);
        newHead.print();;

        newHead = partitionList(head, partVal);
        newHead.print();
    }

    public static ListNode partitionListWithCollection(ListNode head, int partVal){
        List<Integer> elements = head.elements();
        List<Integer> leftList = Lists.newArrayList();
        List<Integer> eqList = Lists.newArrayList();
        List<Integer> rightList = Lists.newArrayList();

        for (Integer ele : elements){
            if (ele < partVal){
                leftList.add(ele);
            }else if (ele == partVal){
                eqList.add(ele);
            }else {
                rightList.add(ele);
            }
        }
        leftList.addAll(eqList);
        leftList.addAll(rightList);

        int[] eleArr = new int[leftList.size()];
        for (int i=0; i<leftList.size(); i++){
            eleArr[i] = leftList.get(i);
        }

        ListNode listNode = MyListUtil.generateList(eleArr);
        return listNode;
    }

    /**
     * 链表划分
     * @param head
     * @param partVal
     * @return
     */
    private static ListNode partitionList(ListNode head, int partVal){
        //小于区域的 头指针 和 尾指针
        ListNode sH = null, sT = null;
        //等于区域的 头指针 和 尾指针
        ListNode eH = null, eT = null;
        //大于区域的 头指针 和 尾指针
        ListNode mH = null, mT = null;

        while (null != head){
            int val = head.val;
            ListNode curNode = new ListNode(val);
            if (val < partVal){
                if (null == sH){
                    sH = curNode;
                    sT = curNode;
                }else {
                    sT.next = curNode;
                    sT = sT.next;
                }
            }else if (val == partVal){
                if (null == eH){
                    eH = curNode;
                    eT = curNode;
                }else {
                    eT.next = curNode;
                    eT = eT.next;
                }
            }else {
                if (null == mH){
                    mH = curNode;
                    mT = curNode;
                }else {
                    mT.next = curNode;
                    mT = mT.next;
                }
            }
            head = head.next;
        }
        //2 理论上连接：sT.next=eH, eT.next=mH

        //2.1 判断sT是否为空，不为空进行连接
        if (null != sT){
            sT.next = eH;
            eT = null == eT ? sT : eT;
        }
        //2.2 判断eT是否为空
        if (null != eT){
            eT.next = mH;
        }

        return null != sH ? sH : (null != eH ? eH : mH);
    }
}
