package com.assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class QuickSortTest {
    @Test
    void testSortRandomArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 5, 5, 6, 9}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {9, 7, 5, 3, 1};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 3, 5, 7, 9}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testRecursionDepthBound() {
        int n = 1000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        QuickSort.sort(arr);

        int maxDepth = QuickSort.getMaxDepth();
        int expectedBound = 2 * (int)Math.floor(Math.log(n) / Math.log(2)) + 5; // ≲ 2*log2(n) + O(1)

        assertTrue(maxDepth <= expectedBound,
                "Recursion depth too high: " + maxDepth + " > " + expectedBound);
    }
