package ru.yurima.ipaddrcounter.average;

import java.util.Arrays;

public class HarmonicMeanProcessor implements MeanProcessor{
    @Override
    public double process(int... array) {
        if (array == null || array.length == 0) return  (double) 0;

//        double result = Arrays.stream(array).mapToDouble(n -> (double)n).map(n -> Math.pow(1/2, n)).sum();
        double sum = 0;
        for (int i : array) {
            double d = (double) i;
            double current = Math.pow(2, -i);
            sum += current;
        }
        return (double) 1.0/sum;
    }
}
