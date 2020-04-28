package ru.yurima.ipaddrcounter.average;

import java.util.Arrays;

public class GeometryMeanProcessor extends MeanProcessor {
    public double process(int... array) {
        if (array == null || array.length == 0) return  (double) 0;

        long product = Arrays.stream(array).reduce(1, (a, b) -> a * b);
        return Math.pow (product, 1/array.length);
    }
}
