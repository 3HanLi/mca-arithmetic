package com.wy.mca.leetcode.graph;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Description 图的另一种表示形式
 * @Author wangyong01
 * @Date 2022/8/10 9:00 下午
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class SimpleGraphNode {

    /**
     * 节点上的值
     */
    private String value;

    /**
     * 节点的邻接节点
     */
    private List<SimpleGraphNode> nextNodes = Lists.newArrayList();

    public SimpleGraphNode(String value, List<SimpleGraphNode> nextNodes) {
        this.value = value;
        this.nextNodes = nextNodes;
    }

}
