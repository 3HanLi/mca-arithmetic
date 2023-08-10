package com.wy.mca.util;

import cn.hutool.core.util.ArrayUtil;
import com.wy.mca.common.DoubleListNode;
import com.wy.mca.common.ListNode;

/**
 * 链表操作工具类
 */
public class MyListUtil {

    public static ListNode generateIncrementListNode(int size){
        ListNode head = new ListNode(1);
        ListNode cur = head;

        for (int i=1; i<size; i++){
            ListNode nextListNode = new ListNode(cur.getVal() + 1);
            cur.next = nextListNode;
            cur = nextListNode;
        }
        return head;
    }

    /**
     * 生成随机长度的单项链表
     * @param size
     * @return
     */
    public static ListNode generateRandomListNode(int size, int range){
        int randomNum = RandUtil.generatePositiveRandomNum(range);

        ListNode head = new ListNode(randomNum);
        ListNode cur = head;

        for (int i=1; i<size; i++){
            randomNum = RandUtil.generatePositiveRandomNum(range);
            ListNode nextListNode = new ListNode(randomNum);
            cur.next = nextListNode;
            cur = nextListNode;
        }
        return head;
    }

    public static ListNode generateList(int[] arr){
        if (ArrayUtil.isEmpty(arr)){
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i=1; i<arr.length; i++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }

        return head;
    }

    /**
     * 生成随机长度的双向链表
     * @param size
     * @return
     */
    public static DoubleListNode generateRandomDoubleListNode(int size){
        DoubleListNode head = new DoubleListNode(1);
        DoubleListNode cur = head;
        for (int i=1; i<size; i++){
            DoubleListNode nextListNode = new DoubleListNode(cur.getVal() + 1);
            cur.next = nextListNode;
            nextListNode.pre = cur;
            cur = nextListNode;
        }
        return head;
    }

}
