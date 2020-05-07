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

    public IpAddressCounter(String filename) {
        this.filename = filename;
    }

    public static void main(String[] args) throws IOException {
        if(args.length < 1) return;

        String filename = args[0];
        if (!Files.exists(Paths.get(filename))) {
            System.out.println("Incorrect filename");
            return;
        }

        IpAddressCounter ipCounter = new IpAddressCounter(filename);
        double result = ipCounter.estimate();
        System.out.println(result);
    }

    public double estimate() throws IOException {
        HashCoder<Integer> intCoder = new IntegerHashCoder();
        DistinctCounter counter = new HyperLogLog();
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
