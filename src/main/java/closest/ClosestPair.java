package com.assignment;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    private static Metric metrics;

    public static double closestPair(double[][] points) {
        double[][] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p[0]));
        double[][] sortedByY = sortedByX.clone();
        return closestRecursive(sortedByX, sortedByY, 0, points.length - 1, 1, false);
    }

    public static Metric closestPairWithMetrics(double[][] points) {
        metrics = new Metric();
        metrics.algorithm = "ClosestPair";
        metrics.size = points.length;

        long start = System.nanoTime();
        closestPair(points);
        metrics.timeNs = System.nanoTime() - start;

        return metrics;
    }

    private static double closestRecursive(double[][] ptsX, double[][] ptsY, int left, int right, int depth, boolean collect) {
        if (right - left <= 3) {
            return bruteForce(ptsX, left, right, collect);
        }
        if (collect) metrics.recursionDepth = Math.max(metrics.recursionDepth, depth);

        int mid = (left + right) / 2;
        double midX = ptsX[mid][0];

        double dLeft = closestRecursive(ptsX, ptsY, left, mid, depth + 1, collect);
        double dRight = closestRecursive(ptsX, ptsY, mid + 1, right, depth + 1, collect);
        return Math.min(dLeft, dRight);
    }

    private static double bruteForce(double[][] pts, int left, int right, boolean collect) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                if (collect) metrics.comparisons++;
                min = Math.min(min, distance(pts[i], pts[j]));
            }
        }
        return min;
    }

    private static double distance(double[] a, double[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}
