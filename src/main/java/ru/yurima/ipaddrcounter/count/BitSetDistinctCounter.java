package ru.yurima.ipaddrcounter.count;

import java.util.BitSet;

public class BitSetDistinctCounter implements DistinctCounter{

    private BitSet pos = new BitSet();
    private BitSet neg = new BitSet();

    @Override
    public void add(int value) {
        if (value < 0) {
            neg.set(~value);
        } else {
            pos.set(value);
        }

    }

    @Override
    public double count() {
        return pos.cardinality() + neg.cardinality();
    }
}
