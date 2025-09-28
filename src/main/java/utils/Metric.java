package com.assignment;

public class Metric {
    public String algorithm;
    public int size;
    public long comparisons = 0;
    public long swaps = 0;
    public int recursionDepth = 0;
    public long timeNs = 0;

    public String toCSV() {
        return algorithm + "," + size + "," + comparisons + "," + swaps + "," + recursionDepth + "," + timeNs;
    }
}
