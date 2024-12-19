package com.example.loginservice.util;

public class SnowflakeIdGenerator {
    private static final long EPOCH = 1577836800000L; // Custom epoch
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & 4095; // 12-bit sequence
            if (sequence == 0) {
                while (timestamp <= lastTimestamp) {
                    timestamp = System.currentTimeMillis();
                }
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - EPOCH) << 22) | sequence;
    }
}
