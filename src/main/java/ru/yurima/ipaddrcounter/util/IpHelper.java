package ru.yurima.ipaddrcounter.util;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class IpHelper {
    public static int toInteger(String s) {
        int[] array = new int[4];
        String[] parts = s.split("\\.");
        for (int i = 0; i < 4; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }

        int ip = 0;
        for (int i = 0; i < 4; i++) {
            ip <<= Byte.SIZE;
            ip += array[i];
        }

        return ip;
    }
}
