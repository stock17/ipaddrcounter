package ru.yurima.ipaddrcounter;

import junit.framework.TestCase;
import org.junit.Test;

public class HyperLogLogTest extends TestCase {

    @Test
    public void testAdd() {
        HyperLogLog logLog = new HyperLogLog();

        logLog.add(1);
        assertEquals(1, logLog.getRegisterValue(0));

        logLog.add(0b100000001);
        assertEquals(9, logLog.getRegisterValue(0));

        logLog.add(0xF0001111);
        assertEquals(13, logLog.getRegisterValue(15));
    }
}