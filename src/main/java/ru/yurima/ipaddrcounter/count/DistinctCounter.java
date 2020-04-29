package ru.yurima.ipaddrcounter.count;

public interface DistinctCounter {
    void add(int hashcode);
    double count();
}
