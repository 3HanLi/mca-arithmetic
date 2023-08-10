package com.wy.mca.structure.graph;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @Description 图的深度优先遍历
 * @Author wangyong01
 * @Date 2022/8/9 8:16 下午
 * @Version 1.0
 */
public class GraphDFS { //Breath First Search

    public static void main(String[] args) {
        GraphNode<String> aNode = new GraphNode<>("a");
        GraphNode<String> bNode = new GraphNode<>("b");
        GraphNode<String> cNode = new GraphNode<>("c");
        GraphNode<String> dNode = new GraphNode<>("d");
        GraphNode<String> eNode = new GraphNode<>("e");
        GraphNode<String> fNode = new GraphNode<>("f");
        GraphNode<String> kNode = new GraphNode<>("k");

        aNode.setNextNodes(Lists.newArrayList(bNode, cNode, kNode));
        bNode.setNextNodes(Lists.newArrayList(cNode, eNode));
        cNode.setNextNodes(Lists.newArrayList(dNode));
        dNode.setNextNodes(Lists.newArrayList(cNode));
        eNode.setNextNodes(Lists.newArrayList(fNode));
        fNode.setNextNodes(Lists.newArrayList(cNode));

        List<String> dfsResultList = dfs(aNode);
        System.out.println("dfsResultList:" + dfsResultList);
    }

    /**
     * 图的深度优先遍历：
     * 1、整体思路：找到一个初始节点，然后一条路走到底，走完之后向上找节点，看看能不能走其他路；能的话就选一条可以走的路继续，不能的话就继续向上找
     * 2、实现方案：
     * 3、具体步骤
     *    3.1 准备一个Set集合 和 栈，并指定开始节点；Set集合表示已经遍历过的节点；栈表示节点的遍历顺序；
     *    3.2 先压入开始节点；
     *    3.3 栈不为空，弹出栈顶元素，并遍历邻接节点；
     *    3.4 判断Set集合是否存在邻接节点，不存在的话，将节点压入Set集合 和 栈；压入即跳出循环 且 需要将原节点压入栈，压入顺序：当前节点，邻接节点
     *    3.5 重复3、4步骤，直至栈为空
     *
     * @param startNode
     * @return
     */
    public static List<String> dfs(GraphNode<String> startNode){
        //1 遍历结果
        List<String> dfsResultList = Lists.newArrayList();

        //2.1 将元素压入栈，压入时即放入遍历结果
        Set<GraphNode<String>> graphNodeSet = Sets.newHashSet(startNode);
        Stack<GraphNode<String>> graphNodeStack = new Stack<>();
        graphNodeStack.push(startNode);
        dfsResultList.add(startNode.getValue());

        while (!graphNodeStack.isEmpty()){
            //2.2 栈不为空，弹出栈顶元素
            GraphNode<String> graphNode = graphNodeStack.pop();

            //2.3 获取当前节点的邻接节点：依次尝试压入，只要压入一个即跳出循环，也就是顺着一条路进行遍历
            for (GraphNode<String> next : graphNode.getNextNodes()){
                if (!graphNodeSet.contains(next)){
                    dfsResultList.add(next.getValue());
                    graphNodeSet.add(next);
                    //2.4 压入元素时，要先将当前节点压入，再压入邻接节点。因为如果邻接节点遍历完毕后，还需要弹出当前节点，找其他邻接节点
                    graphNodeStack.push(graphNode);
                    graphNodeStack.push(next);
                    break;
                }
            }
        }

        return dfsResultList;
    }

}
