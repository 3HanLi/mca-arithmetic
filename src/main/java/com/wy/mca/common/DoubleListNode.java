package com.wy.mca.common;

import lombok.Data;

/**
 * @author wangyong01
 */
@Data
public class DoubleListNode {

    /**
     * 值
     */
    public int val;

    /**
     * 上一个节点
     */
    public DoubleListNode pre;

    /**
     * 下一个节点
     */
    public DoubleListNode next;

    public DoubleListNode(int val) {
        this.val = val;
    }

    public void printFromHead(){
        DoubleListNode cur = this;
        while (cur != null){
            System.out.print(cur.val + "\t");
            cur = cur.next;
        }

        System.out.println();
    }

}
