package ru.yurima.ipaddrcounter.util;

public class IntegerHashCoder implements HashCoder<Integer>{
    @Override
    public long getHashCode64(Integer obj) {
        return 0;
    }

    @Override
    public int getHashCode32(Integer obj) {
        int key = obj.intValue();
        key = ~key + (key << 15); // key = (key << 15) - key - 1;
        key = key ^ (key >>> 12);
        key = key + (key << 2);
        key = key ^ (key >>> 4);
        key = key * 2057; // key = (key + (key << 3)) + (key << 11);
        key = key ^ (key >>> 16);
        return key;
    }
}
