package com.wy.mca.leetcode.list;

import com.google.common.collect.Lists;
import com.wy.mca.common.ListNode;

import java.util.Collections;
import java.util.List;

public class MergeList {

    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(4);
        ListNode listNode13 = new ListNode(5);
        listNode11.setNext(listNode12);
        listNode12.setNext(listNode13);

        ListNode listNode21 = new ListNode(1);
        ListNode listNode22 = new ListNode(3);
        ListNode listNode23 = new ListNode(4);
        listNode21.setNext(listNode22);
        listNode22.setNext(listNode23);

        ListNode listNode31 = new ListNode(2);
        ListNode listNode32 = new ListNode(6);
        listNode31.setNext(listNode32);

        ListNode[] listNodeArr = {listNode11,listNode21,listNode31};
        ListNode listNode = mergeKLists(listNodeArr);
        listNode.print();
    }

    public static ListNode mergeKLists(ListNode[] listNodeArr) {
        if (null == listNodeArr || listNodeArr.length == 0){
            return null;
        }

        List<Integer> elementList = Lists.newArrayList();
        for (ListNode listNode : listNodeArr){
            elementList.addAll(listNode.elements());
        }
        Collections.sort(elementList);

        ListNode listNode = new ListNode(elementList.get(0));
        ListNode head = listNode;
        for (int i=1; i<elementList.size(); i++){
            ListNode next = new ListNode(elementList.get(i));
            listNode.setNext(next);
            listNode = next;
        }

        return head;
    }

}
