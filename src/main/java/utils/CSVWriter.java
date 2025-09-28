package com.assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    public static void save(String filename, List<Metric> results) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Algorithm,Size,Comparisons,Swaps,RecursionDepth,TimeNs\n");
            for (Metric m : results) {
                writer.write(m.toCSV() + "\n");
            }
        }
    }
}
