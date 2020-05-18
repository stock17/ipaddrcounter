package ru.yurima.ipaddrcounter.count;

import java.util.HashSet;
import java.util.Set;

public class NaiveCounter implements DistinctCounter {
    Set<Integer> set = new HashSet<>();

    @Override
    public void add(int value) {
        set.add(value);
    }

    @Override
    public double count() {
        return set.size();
    }
}
