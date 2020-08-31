package com.homurax.algorithms.chapter02;

import java.util.Arrays;

/**
 * 选择排序
 */
public class Selection {

    public static <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[i])) {
                    minIndex = j;
                }
            }
            swap(a, i, minIndex);
        }
    }

    private static <T extends Comparable<T>> boolean less(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }

    private static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static <T extends Comparable<T>> void show(T[] a) {
        System.out.println(Arrays.toString(a));
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

}