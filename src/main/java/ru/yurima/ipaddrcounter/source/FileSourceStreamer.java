package ru.yurima.ipaddrcounter.source;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileSourceStreamer {
    private String filename;
    public FileSourceStreamer(String filename) {
        this.filename = filename;
    }

    public Stream<String> stream() {
        Path file = Paths.get(filename);
        if (Files.exists(file)) {
            try {
                return Files.lines(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}
