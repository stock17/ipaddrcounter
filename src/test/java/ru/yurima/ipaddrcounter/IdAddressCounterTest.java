package ru.yurima.ipaddrcounter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yurima.ipaddrcounter.source.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IdAddressCounterTest {

    private final Path file = Paths.get("./ipaddrcountertest");
    private final int size = 1000000;
    private final float accuracy = 0.05f;

    @Before
    public void init() {
        FileHelper.createFile(file, size);
    }

    @Test
    public void estimate() throws IOException {
        IpAddressCounter ipAddressCounter = new IpAddressCounter(file.toString());
        double result = ipAddressCounter.estimate();
        System.out.printf("Expected: %s\n", size);
        System.out.printf("Result: %s", Math.round(result));
        assert (result  > size * (1-accuracy)
                && result < size * (1 + accuracy));

    }

    @After
    public void destroy() {
        FileHelper.deleteFile(file);
    }
}