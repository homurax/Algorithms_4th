package com.homurax.algorithms.chapter01;

/**
 * union-find 算法
 *
 * quick-find
 *
 * 当且仅当 id[p] 等于 id[q] 时 p 和 q 是连通的
 * 在同一个连通分量中的所有触点在 id[] 中的值必须全部相同
 *
 * find() 操作的速度较快
 * 对于每一对输入 union() 都需要扫描整个 id[] 数组
 */
public class UF1 {

    private final int[] id; // 分量id
    private int count; // 分量数量

    public UF1(int N) {
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
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count--;
    }

}
