package com.assignment;

import java.util.*;

public class Benchmark {
    public static void main(String[] args) throws Exception {
        List<Metric> results = new ArrayList<>();
        Random rand = new Random();

        int[] sizes = {10, 100, 1000};

        for (int n : sizes) {
            int[] arr = rand.ints(n, 0, 10000).toArray();

            results.add(MergeSort.sortWithMetrics(arr.clone()));
            results.add(QuickSort.sortWithMetrics(arr.clone()));
            results.add(DeterministicSelect.selectWithMetrics(arr.clone(), n/2));

            double[][] pts = new double[n][2];
            for (int i = 0; i < n; i++) {
                pts[i][0] = rand.nextDouble() * 1000;
                pts[i][1] = rand.nextDouble() * 1000;
            }
            results.add(ClosestPair.closestPairWithMetrics(pts));
        }

        CSVWriter.save("metrics.csv", results);
    }
}
