package ru.yurima.ipaddrcounter.count;

import java.util.BitSet;

public class BitSetDistinctCounter implements DistinctCounter{

    private BitSet pos = new BitSet();
    private BitSet neg = new BitSet();

    @Override
    public void add(int hashcode) {
        if (hashcode < 0) {
            neg.set(~hashcode);
        } else {
            pos.set(hashcode);
        }

    }

    @Override
    public double count() {
        return pos.cardinality() + neg.cardinality();
    }
}
