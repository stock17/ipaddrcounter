package ru.yurima.ipaddrcounter.count;

public interface DistinctCounter {
    void add(long hashcode);
    double count();
}
