package com.homurax.algorithms.chapter02.mege;

import com.homurax.algorithms.chapter02.SortUtil;

/**
 * 归并排序
 * 自顶向下的归并排序
 */
public class Merge {

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void sort(T[] a) {
        T[] aux = (T[]) new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        sort(a, aux, l, mid);
        sort(a, aux, mid + 1, r);
        if (SortUtil.less(a[mid + 1], a[mid])) {
            merge(a, aux, l, mid, r);
        }
    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, int l, int mid, int r) {
        int i = l, j = mid + 1;
        System.arraycopy(a, l, aux, l, r - l + 1);
        for (int w = l; w <= r; w++) {
            if (i > mid) { // 左边用尽 取右边
                a[w] = aux[j++];
            } else if (j > r) { // 右边用尽 取左边
                a[w] = aux[i++];
            } else if (SortUtil.less(aux[j], aux[i])) { // 取更小的一边
                a[w] = aux[j++];
            } else {
                a[w] = aux[i++];
            }
        }
    }

}
