package ru.yurima.ipaddrcounter.source;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.StringJoiner;

public class FileHelper {
    public boolean createFile(Path file){
        try {
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
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Path file) {
        try {
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}