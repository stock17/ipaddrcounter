package ru.yurima.ipaddrcounter;

import ru.yurima.ipaddrcounter.count.DistinctCounter;
import ru.yurima.ipaddrcounter.count.HyperLogLog;
import ru.yurima.ipaddrcounter.source.FileSourceStreamer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IdAddressCounter {

    private String filename;

    public IdAddressCounter(String filename) {
        this.filename = filename;
    }

    public static void main(String[] args) throws IOException {
        if(args.length < 1) return;
            String filename = args[0];
        if (!Files.exists(Paths.get(filename))) {
            System.out.println("Incorrect filename");
            return;
        }

        IdAddressCounter ipCounter = new IdAddressCounter(args[0]);
        double result = ipCounter.estimate();
        System.out.println(result);
    }

    public double estimate() throws IOException {
        DistinctCounter counter = new HyperLogLog();
        FileSourceStreamer streamer = new FileSourceStreamer(filename);
        streamer.stream().forEach(s-> {
            counter.add(s.hashCode());
        });
        return counter.count();
    }
}
