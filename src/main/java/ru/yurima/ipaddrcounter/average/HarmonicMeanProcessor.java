package ru.yurima.ipaddrcounter.average;

import java.util.Arrays;

public class HarmonicMeanProcessor implements MeanProcessor{
    @Override
    public double process(int... array) {
        if (array == null || array.length == 0) return  (double) 0;
        double result = Arrays.stream(array).mapToDouble(n -> Math.pow(2, -n)).sum();
        return 1.0/result;
    }
}
