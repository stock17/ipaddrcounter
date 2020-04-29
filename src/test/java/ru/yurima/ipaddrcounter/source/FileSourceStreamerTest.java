package ru.yurima.ipaddrcounter.source;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class FileSourceStreamerTest {

    Path file = Paths.get("./streamtest");
    private final String content = "255.255.255.255\n" +
                                   "123.123.123.123\n" +
                                   "0.0.0.0";


    @Before
    public void init() throws IOException {
        Files.createFile(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file)));
        writer.write(content);
        writer.newLine();
        writer.close();
    }

    @Test
    public void stream() throws IOException {

        FileSourceStreamer streamer = new FileSourceStreamer(file.toString());

        Supplier<Stream<String>> supplier = () -> streamer.stream();

        long count = supplier.get().count();
        assertEquals(3, count);

        Optional<String> expected = supplier.get().findFirst();
        assertEquals(expected.get(), "255.255.255.255");

        expected = supplier.get().findAny();
        assert(content.contains(expected.get()));
    }

    @After
    public void destroy() throws IOException {
        Files.deleteIfExists(file);
    }
}