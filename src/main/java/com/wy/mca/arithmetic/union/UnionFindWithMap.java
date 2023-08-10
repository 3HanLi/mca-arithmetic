package com.wy.mca.arithmetic.union;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @Description 并查集 Map实现
 * @Author wangyong01
 * @Date 2022/7/19 4:55 下午
 * @Version 1.0
 */
public class UnionFindWithMap <V> {

    /**
     * 元素和集合的关系，一开始指向自己
     */
    private Map<V, Node<V>> nodeMap = Maps.newHashMap();

    /**
     * 节点和父节点的关系
     */
    private Map<Node<V>, Node<V>> parents = Maps.newHashMap();

    /**
     * 最头部节点 挂载 的集合数量
     */
    private Map<Node<V>, Integer> sizeMap = Maps.newHashMap();

    /**
     * 并查集
     * 1 问题描述
     *   1.1 假如现在有一堆集合，每个集合中只有一个元素，比如：a b c d e f g，每个元素可以认为是一个仅包含自己的集合
     *   1.2 需要经常执行如下方法
     *       a) 判断两个元素是否在一个集合：isSameSet(a, b)
     *       b) 将a和b所在的两个集合合并：unionSet
     *   1.3 希望上述方法的时间复杂度是O(1)
     *
     * 2 解决思路
     *   2.1 一开始把每个节点包起来，指针指向自己，比如：a -> a，b -> b
     *   2.2 方法执行
     *       a) 集合 a 和 b 合并
     *          a -> a
     *          b -> a
     *          只需要将 b 指向 a即可，头部节点也叫做当前元素的代表节点
     *       b) 判断 a 和 b 是否在一个集合，只需要向上找到头节点，判断头节点是否为同一个即可
     *
     * 3 并查集核心
     *   一开始链路很长，后面每找一次代表节点的同时，就会将沿途链路上的节点挂到代表节点上，这样就会保证后面再次查询的时候，时间复杂度为O(1)
     *
     * 4 并查集用途
     *   主要用于解决图的连通性
     * @param valueList
     */
    public UnionFindWithMap(List<V> valueList){
        Assert.notEmpty(valueList, "集合为空");

        for (V v : valueList){
            Node<V> node = new Node<>(v);
            nodeMap.put(v, node);
            parents.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    /**
     * 判断2个元素是否同属于一个集合
     * @param a
     * @param b
     * @return
     */
    public boolean isSameSet(V a, V b){
        return findHead(a) == findHead(b);
    }

    /**
     * 查找代表该节点的头部节点
     *
     * @param a
     * @return
     */
    private Node<V> findHead(V a){
        Stack<Node> childrenStack = new Stack<>();
        Node<V> cur = nodeMap.get(a);
        //1.1 找到代表该节点的头部节点
        while (cur != parents.get(cur)){
            //2.1 在遍历的过程中，将沿途节点加入栈中
            childrenStack.add(cur);
            cur = parents.get(cur);
        }

        //2.2 将沿途节点都挂在头部节点，缩短路径
        while (!childrenStack.isEmpty()){
            parents.put(childrenStack.pop(), cur);
        }

        return cur;
    }

    /**
     * 将 a 和 b 进行合并
     * @param a
     * @param b
     */
    public void unionSet(V a, V b){
        //1.1 找到 a 和 b节点所在的集合的头部节点
        Node<V> aHead = findHead(a);
        Node<V> bHead = findHead(b);
        //1.2 a 和 b 的头部节点不同，说明不在一个集合，可以进行合并
        if (aHead != bHead){
            //1.3 查看哪个集合小
            Integer aSize = sizeMap.get(aHead);
            Integer bSize = sizeMap.get(bHead);
            Node<V> big = aSize >= bSize ? aHead : bHead;
            Node<V> small = aSize >= bSize ? bHead : aHead;
            //1.4 将小集合挂在大集合下面，然后更新大集合 最头部节点 集合数量
            parents.put(small, big);
            sizeMap.put(big, aSize + bSize);
            //1.5 小集合 头部节点变更，记录的集合数量移除
            sizeMap.remove(small);
        }
    }

    public int getUnionSize(){
        return sizeMap.size();
    }
}

@Data
@AllArgsConstructor
class Node<V>{

    private V v;

}