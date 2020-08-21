package com.homurax.algorithms.chapter01;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UFTests {

    private static int N;
    private static List<Integer> data;

    @BeforeAll
    static void beforeAll() {
        Path file = Paths.get("data\\chapter01\\mediumUF.txt");
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            N = Integer.parseInt(reader.readLine());
            data = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] pq = line.split("\\s+");
                data.add(Integer.parseInt(pq[0]));
                data.add(Integer.parseInt(pq[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("UF")
    void ufTest() {
        System.out.println("N: " + N);
        System.out.println("pq group: " + data.size() / 2);

        QuickFindUF quickFindUF = new QuickFindUF(N);
        QuickUnionUF quickUnionUF = new QuickUnionUF(N);
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(N);
        WeightedQuickUnionPathCompressionUF quickUnionPathCompressionUF = new WeightedQuickUnionPathCompressionUF(N);

        for (int i = 0; i < data.size(); i += 2) {
            Integer p = data.get(i);
            Integer q = data.get(i + 1);
            quickFindUF.union(p, q);
            quickUnionUF.union(p, q);
            weightedQuickUnionUF.union(p, q);
            quickUnionPathCompressionUF.union(p, q);
        }

        int count1 = quickFindUF.count();
        int count2 = quickUnionUF.count();
        int count3 = weightedQuickUnionUF.count();
        int count4 = quickUnionPathCompressionUF.count();
        assertTrue(count1 == count2 && count3 == count4 && count1 == count3);
        System.out.println("count: " + count1);

        for (int i = 0; i < 10; i++) {

            int p = ThreadLocalRandom.current().nextInt(N);
            int q = ThreadLocalRandom.current().nextInt(N);
            boolean connected1 = quickFindUF.connected(p, q);
            boolean connected2 = quickUnionUF.connected(p, q);
            boolean connected3 = weightedQuickUnionUF.connected(p, q);
            boolean connected4 = quickUnionPathCompressionUF.connected(p, q);
            assertTrue(connected1 == connected2 && connected3 == connected4 && connected1 == connected3);
            System.out.println("p: " + p + ", q: " + q + ", connected: " + connected1);
        }
    }


}
