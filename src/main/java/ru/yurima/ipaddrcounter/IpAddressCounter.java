package ru.yurima.ipaddrcounter;

import ru.yurima.ipaddrcounter.count.DistinctCounter;
import ru.yurima.ipaddrcounter.count.HyperLogLog;
import ru.yurima.ipaddrcounter.source.FileSourceStreamer;
import ru.yurima.ipaddrcounter.util.HashCoder;
import ru.yurima.ipaddrcounter.util.IntegerHashCoder;
import ru.yurima.ipaddrcounter.util.IpHelper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IpAddressCounter {

    private String filename;
    private DistinctCounter counter;

    public IpAddressCounter(String filename, DistinctCounter counter) {
        this.filename = filename;
        this.counter = counter;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("No file");
            return;
        }

        String filename = args[0];
        if (!Files.exists(Paths.get(filename))) {
            System.out.println("Incorrect filename");
            return;
        }

        IpAddressCounter ipCounter = new IpAddressCounter(filename, new HyperLogLog());
        double result = ipCounter.estimate();
        System.out.printf("There are about %d distinct records in this file",
                Math.round(result));
    }

    public double estimate() throws IOException {
        HashCoder<Integer> intCoder = new IntegerHashCoder();
        FileSourceStreamer streamer = new FileSourceStreamer(filename);
        streamer.stream().forEach(s-> {
            counter.add(
                    intCoder.hash(
                            IpHelper.toInteger(s)
                    )
            );
        });
        return counter.count();
    }
}
