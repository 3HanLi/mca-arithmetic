package com.wy.mca.structure.graph;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @Description 拓扑排序
 * @Author wangyong01
 * @Date 2022/8/9 9:03 下午
 * @Version 1.0
 */
public class TopologicalSort {

    public static void main(String[] args) {
        GraphNode<String> aNode = new GraphNode<>("a");
        GraphNode<String> bNode = new GraphNode<>("b");
        GraphNode<String> dNode = new GraphNode<>("d");
        GraphNode<String> eNode = new GraphNode<>("e");
        GraphNode<String> fNode = new GraphNode<>("f");

        aNode.setNextNodes(Lists.newArrayList(dNode));
        bNode.setNextNodes(Lists.newArrayList(dNode, eNode, fNode));
        dNode.setNextNodes(Lists.newArrayList(eNode, fNode));
        eNode.setNextNodes(Lists.newArrayList(fNode));

        dNode.setIn(2);
        eNode.setIn(2);
        fNode.setIn(3);

        Graph<String> graph = new Graph<>();
        Map<String, GraphNode<String>> graphNodeMap = Maps.newHashMap();
        graphNodeMap.put("a", aNode);
        graphNodeMap.put("b", bNode);
        graphNodeMap.put("d", dNode);
        graphNodeMap.put("e", eNode);
        graphNodeMap.put("f", fNode);
        graph.setGraphNodeMap(graphNodeMap);

        List<GraphNode> graphNodes = typologicalSort(graph);
        graphNodes.stream().map(GraphNode::getValue).forEach(System.out::println);
    }

    /**
     * 拓扑排序
     * 1、拓扑排序：必须是有向图、且无环
     *
     * 2、具体步骤
     *    2.1 在图中找到所有入度为0的点输出
     *    2.2 把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
     *    2.3 图的所有点都被删除后，依次输出的顺序就是拓扑排序
     *
     * 3 拓扑排序特点：排序结果不唯一
     * @param graph
     * @return
     */
    public static List<GraphNode> typologicalSort(Graph graph){
        Collection<GraphNode<String>> nodes = graph.getGraphNodeMap().values();

        //1.1 记录节点的入度
        Map<GraphNode<String>, Integer> graphNodeInMap = Maps.newHashMap();
        //1.2 将入度为0的节点加入队列，因为：我们需要消除入度为零的节点的边
        Queue<GraphNode<String>> zeroInQueue = new LinkedList<>();
        for (GraphNode<String> node : nodes){
            graphNodeInMap.put(node, node.getIn());
            if (node.getIn() == 0){
                zeroInQueue.add(node);
            }
        }

        List<GraphNode> sortList = Lists.newArrayList();
        while (!zeroInQueue.isEmpty()){
            //2.1 弹出入度为0的节点
            GraphNode<String> graphNode = zeroInQueue.poll();
            sortList.add(graphNode);

            for (GraphNode<String> next : graphNode.getNextNodes()){
                //2.2 逻辑删除入度为0的节点：依次将入度为0的节点的邻接节点的入度-1
                graphNodeInMap.put(next, graphNodeInMap.get(next)-1);
                //2.3 如果邻接节点的入度为0，继续加入队列进行遍历
                if (graphNodeInMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }

        return sortList;
    }

}
