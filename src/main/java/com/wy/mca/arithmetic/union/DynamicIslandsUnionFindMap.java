package com.wy.mca.arithmetic.union;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Description 动态岛屿优化
 * 1、问题描述：由前面可知，动态岛屿的时间复杂度是：O(M * N) + O(k)，
 * 2、如果M和N特别大，而k又特别小，如果沿用前面的数组方式，那么时间复杂度就会很高，可以进行优化
 * @Author wangyong01
 * @Date 2022/8/5 5:11 下午
 * @Version 1.0
 */
public class DynamicIslandsUnionFindMap {

    /**
     * 当前节点和父节点的关系
     * key：(i + "-" + j)，当前节点的坐标
     * value：(i + "-" + j)，父节点坐标
     */
    private Map<String, String> nodeParentPosMap = Maps.newHashMap();

    /**
     * 头部节点下挂载的节点数量
     * key：(i + "-" + j)，头部节点的坐标
     */
    private Map<String, Integer> headNodeChildrenMap = Maps.newHashMap();

    /**
     * 岛屿数量
     */
    private int sets;

    private String findFather(String node) {
        if (!StrUtil.equals(node, nodeParentPosMap.get(node))) {
            node = nodeParentPosMap.get(node);
        }
        return node;
    }

    public void union(String curNode, String nodeAside) {
        //1.1 当前节点curNode旁边的节点nodeAside不为空，说明可以合并
        if (headNodeChildrenMap.containsKey(nodeAside)) {
            String curHead = findFather(curNode);
            String asideHead = findFather(nodeAside);
            //1.2 当前节点 和 旁边的节点不在一个集合，说明可以合并
            if (!StrUtil.equals(curHead, asideHead)) {
                Integer curHeadSize = headNodeChildrenMap.get(curHead);
                Integer asideHeadSize = headNodeChildrenMap.get(asideHead);

                if (curHeadSize > asideHeadSize) {
                    nodeParentPosMap.put(asideHead, curHead);
                    headNodeChildrenMap.put(curHead, curHeadSize + asideHeadSize);
                } else {
                    nodeParentPosMap.put(curHead, asideHead);
                    headNodeChildrenMap.put(asideHead, curHeadSize + asideHeadSize);
                }

                sets--;
            }
        }
    }

    /**
     * 空降一个元素
     * @param i
     * @param j
     * @return
     */
    public int land(int i, int j) {
        String nodePos = i + "-" + j;

        //1 当前位置(i,j)没有空降过，可以空降
        if (!headNodeChildrenMap.containsKey(nodePos)) {
            //1.1 当前节点 指向 自己
            nodeParentPosMap.put(nodePos, nodePos);
            //1.2 当前节点为头节点下的节点数量
            headNodeChildrenMap.put(nodePos, 1);
            sets++;

            union(nodePos, (i - 1) + "-" + j);
            union(nodePos, (i + 1) + "-" + j);
            union(nodePos, i + "-" + (j - 1));
            union(nodePos, i + "-" + (j + 1));
        }

        return sets;
    }

}
