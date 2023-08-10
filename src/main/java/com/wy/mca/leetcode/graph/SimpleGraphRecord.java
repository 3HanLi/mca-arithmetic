package com.wy.mca.leetcode.graph;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 节点 和 节点对应的图的大小
 * @Author wangyong01
 * @Date 2022/8/10 9:06 下午
 * @Version 1.0
 */
@Getter
@Setter
public class SimpleGraphRecord {

    /**
     * 节点
     */
    private SimpleGraphNode simpleGraphNode;

    /**
     * 节点所在图 拥有的节点数量
     */
    private int graphSize;

    public SimpleGraphRecord(SimpleGraphNode simpleGraphNode, int graphSize) {
        this.simpleGraphNode = simpleGraphNode;
        this.graphSize = graphSize;
    }
}
