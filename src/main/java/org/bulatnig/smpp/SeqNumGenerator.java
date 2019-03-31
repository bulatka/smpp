package org.bulatnig.smpp;

public class SeqNumGenerator {
    private static final int MIN_VALUE = 0x00000001;
    private static final int MAX_VALUE = 0x7FFFFFFF;

    private int value = 0;

    public synchronized int next() {
        if (MAX_VALUE == value) {
            value = MIN_VALUE;
        } else {
            value++;
        }
        return value;
    }

}
