package ru.yurima.ipaddrcounter.count;

import java.util.HashSet;
import java.util.Set;

public class NaiveCounter implements DistinctCounter {
    Set<Long> set = new HashSet<>();

    @Override
    public void add(long hashcode) {
        set.add(hashcode);
    }

    @Override
    public double count() {
        return set.size();
    }
}
