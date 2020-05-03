package ru.yurima.ipaddrcounter.hash;

public class StringHashCoder implements HashCoder<String> {
    @Override
    public long getHashCode64(String obj) {
//        long h = 1125899906842597L;
//        for (byte v : obj.getBytes()) {
//            h = 31 * h + (v & 0xff);
//        }
//        return h;

//        long h = 1125899906842597L;
//        int length = obj.length() >> 1;
//        for (int i = 0; i < length; i++) {
//            h = 31 * h + obj.charAt(i);
//        }
//        return h;



//            long h = 1125899906842597L; // prime
//            int len = obj.length();
//
//            for (int i = 0; i < len; i++) {
//                h = 31*h + obj.charAt(i);
//            }
//            return h;


        long h = obj.hashCode();
        long h1 = h << 32;
        h1 = h1 + h;
        return  h1;

    }

    @Override
    public int getHashCode32(String obj) {
        return obj.hashCode();
    }
}
