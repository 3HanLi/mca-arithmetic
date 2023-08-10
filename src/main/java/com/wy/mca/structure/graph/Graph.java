package com.wy.mca.structure.graph;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

/**
 * @Description 图的表示形式
 * 1、图的概念梳理
 * 1.1 有向图：两个节点之间有箭头指向
 * 1.2 无向图：两个节点之间没有箭头指向
 * 1.3 权重：连接两个节点的边的值
 *
 * 2、图的表示形式
 * 2.1 邻接矩阵：将图上面的每个节点排序，然后生成对应的的二维数组；如果两个节点是连接节点，则在二维数组对应节点赋值上权重；
 *     举例说明：a -> b，权重是3，则对应二维数组
 *        a  b
 *     a  0  3
 *     b  ~  0
 * 2.2 二维数组：具体来说就是一个列为3的二维数组matrix；每一行的第0个元素是权重，第1个元素是from，第二个节点是to
 *     举例说明：
 *     3 0 7
 *     5 1 2
 *     6 2 7
 *     表示：0->7的权重是3，1->2的权重是5，2->7的权重是6
 * 2.3 一维数组：具体来说就是下标index代表元素的值，只能表示不带权重的图；
 *    举例说明：
 *    {3,1,1,1}
 *    0 -> 3
 *    1 -> 1
 *    2 -> 1
 *    3 -> 1
 * 总结：图的表示方式比较多，写法也不同，因而我们只需要将图转换为自己熟悉的图结构即可
 *
 * 3 总结起来就是：
 *   3.1 由点的集合和边的集合构成
 *   3.2 存在有向图和无向图的概念，但实际上都可以用有向图来表达
 *   3.3 边上可能带有权值
 *
 * 4 这里图的表示形式：节点的集合 加上 边的集合
 *
 * @Author wangyong01
 * @Date 2022/8/8 9:49 下午
 * @Version 1.0
 */
@Setter
@Getter
public class Graph <T>{

    /**
     * 图上面的所有节点：元素 和 节点的关系
     * key：节点上的元素值
     * value：节点对象，其实就是将值包起来的对象
     */
    private Map<T, GraphNode<T>> graphNodeMap = Maps.newHashMap();

    /**
     * 图上面的所有边
     */
    private Set<GraphEdge> graphEdgeSet = Sets.newHashSet();

}
