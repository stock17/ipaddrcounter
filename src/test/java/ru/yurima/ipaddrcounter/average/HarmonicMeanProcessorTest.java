package ru.yurima.ipaddrcounter.average;

import junit.framework.TestCase;

public class HarmonicMeanProcessorTest extends TestCase {

    public void testProcess() {
        MeanProcessor processor = new HarmonicMeanProcessor();

        double result = processor.process(2,4,6);
        System.out.println(result);
        double expected = 3 / (1.0/2 + 1.0/4+ 1.0/6);
        System.out.println(expected);
        assertEquals(expected, result, 0.00001);

        result = processor.process(10,25,100,250,500);
        System.out.println(result);
        expected = 5 / (1.0/10 + 1.0/25 + 1.0/100 +1.0/250 +1.0/500);
        System.out.println(expected);
        assertEquals(expected, result, 0.00001);
    }
}