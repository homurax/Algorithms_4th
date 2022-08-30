package com.homurax.algorithms.chapter01;

/**
 * union-find 算法
 *
 * 使用路径压缩的加权 quick-union
 *
 * 将在路径上遇到的所有节点都直接链接到根节点
 */
public class WeightedQuickUnionPathCompressionUF {

    private final int[] id;
    private final int[] size;
    private int count;

    public WeightedQuickUnionPathCompressionUF(int n) {
        this.count = n;
        this.id = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            this.id[i] = i;
            this.size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        int root = p;
        // 找到根节点
        while (root != id[root]) {
            root = id[root];
        }
        while (p != id[p]) {
            int temp = p;
            p = id[p];
            id[temp] = root;
        }
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
        count--;
    }
}
