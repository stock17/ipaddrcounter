package ru.yurima.ipaddrcounter;


import org.junit.Test;
import ru.yurima.ipaddrcounter.count.HyperLogLog;
import ru.yurima.ipaddrcounter.util.IntegerHashCoder;

import static org.junit.Assert.assertEquals;

public class HyperLogLogTest {

    @Test
    public void testAdd() {
        HyperLogLog logLog = new HyperLogLog();

        int value = 0b10000000;
        logLog.add(value);
        int h = new IntegerHashCoder().hash(value);
        int registerNumber = h >>> (Integer.SIZE - 11);
        int mask = Integer.MIN_VALUE >> (11 - 1);
        int body = h & ~mask;
        int mostLeftBit = Integer.numberOfLeadingZeros(body) - 11 + 1;

        assertEquals(mostLeftBit, logLog.getRegisterValue(registerNumber));
    }
}