package ru.yurima.ipaddrcounter.util;

public class IntegerHashCoder implements HashCoder<Integer>{

    @Override
    public int hash(final Integer obj) {
        int h = obj;
        h ^= h >>> 16;
        h *= 0x85ebca6b;
        h ^= h >>> 13;
        h *= 0xc2b2ae35;
        h ^= h >>> 16;
        return h;
    }
}
