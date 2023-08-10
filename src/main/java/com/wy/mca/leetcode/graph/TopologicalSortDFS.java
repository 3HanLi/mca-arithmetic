package com.wy.mca.leetcode.graph;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 拓扑排序
 * @Author wangyong01
 * @Date 2022/8/10 8:59 下午
 * @Version 1.0
 */
public class TopologicalSortDFS {

    public static void main(String[] args) {

    }

    /**
     * 拓扑排序
     * 1、排序思路
     *    1.1 计算每个节点所对应的图 所拥有的节点数量
     *    1.2 按照节点数量倒排序，得到的结果即为拓扑排序结果
     * 2、举例说明
     *    2.1 假如图为：
     *        a -> b
     *    2.2 那么：a节点所在的图拥有的节点数量为2，b节点所在的图所拥有的节点数量为1，因而只要按照节点 所对应的图拥有的节点数量倒排序即可
     *
     * @param simpleGraphNode
     * @return
     */
    public List<SimpleGraphNode> sort(SimpleGraphNode simpleGraphNode){
        Map<SimpleGraphNode, SimpleGraphRecord> graphRecordMap = Maps.newHashMap();
        for (SimpleGraphNode next : simpleGraphNode.getNextNodes()){
            initRecord(graphRecordMap, next);
        }

        Collection<SimpleGraphRecord> graphRecords = graphRecordMap.values();
        List<SimpleGraphRecord> sortList = graphRecords.stream().sorted((x, y) -> (y.getGraphSize() - x.getGraphSize())).collect(Collectors.toList());
        List<SimpleGraphNode> sortNodeList = sortList.stream().map(SimpleGraphRecord::getSimpleGraphNode).collect(Collectors.toList());

        return sortNodeList;
    }

    /**
     * 给定一个节点，返回节点 和 节点对应的图的大小
     *
     * @param graphRecordMap
     * @param simpleGraphNode
     * @return
     */
    private static SimpleGraphRecord initRecord(Map<SimpleGraphNode, SimpleGraphRecord> graphRecordMap,SimpleGraphNode simpleGraphNode){
        if (graphRecordMap.containsKey(simpleGraphNode)){
            return graphRecordMap.get(simpleGraphNode);
        }

        int graphSize = 1;
        for (SimpleGraphNode next : simpleGraphNode.getNextNodes()){
            SimpleGraphRecord simpleGraphRecord = initRecord(graphRecordMap, next);
            graphSize += simpleGraphRecord.getGraphSize();
        }
        SimpleGraphRecord simpleGraphRecord = new SimpleGraphRecord(simpleGraphNode, graphSize);
        graphRecordMap.put(simpleGraphNode, simpleGraphRecord);

        return simpleGraphRecord;
    }

}
