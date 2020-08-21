package com.homurax.algorithms.chapter01;

/**
 * union-find 算法
 *
 * quick-union
 *
 * 每个触点所对应的 id[] 元素都是同一个分量中的另一个触点的名称
 * id[] 数组用父链接的形式表示了一片森林
 *
 * union()只需要修改一个链接
 */
public class QuickUnionUF {

    private final int[] id;
    private int count;

    public QuickUnionUF(int N) {
        this.count = N;
        this.id = new int[N];
        for (int i = 0; i < N; i++) {
            this.id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        id[pRoot] = qRoot;
        count--;
    }

}
