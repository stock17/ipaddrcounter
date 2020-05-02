package ru.yurima.ipaddrcounter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yurima.ipaddrcounter.source.FileHelper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.StringJoiner;

public class IdAddressCounterTest {

    private final Path file = Paths.get("./ipaddrcountertest");

    @Before
    public void init() throws IOException {
        new FileHelper().createFile(file);
    }

    @Test
    public void estimate() throws IOException {

        IpAddressCounter ipAddressCounter = new IpAddressCounter(file.toString());
        double result = ipAddressCounter.estimate();
        System.out.println(Math.round(result));
    }

    @After
    public void destroy() throws IOException {
        new FileHelper().delete(file);
    }
}