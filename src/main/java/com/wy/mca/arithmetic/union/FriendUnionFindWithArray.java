package com.wy.mca.arithmetic.union;

import java.util.Stack;

/**
 * 通过数组实现并查集、用于压缩空间、提高效率
 */
public class FriendUnionFindWithArray {

    /**
     * 节点和父节点关系 parent[i] = k，代表i的父节点是k
     */
    private int[] parents;

    /**
     * sizes[i]：只有i是头部节点时，size[i]才有意义，表示该头部节点下元素数量
     */
    private int[] sizes;

    /**
     * 集合大小
     */
    private int sets;

    public FriendUnionFindWithArray(int N) {
        parents = new int[N];
        sizes = new int[N];
        for (int i = 0; i < N; i++) {
            //1.1 节点i一开始指向自己，表示i的父节点是自己
            parents[i] = i;
            //1.2 i是代表节点，i所在集合大小一开始是1
            sizes[i] = 1;
        }
        //1.3 一开始朋友圈数量是N个
        sets = N;
    }

    public int findHead(int i) {
        Stack<Integer> stack = new Stack<>();
        if (i != parents[i]) {
            stack.push(i);
            i = parents[i];
        }

        while (!stack.isEmpty()) {
            parents[stack.pop()] = i;
        }
        return i;
    }

    public void union(int i, int j) {
        int headI = findHead(i);
        int headJ = findHead(j);
        if (headI != headJ) {
            if (sizes[headI] >= sizes[headJ]) {
                sizes[headI] += sizes[headJ];
                parents[headJ] = headI;
                sizes[headJ] = 0;
            } else {
                sizes[headJ] += sizes[headI];
                parents[headI] = headJ;
                sizes[headI] = 0;
            }
            sets--;
        }
    }

    public int getCircles() {
        return sets;
    }
}
