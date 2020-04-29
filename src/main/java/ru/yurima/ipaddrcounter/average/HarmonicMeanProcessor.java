package ru.yurima.ipaddrcounter.average;

import java.util.Arrays;

public class HarmonicMeanProcessor implements MeanProcessor{
    @Override
    public double process(int... array) {
        if (array == null || array.length == 0) return  (double) 0;

        double denominator = Arrays.stream(array).mapToDouble(n -> (double)n).map(n -> n = 1/n).sum();
        return (double) array.length / denominator;
    }
}
