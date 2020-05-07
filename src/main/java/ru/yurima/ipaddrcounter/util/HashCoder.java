package ru.yurima.ipaddrcounter.util;

public interface HashCoder<T> {
    public long getHashCode64(T obj);
    public int getHashCode32(T obj);
}
