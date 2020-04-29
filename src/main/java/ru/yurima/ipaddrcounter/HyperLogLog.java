package ru.yurima.ipaddrcounter;

import java.util.HashMap;
import java.util.Map;

public class HyperLogLog {

    private final int M = 16;                               // Number of registers
    private final int b = (int)(Math.log(M) / Math.log(2)); // Calculate log2M register bits
    private final int[] registers = new int[M];

    {
        for (int i = 0; i < M-1; i++) {
            registers[i] = 0;
        }
    }

    public void add(int hashcode){
        int registerNumber = hashcode >>> (16 - b);         //get b first bits
        int body = (0xF000 | hashcode) - 0xF000;            //get rest of the bits
        int mostLeftBit = 16 - Integer.numberOfLeadingZeros(body);
        registers[registerNumber] = Math.max(registers[registerNumber], mostLeftBit);
    }
}
