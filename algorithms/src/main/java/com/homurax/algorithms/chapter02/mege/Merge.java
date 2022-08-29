package com.homurax.algorithms.chapter02.mege;

import com.homurax.algorithms.chapter02.SortUtil;

/**
 * 归并排序
 */
public class Merge {

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void sort(T[] a) {
        T[] aux = (T[]) new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + ((hi - lo) >> 1);
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (SortUtil.less(a[mid + 1], a[mid])) {
            merge(a, aux, lo, mid, hi);
        }
    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++]; // 左边用尽 取右边
            else if (j > hi) a[k] = aux[i++]; // 右边用尽 取左边
            else if (SortUtil.less(aux[j], aux[i])) a[k] = aux[j++]; // 取更小的一边
            else a[k] = aux[i++];
        }
    }

}
