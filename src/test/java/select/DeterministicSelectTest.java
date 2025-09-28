package com.assignment;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testSelectMiddle() {
        int[] arr = {7, 2, 9, 4, 3, 8};
        int val = DeterministicSelect.select(arr.clone(), 3);
        Arrays.sort(arr);
        assertEquals(arr[3], val);
    }

    @Test
    void testSelectMin() {
        int[] arr = {9, 1, 5, 7};
        int val = DeterministicSelect.select(arr.clone(), 0);
        Arrays.sort(arr);
        assertEquals(arr[0], val);
    }

    @Test
    void testSelectMax() {
        int[] arr = {10, 20, 30, 40, 50};
        int val = DeterministicSelect.select(arr.clone(), arr.length - 1);
        Arrays.sort(arr);
        assertEquals(arr[arr.length - 1], val);
    }

    @Test
    void testCompareWithSort() {
        int[] arr = {15, 3, 8, 22, 7, 9, 13, 1, 5};
        for (int k = 0; k < arr.length; k++) {
            int val = DeterministicSelect.select(arr.clone(), k);
            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            assertEquals(sorted[k], val, "Mismatch at k=" + k);
        }
    }
}
