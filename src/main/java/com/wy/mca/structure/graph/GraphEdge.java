package com.wy.mca.structure.graph;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 图-边的表示
 * @Author wangyong01
 * @Date 2022/8/8 9:44 下午
 * @Version 1.0
 */
@Setter
@Getter
public class GraphEdge {

    /**
     * 边上面的权重
     */
    private int weight;

    /**
     * 边的开始节点
     */
    private GraphNode fromNode;

    /**
     * 边的结束节点
     */
    private GraphNode toNode;

    public GraphEdge(int weight, GraphNode fromNode, GraphNode toNode) {
        this.weight = weight;
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

}
