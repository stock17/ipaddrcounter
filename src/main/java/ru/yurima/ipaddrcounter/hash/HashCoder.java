package ru.yurima.ipaddrcounter.hash;

public interface HashCoder<T> {
    public long getHashCode64(T obj);
    public int getHashCode32(T obj);
}
