package com.wy.mca.structure.graph;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description 图-节点表示
 * @Author wangyong01
 * @Date 2022/8/8 9:43 下午
 * @Version 1.0
 */
@Setter
@Getter
public class GraphNode <T>{

    /**
     * 节点值
     */
    private T value;

    /**
     * 入度：指向该节点的边有几条
     */
    private int in;

    /**
     * 出度：从该节点出去的边有几条
     */
    private int out;

    /**
     * 当前节点的邻接节点
     */
    private List<GraphNode<T>> nextNodes = Lists.newArrayList();

    /**
     * 当前节点邻接的边
     */
    private List<GraphEdge> nextEdges = Lists.newArrayList();

    public GraphNode(T value) {
        this.value = value;
    }

    public List<GraphNode<T>> getNextNodes() {
        return nextNodes;
    }
}
