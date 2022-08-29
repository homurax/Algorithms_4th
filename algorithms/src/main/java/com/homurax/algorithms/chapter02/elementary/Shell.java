package com.homurax.algorithms.chapter02.elementary;

import com.homurax.algorithms.chapter02.SortUtil;

/**
 * 希尔排序
 */
public class Shell {

    public static <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;
        int step = 1;
        while (step < N / 3) { // 1, 4, 13, 40, 121, 364, 1093, ...
            step = 3 * step + 1;
        }
        while (step >= 1) {
            for (int i = step; i < N; i++) {
                for (int j = i; j >= step && SortUtil.less(a[j], a[j - step]); j -= step) {
                    SortUtil.swap(a, j, j - step);
                }
            }
            step = step / 3;
        }
    }

}
