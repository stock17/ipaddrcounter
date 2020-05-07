package ru.yurima.ipaddrcounter.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class IpHelperTest {

    @Test
    public void toInteger() {
        String s = "0.0.0.0";
        assertEquals(0, IpHelper.toInteger(s));

        s = "0.0.0.1";
        assertEquals(1, IpHelper.toInteger(s));

        s = "255.255.255.255";
        assertEquals(0xFFFFFFFF, IpHelper.toInteger(s));
    }
}