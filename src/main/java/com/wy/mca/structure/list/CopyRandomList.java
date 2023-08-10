package com.wy.mca.structure.list;

import com.wy.mca.common.RandomListNode;

import java.util.HashMap;

/**
 * 1) 问题描述：复制随机链表
 * 2) 案例说明，假如随机链表结构如下
 *  ↑ - - - - ↓
 *  ↑         ↓
 *  1 - ->2 ->3 ->null
 *  ↑     ↓
 *  ↑ - - ↓
 */
public class CopyRandomList {

    public static void main(String[] args) {
        RandomListNode randomHead = new RandomListNode(1);
        randomHead.next = new RandomListNode(2);
        randomHead.next.next = new RandomListNode(3);

        randomHead.random = randomHead.next.next;
        randomHead.next.random = randomHead;

        RandomListNode randomListNode1 = copyRandomListWithMap(randomHead);
        randomListNode1.print();

        RandomListNode randomListNode2 = copyRandomList(randomHead);
        randomListNode2.print();
    }

    /**
     * 通过容器的方式实现随机链表的拷贝
     *
     * @param randomListNodeHead
     * @return
     */
    public static RandomListNode copyRandomListWithMap(RandomListNode randomListNodeHead){
        if (null == randomListNodeHead){
            return null;
        }

        //1 遍历链表，放入map；key为链表当前节点, value为新创建的节点，值为当前节点的值，且next和random指针为空
        //链表结构 1->2->3->null
        HashMap<RandomListNode, RandomListNode> nodeHashMap = new HashMap<>();
        RandomListNode cur = randomListNodeHead;
        while (null != cur){
            nodeHashMap.put(cur, new RandomListNode(cur.val));
            cur = cur.next;
        }

        //2 遍历链表，取到value；
        cur = randomListNodeHead;
        while (null != cur){
            //2.1 根据当前节点从map中取到复制出来的节点 copyNode
            RandomListNode copyNode = nodeHashMap.get(cur);
            //2.2 让复制出来的节点copyNode.next 指向 当前节点的next 指向的新节点
            copyNode.next = nodeHashMap.get(cur.next);
            copyNode.random = nodeHashMap.get(cur.random);
            cur = cur.next;
        }

        return nodeHashMap.get(randomListNodeHead);
    }

    /**
     * 基于链表进行拷贝
     *
     * @param randomListNodeHead
     * @return
     */
    public static RandomListNode copyRandomList(RandomListNode randomListNodeHead){
        if (null == randomListNodeHead){
            return null;
        }

        //1 在链表每个节点之后插入重复的节点copyNode，copyNode.random指针为空
        //1.1 假如链表为：1->2->3->null，插入后结果为：1->1`->2->2`->3->3`->null
        RandomListNode cur = randomListNodeHead;
        RandomListNode next = null;
        while (null != cur){
            next = cur.next;
            cur.next = new RandomListNode(cur.val);
            cur.next.next = next;
            cur = next;
        }

        //2 设置临时节点的random指针
        //2.1 假如链表插入重复节点后为：1->1`->2->2`->3->3`->null，设置1`,2`,3`的random指针
        //2.2 思路：copyNode.random = node.random.next;
        cur = randomListNodeHead;
        while (null != cur){
            next = cur.next;
            next.random = null != cur.random ? cur.random.next : null;
            cur = cur.next.next;
        }

        //3 将copyNode连起来，并从原始链表中分离出来
        cur = randomListNodeHead;
        next = randomListNodeHead.next;

        RandomListNode copy = null;
        while (null != cur){
            //3.1.1 定位到复制节点copyNode
            copy = cur.next;
            //3.2 恢复原始链表，并跳转下个节点
            cur.next = cur.next.next;
            cur = cur.next;
            //3.1.2 连接复制节点
            copy.next = null != cur ? cur.next : null;
        }

        return next;
    }
}
