package ru.yurima.ipaddrcounter.util;

public final class IpHelper {

    private IpHelper(){}

    public static int toInteger(String s) {
        final String[] parts = s.split("\\.");
        int ip = 0;
        for (int i = 0; i < 4; i++) {
            ip <<= Byte.SIZE;
            ip += Integer.parseInt(parts[i]);
        }
        return ip;
    }
}
