package ru.yurima.ipaddrcounter.count;

import java.util.HashSet;
import java.util.Set;

public class NaiveCounter implements DistinctCounter {
    Set<Integer> set = new HashSet<>();

    @Override
    public void add(int hashcode) {
        set.add(hashcode);
    }

    @Override
    public double count() {
        return set.size();
    }
}
