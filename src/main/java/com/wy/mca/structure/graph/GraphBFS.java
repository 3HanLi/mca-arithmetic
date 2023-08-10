package com.wy.mca.structure.graph;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @Description 图的宽度优先遍历
 * @Author wangyong01
 * @Date 2022/8/9 5:39 下午
 * @Version 1.0
 */
public class GraphBFS { //Breath First Search

    public static void main(String[] args) {
        GraphNode<String> aNode = new GraphNode<>("a");
        GraphNode<String> bNode = new GraphNode<>("b");
        GraphNode<String> cNode = new GraphNode<>("c");
        GraphNode<String> kNode = new GraphNode<>("k");
        GraphNode<String> eNode = new GraphNode<>("e");
        GraphNode<String> fNode = new GraphNode<>("f");

        aNode.setNextNodes(Lists.newArrayList(bNode, cNode));
        bNode.setNextNodes(Lists.newArrayList(cNode, kNode));
        cNode.setNextNodes(Lists.newArrayList(eNode, fNode));
        kNode.setNextNodes(Lists.newArrayList(eNode, aNode));
        eNode.setNextNodes(Lists.newArrayList(fNode));

        List<String> bfsResultList = bfs(aNode);
        System.out.println("宽度优先遍历:" + bfsResultList);
    }

    /**
     * 图的宽度优先遍历：
     * 1、整体思路：每次遍历节点时，都尝试获取所有邻接节点
     * 2、实现方案：队列
     * 3、具体步骤：
     *    3.1、准备一个Set集合 和 队列，并指定开始节点；Set集合表示已经遍历过的节点；队列表示节点的遍历顺序；
     *    3.2、先压入开始节点；
     *    3.3、如果队列不为空，弹出队列头部元素，并遍历邻接节点；
     *    3.4、判断Set集合是否存在邻接节点，不存在的话，将节点压入Set集合 和 队列；
     *    3.5、重复步骤3、4，直到队列为空
     *
     * @param startNode
     * @return
     */
    public static List<String> bfs(GraphNode<String> startNode){
        //1 遍历结果
        List<String> bfsResultList = Lists.newArrayList();

        //2.1 将开始节点压入Set集合和队列
        Queue<GraphNode> graphNodeQueue = new LinkedList<>();
        Set<GraphNode> graphNodeSet = Sets.newHashSet(startNode);
        graphNodeQueue.add(startNode);
        while (!graphNodeQueue.isEmpty()){
            //2.2 队列不为空，先弹出头部节点；
            GraphNode<String> graphNode = graphNodeQueue.poll();
            bfsResultList.add(graphNode.getValue());
            //2.3 遍历当前节点邻接节点，判断Set集合是否存在，不存在则加入Set集合和队列；
            List<GraphNode<String>> nextNodes = graphNode.getNextNodes();
            for (GraphNode next : nextNodes){
                if (!graphNodeSet.contains(next)){
                    graphNodeSet.add(next);
                    graphNodeQueue.add(next);
                }
            }
        }

        return bfsResultList;
    }

}
