package com.wy.mca.structure.tree.multi;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author wangyong01
 * @Date 2022/4/15 3:56 下午
 * @Version 1.0
 */
@Data
public class MultiTreeNode {

    /**
     * 节点值
     */
    private int no;

    /**
     * 孩子节点
     */
    private List<MultiTreeNode> children;

    public MultiTreeNode(){

    }

    public MultiTreeNode(int no) {
        this.no = no;
    }

    public MultiTreeNode(int no, List<MultiTreeNode> children) {
        this.no = no;
        this.children = children;
    }

}
