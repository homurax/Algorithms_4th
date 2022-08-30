package com.homurax.algorithms.chapter01;

/**
 * union-find 算法
 *
 * 加权 quick-union
 *
 * 记录每一棵树的大小并总是将较小的树连接到较大的树上
 */
public class WeightedQuickUnionUF {

    private final int[] id; // 父链接数组 由触点索引
    private final int[] sz; // 由触点索引的各个根节点所对应的分量的大小
    private int count;

    public WeightedQuickUnionUF(int n) {
        this.count = n;
        this.id = new int[n];
        this.sz = new int[n];
        for (int i = 0; i < n; i++) {
            this.id[i] = i;
            this.sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        // 将小树的根节点连接到大树的根节点
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
}
