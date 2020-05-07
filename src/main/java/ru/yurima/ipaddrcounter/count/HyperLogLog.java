package ru.yurima.ipaddrcounter.count;

import ru.yurima.ipaddrcounter.average.HarmonicMeanProcessor;
import ru.yurima.ipaddrcounter.average.MeanProcessor;

public class HyperLogLog implements DistinctCounter {

    private final int M = 2048;                               // Number of registers
    private final int b = (int)(Math.log(M) / Math.log(2)); // Calculate log2M register bits
    private final int[] registers = new int[M];

    {
        for (int i = 0; i < M-1; i++) { registers[i] = 0; }
    }

    public void add(int hashcode){
//        int mask = Integer.MIN_VALUE >> (Integer.SIZE - b) - 1;   // set first b bits to 0, others to 1
//        int registerNumber = hashcode & ~mask;                    //get b first bits
//        int body = hashcode >> b;                                 //get rest of the bits

        int registerNumber = hashcode >>> (Integer.SIZE - b);   //get b first bits
        int mask = Integer.MIN_VALUE >> (b - 1);                // set first b bits to 0, others to 1
        int body = hashcode & ~mask;                            //get rest of the bits

        int mostLeftBit = Integer.numberOfLeadingZeros(body) - b + 1;
        registers[registerNumber] = Math.max(registers[registerNumber], mostLeftBit);
    }

    public int getRegisterValue(int n){
        if (n > M - 1) throw new IllegalArgumentException();
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
