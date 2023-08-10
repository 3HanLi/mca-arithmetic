package com.wy.mca.structure.graph;

import java.util.Map;

/**
 * @Description 图的构建
 * @Author wangyong01
 * @Date 2022/8/8 9:44 下午
 * @Version 1.0
 */
public class GraphGenerate {

    public static void main(String[] args) {
        int[][] matrix = {
                {5,0,7},
                {3,0,1}
        };
        Graph graph = createGraph(matrix);
        System.out.println("graph Nodes:" + graph.getGraphNodeMap());
        System.out.println("graph Edges:" + graph.getGraphEdgeSet());
    }

    /**
     * 将二维数组转为图
     * @param matrix
     * @return
     */
    public static Graph createGraph(int[][] matrix){
        //1 图
        Graph graph = new Graph();

        for (int i=0; i<matrix.length; i++){
            //1.1 权重
            int weight = matrix[i][0];
            //1.2 fromNode
            int from = matrix[i][1];
            //1.3 toNode
            int to = matrix[i][2];

            //2 图上面的所有节点
            Map<Integer, GraphNode<Integer>> graphNodeMap = graph.getGraphNodeMap();
            //2.1 将开始节点 和 结束节点加入图
            if (!graphNodeMap.containsKey(from)){
                graphNodeMap.put(from, new GraphNode(from));
            }
            if (!graphNodeMap.containsKey(to)){
                graphNodeMap.put(to, new GraphNode(to));
            }

            GraphNode fromNode = graphNodeMap.get(from);
            GraphNode toNode = graphNodeMap.get(to);
            //2.2 初始化边
            GraphEdge graphEdge = new GraphEdge(weight, fromNode, toNode);
            //2.3 from节点的邻接节点
            fromNode.getNextNodes().add(toNode);
            //2.4 from节点出度+1、边+1
            fromNode.setOut(fromNode.getOut() + 1);
            fromNode.getNextEdges().add(graphEdge);

            //2.5 to节点入度+1
            toNode.setIn(toNode.getIn() + 1);

            //2.6 图增加一条边
            graph.getGraphEdgeSet().add(graphEdge);
        }

        return graph;
    }

}
