package com.homurax.algorithms.chapter02.elementary;

import com.homurax.algorithms.chapter02.SortUtil;

/**
 * 选择排序
 */
public class Selection {

    public static <T extends Comparable<T>> void sort(T[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (SortUtil.less(a[j], a[minIndex])) {
                    minIndex = j;
                }
            }
            SortUtil.swap(a, i, minIndex);
        }
    }

}