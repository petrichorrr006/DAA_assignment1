package com.assignment;

import java.util.Random;

public class QuickSort {
    private static final Random RAND = new Random();
    private static Metric metrics;
    
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1, 1, false);
    }
    
    public static Metric sortWithMetrics(int[] arr) {
        metrics = new Metric();
        metrics.algorithm = "QuickSort";
        metrics.size = arr.length;

        long start = System.nanoTime();
        quickSort(arr, 0, arr.length - 1, 1, true);
        metrics.timeNs = System.nanoTime() - start;

        return metrics;
    }

    public static int getMaxDepth() {
        return metrics != null ? metrics.recursionDepth : -1;
    }

    private static void quickSort(int[] arr, int left, int right, int depth, boolean collect) {
        while (left < right) {
            int pivotIndex = left + RAND.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];
            swap(arr, pivotIndex, right, collect);

            int partitionIndex = partition(arr, left, right, pivot, collect);

            if (collect) metrics.recursionDepth = Math.max(metrics.recursionDepth, depth);

            if (partitionIndex - left < right - partitionIndex) {
                quickSort(arr, left, partitionIndex - 1, depth + 1, collect);
                left = partitionIndex + 1;
            } else {
                quickSort(arr, partitionIndex + 1, right, depth + 1, collect);
                right = partitionIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int left, int right, int pivot, boolean collect) {
        int i = left;
        for (int j = left; j < right; j++) {
            if (collect) metrics.comparisons++;
            if (arr[j] <= pivot) {
                swap(arr, i, j, collect);
                i++;
            }
        }
        swap(arr, i, right, collect);
        return i;
    }

    private static void swap(int[] arr, int i, int j, boolean collect) {
        if (collect) metrics.swaps++;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
