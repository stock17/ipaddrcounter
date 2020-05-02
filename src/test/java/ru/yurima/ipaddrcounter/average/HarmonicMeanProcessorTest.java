package ru.yurima.ipaddrcounter.average;

import junit.framework.TestCase;

public class HarmonicMeanProcessorTest extends TestCase {

    public void testProcess() {
        MeanProcessor processor = new HarmonicMeanProcessor();

        double result = processor.process(2,4,6);
        System.out.println(result);
        double expected = 1.0 / (Math.pow(2, -2) + Math.pow(2, -4) + Math.pow(2, -6));
        System.out.println(expected);
        assertEquals(expected, result, 0.00001);

        result = processor.process(10,25,100,250,500);
        System.out.println(result);
        expected = 1.0 / (Math.pow(2, -10) + Math.pow(2, -25) + Math.pow(2, -100) + Math.pow(2, -250) + Math.pow(2, -500));
        System.out.println(expected);
        assertEquals(expected, result, 0.00001);
    }
}