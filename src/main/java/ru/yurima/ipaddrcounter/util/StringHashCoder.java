package ru.yurima.ipaddrcounter.util;

import java.net.Inet4Address;

public class StringHashCoder implements HashCoder<String> {

    @Override
    public int hash(String obj) {
        return obj.hashCode();
    }
}
