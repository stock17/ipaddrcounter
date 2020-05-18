package ru.yurima.ipaddrcounter;

import ru.yurima.ipaddrcounter.count.BitSetDistinctCounter;
import ru.yurima.ipaddrcounter.count.DistinctCounter;
import ru.yurima.ipaddrcounter.count.HyperLogLog;
import ru.yurima.ipaddrcounter.source.FileSourceStreamer;
import ru.yurima.ipaddrcounter.util.HashCoder;
import ru.yurima.ipaddrcounter.util.IntegerHashCoder;
import ru.yurima.ipaddrcounter.util.IpHelper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

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

        DistinctCounter counter;

        System.out.println("Choose algorithm: \n" +
                "1. HyperLogLog: min RAM, accuracy +-2%\n" +
                "2. BitSet: more RAM, exact number\n");

        Scanner sc = new Scanner(System.in);
        try {
            int i = sc.nextInt();
            switch (i) {
                case 1 : counter = new HyperLogLog();
                break;
                case 2: counter = new BitSetDistinctCounter();
                break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e){
            System.out.println("Wrong parameters");
            return;
        }

        IpAddressCounter ipCounter = new IpAddressCounter(filename,counter);
        double result = ipCounter.estimate();
        System.out.printf("There are %d distinct records in this file",
                Math.round(result));
    }

    public double estimate() throws IOException {

        FileSourceStreamer streamer = new FileSourceStreamer(filename);
        streamer.stream().forEach(s-> {
            counter.add(
                            IpHelper.toInteger(s)
            );
        });
        return counter.count();
    }
}
