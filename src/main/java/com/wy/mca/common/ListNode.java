package com.wy.mca.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 单向链表定义
 * @author boss
 */
@Data
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(){}

    public ListNode(int x) { this.val = x; }

    public int length(){
        int i= 1;
        ListNode curr = this.next;
        while (curr != null){
            i ++;
            curr = curr.next;
        }
        return i;
    }

    public void print(){
        System.out.print(this.val + "\t");
        ListNode curr = this.next;
        while (curr != null){
            System.out.print(curr.val + "\t");
            curr = curr.next;
        }
        System.out.println();
    }

    public List<Integer> elements(){
        List<Integer> eleList = Lists.newArrayList();
        //如果链表是循环链表，避免死循环
        Set<ListNode> nodeSet = Sets.newHashSet();

        ListNode curr = this;
        while (curr != null){
            eleList.add(curr.val);
            if (nodeSet.contains(curr)){
                nodeSet.add(curr);
            }else {
                break;
            }
            curr = curr.next;
        }
        return eleList;
    }

}
