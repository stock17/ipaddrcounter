package ru.yurima.ipaddrcounter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Files.createFile(file);
        Random random = new Random();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file)));
        for (int i = 0; i < 100000; i++) {

            StringJoiner joiner = new StringJoiner(".");
            for (int j = 0; j < 4; j++) {
                joiner.add("" + random.nextInt(256));
            }
            writer.write(joiner.toString() + "\n");
        }
        writer.close();
    }

    @Test
    public void estimate() throws IOException {

        IpAddressCounter ipAddressCounter = new IpAddressCounter(file.toString());
        double result = ipAddressCounter.estimate();
        System.out.println(result);
    }

    @After
    public void destroy() throws IOException {
        Files.deleteIfExists(file);
    }
}