package com.assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    void testSortRandomArray() {
        int[] arr = {5, 2, 4, 6, 1, 3};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }
}
