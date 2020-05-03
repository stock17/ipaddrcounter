package ru.yurima.ipaddrcounter;

import junit.framework.TestCase;
import org.junit.Test;
import ru.yurima.ipaddrcounter.count.HyperLogLog;

public class HyperLogLogTest extends TestCase {

    @Test
    public void testAdd() {
        HyperLogLog logLog = new HyperLogLog();

        logLog.add(0xFFFFFFFFFFFFFFFFL);
        assertEquals(1, logLog.getRegisterValue(2047));

        logLog.add(0b100000001);
        assertEquals(45, logLog.getRegisterValue(0));

        logLog.add(1);
        assertEquals(53, logLog.getRegisterValue(0));

        logLog.add(0xFFE000000000000FL);
        assertEquals(50, logLog.getRegisterValue(2047));
    }
}