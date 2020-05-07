package ru.yurima.ipaddrcounter;


import org.junit.Test;
import ru.yurima.ipaddrcounter.count.HyperLogLog;

import static org.junit.Assert.assertEquals;

public class HyperLogLogTest {

    @Test
    public void testAdd() {
        HyperLogLog logLog = new HyperLogLog();

        logLog.add(0b100000000);
        assertEquals(13, logLog.getRegisterValue(0));

        logLog.add(1);
        assertEquals(21, logLog.getRegisterValue(0));


        logLog.add(0xFFE01111);
        assertEquals(9, logLog.getRegisterValue(2047));
    }
}