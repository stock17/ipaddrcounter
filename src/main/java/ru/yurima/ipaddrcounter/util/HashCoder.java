package ru.yurima.ipaddrcounter.util;

public interface HashCoder<T> {
    public int hash(T obj);
}
