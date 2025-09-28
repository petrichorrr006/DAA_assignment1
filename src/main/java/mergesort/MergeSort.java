package com.assignment;

public class MergeSort {
    private static final int CUTOFF = 16;
    private static Metric metrics;

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1, 1, false);
    }

    public static Metric sortWithMetrics(int[] arr) {
        metrics = new Metric();
        metrics.algorithm = "MergeSort";
        metrics.size = arr.length;

        long start = System.nanoTime();
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1, 1, true);
        metrics.timeNs = System.nanoTime() - start;

        return metrics;
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right, int depth, boolean collect) {
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right, collect);
            return;
        }

        if (collect) metrics.recursionDepth = Math.max(metrics.recursionDepth, depth);

        int mid = (left + right) / 2;
        mergeSort(arr, buffer, left, mid, depth + 1, collect);
        mergeSort(arr, buffer, mid + 1, right, depth + 1, collect);

        if (collect) metrics.comparisons++;
        if (arr[mid] <= arr[mid + 1]) return;

        merge(arr, buffer, left, mid, right, collect);
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, boolean collect) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (collect) metrics.comparisons++;
            if (arr[i] <= arr[j]) buffer[k++] = arr[i++];
            else {
                buffer[k++] = arr[j++];
                if (collect) metrics.swaps++;
            }
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (i = left; i <= right; i++) arr[i] = buffer[i];
    }

    private static void insertionSort(int[] arr, int left, int right, boolean collect) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                if (collect) {
                    metrics.comparisons++;
                    metrics.swaps++;
                }
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
