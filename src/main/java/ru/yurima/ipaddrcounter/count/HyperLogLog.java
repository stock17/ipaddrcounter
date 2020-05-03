package ru.yurima.ipaddrcounter.count;

import ru.yurima.ipaddrcounter.average.HarmonicMeanProcessor;
import ru.yurima.ipaddrcounter.average.MeanProcessor;

public class HyperLogLog implements DistinctCounter {

    private final int M = 8192;                                 // Number of registers
    private final int b = (int) (Math.log(M) / Math.log(2));     // Calculate log2M register bits
    private final int[] registers = new int[M];

    {
        for (int i = 0; i < M-1; i++) { registers[i] = 0; }
    }

    public void add(long hashcode){
        int registerNumber = (int)(hashcode >>> (64 - b));      //get b first bits
        long mask = Long.MIN_VALUE >> (b - 1);                    // set first b bits to 0
        long body = (mask | hashcode) - mask;                   //get rest of the bits
        int mostLeftBit = Long.numberOfLeadingZeros(body) - b + 1;
        registers[registerNumber] = Math.max(registers[registerNumber], mostLeftBit);
    }

    public int getRegisterValue(int n){
        if (n < 0 || n > M - 1) throw new IllegalArgumentException();
        return registers[n];
    }

    /**
     * Use formula E = a * (M*M) * Z, where:
     * E is an estimation of the count
     * a (alpha) is a constant
     * M is a length of the register array
     * Z is Harmonic Mean
     */
    public double count(){
        MeanProcessor processor = new HarmonicMeanProcessor();
        double Z = processor.process(registers);
        double alpha = getAlpha();
        double E = alpha * (M*M) * Z;
        return E;
    }

    private double getAlpha(){
        switch (M){
            case(16): return 0.673;
            case(32): return 0.697;
            case(64): return 0.709;
            default:  return 0.7213 / (1 + (1.079/M));
        }
    }
}
