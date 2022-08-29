package com.homurax.algorithms.chapter02.mege;

/**
 * 自底向上的归并排序
 */
public class MergeBU {

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void sort(T[] a) {
        int len = a.length;
        T[] aux = (T[]) new Comparable[a.length];
        for (int size = 1; size < len; size *= 2) {
            for (int idx = 0; idx < len - size; idx += size * 2) {
                Merge.merge(a, aux, idx, idx + size - 1, Math.min(idx + size + size - 1, len - 1));
            }
        }
    }

}
