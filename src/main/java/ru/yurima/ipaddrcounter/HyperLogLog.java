package ru.yurima.ipaddrcounter;

public class HyperLogLog {

    private int M; // Number of registers
    int[] registers;

    public HyperLogLog(int m) {
        this.M = m;
        this.registers = new int[M];
    }

    public HyperLogLog() {
        this(32);
    }
}
