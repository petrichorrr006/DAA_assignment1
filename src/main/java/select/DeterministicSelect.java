package com.assignment;

import java.util.Arrays;

public class DeterministicSelect {
    private static Metric metrics;

    public static int select(int[] arr, int k) {
        return selectInternal(arr.clone(), 0, arr.length - 1, k, 1, false);
    }

    public static Metric selectWithMetrics(int[] arr, int k) {
        metrics = new Metric();
        metrics.algorithm = "DeterministicSelect";
        metrics.size = arr.length;

        long start = System.nanoTime();
        selectInternal(arr, 0, arr.length - 1, k, 1, true);
        metrics.timeNs = System.nanoTime() - start;

        return metrics;
    }

    private static int selectInternal(int[] arr, int left, int right, int k, int depth, boolean collect) {
        if (left == right) return arr[left];

        if (collect) metrics.recursionDepth = Math.max(metrics.recursionDepth, depth);

        int pivot = medianOfMedians(arr, left, right, collect);
        int pivotIndex = partition(arr, left, right, pivot, collect);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return selectInternal(arr, left, pivotIndex - 1, k, depth + 1, collect);
        } else {
            return selectInternal(arr, pivotIndex + 1, right, k, depth + 1, collect);
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right, boolean collect) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }
        int numMedians = (int) Math.ceil(n / 5.0);
        int[] medians = new int[numMedians];
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }
        return selectInternal(medians, 0, numMedians - 1, numMedians / 2, 1, collect);
    }

    private static int partition(int[] arr, int left, int right, int pivot, boolean collect) {
        int i = left;
        for (int j = left; j <= right; j++) {
            if (collect) metrics.comparisons++;
            if (arr[j] < pivot) {
                swap(arr, i, j, collect);
                i++;
            }
        }
        return i;
    }

    private static void swap(int[] arr, int i, int j, boolean collect) {
        if (collect) metrics.swaps++;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
