package ru.yurima.ipaddrcounter.source;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.StringJoiner;

public class FileHelper {
    public static boolean createFile(Path file, int size) {
        try {
            deleteFile(file);
            Files.createFile(file);
            Random random = new Random();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file)));
            for (int i = 0; i < size; i++) {
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

    public static boolean createUniqueFile(Path file, int size) {
        deleteFile(file);
        try {
            Files.createFile(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file)));
            for (int i = 0; i < 256; i++) {
                for (int j = 0; j < 256; j++) {
                    for (int k = 0; k < 256; k++) {
                        for (int l = 0; l < 256; l++) {
                            StringJoiner joiner = new StringJoiner(".");
                            joiner.add("" +  i)
                                    .add("" + j)
                                    .add("" + k)
                                    .add("" + l);
                            writer.write(joiner.toString() + "\n");
                            if (--size < 1) {
                                writer.close();
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteFile(Path file){
        try {
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
