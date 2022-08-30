package com.homurax.algorithms.chapter01;

public class BinarySearch {

    public static int rank(int key, int[] a) {
        int l = 0;
        int r = a.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (key < a[mid]) {
                r = mid - 1;
            } else if (key > a[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
