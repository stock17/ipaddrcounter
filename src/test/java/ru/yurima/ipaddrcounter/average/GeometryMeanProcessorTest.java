package ru.yurima.ipaddrcounter.average;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeometryMeanProcessorTest {

    @Test
    public void process() {
        MeanProcessor processor = new GeometryMeanProcessor();

        double result = processor.process(new int[]{3, 8, 9});
        assertEquals(result, Math.pow(3*8*9, 1/3), 0.00001);

        result = processor.process(6, 8, 10, 12, 14);
        assertEquals(result, Math.pow(6*8*10*12*14, 1/5), 0.00001);

        result = processor.process(new int[]{1});
        assertEquals(result, 1, 0.00001);

        result = processor.process(new int[]{0});
        assertEquals(result, 0, 0.00001);

        result = processor.process(new int[]{});
        assertEquals(result, 0, 0.00001);

        result = processor.process(null);
        assertEquals(result, 0, 0.00001);
    }
}