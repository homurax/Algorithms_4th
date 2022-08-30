package com.homurax.algorithms.chapter02;

import com.homurax.algorithms.chapter02.elementary.Insertion;
import com.homurax.algorithms.chapter02.elementary.Selection;
import com.homurax.algorithms.chapter02.elementary.Shell;
import com.homurax.algorithms.chapter02.mege.Merge;
import com.homurax.algorithms.chapter02.mege.MergeBU;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ElementarySortsTests {

    private String[] data;

    @BeforeEach
    void beforeEach() {
        Path file = Paths.get("data\\chapter02\\tiny.txt");
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = reader.readLine();
            data = line.split("\\s+");
            //SortUtil.show(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Selection")
    void selectionTest() {
        Selection.sort(data);
        //SortUtil.show(data);
        assertTrue(SortUtil.isSorted(data));
    }

    @Test
    @DisplayName("Insertion")
    void insertionTest() {
        Insertion.sort(data);
        //SortUtil.show(data);
        assertTrue(SortUtil.isSorted(data));
    }

    @Test
    @DisplayName("Shell")
    void shellTest() {
        Shell.sort(data);
        //SortUtil.show(data);
        assertTrue(SortUtil.isSorted(data));
    }

    @Test
    @DisplayName("Merge - 自顶向下")
    void mergeTest() {
        Merge.sort(data);
        //SortUtil.show(data);
        assertTrue(SortUtil.isSorted(data));
    }

    @Test
    @DisplayName("MergeBU - 自底向上的归并排序")
    void mergeBUTest() {
        MergeBU.sort(data);
        //SortUtil.show(data);
        assertTrue(SortUtil.isSorted(data));
    }

}
