package com.homurax.algorithms.chapter01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTests {

    private static final int[] arr = new int[]{1, 2, 3, 4, 5};

    @Test
    @DisplayName("arr[2] = 3")
    void rankTest() {
        assertEquals(2, BinarySearch.rank(3, arr));
    }

    @ParameterizedTest(name = "arr{0} = {1}")
    @CsvSource({
            "1, 0",
            "2, 1",
            "3, 2"
    })
    void indexOf(int key, int index) {
        assertEquals(index, BinarySearch.rank(key, arr), () -> "index of " + key + " in " + Arrays.toString(arr) + " = " + index);
    }
}
