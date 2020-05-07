package ru.yurima.ipaddrcounter.util;

import java.net.Inet4Address;

public class StringHashCoder implements HashCoder<String> {
    @Override
    public long getHashCode64(String obj) {
        long h = 1125899906842597L;
        for (byte v : obj.getBytes()) {
            h = 31 * h + (v & 0xff);
        }
        return h;
    }

    @Override
    public int getHashCode32(String obj) {
        return obj.hashCode();
    }
}
