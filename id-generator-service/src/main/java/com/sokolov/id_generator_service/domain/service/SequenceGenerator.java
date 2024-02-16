package com.sokolov.id_generator_service.domain.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

//Epoch bits - 41
//Unused bits - 1
@RequiredArgsConstructor
@Service
public class SequenceGenerator {

    private static final int NODE_ID_BITS = 10;

    private static final int SEQUENCE_BITS = 12;

    private static final int MAX_SEQUENCE = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    //Custom Epoch (February 12, 2024, Midnight UTC = 2024-02-12T00:00:00Z)
    private static final long CUSTOM_EPOCH = 1707696000000L;

    private volatile long lastTimestamp = -1L;

    private volatile long sequence = 0L;

    private final Integer nodeId;

    public synchronized long nextId() {
        long currentTimestamp = getTimestamp();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;

        return currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS)
                | ((long) nodeId << SEQUENCE_BITS)
                | sequence;
    }

    private static long getTimestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = getTimestamp();
        }
        return currentTimestamp;
    }

}