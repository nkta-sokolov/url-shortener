package com.sokolov.id_generator_service.domain;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SequenceGenerator {

    private static final int SEQUENCE_BITS = 30;

    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

    //todo: need to persist on disk
    private final AtomicLong sequence = new AtomicLong(0);

    private final MachineIdHolder machineIdHolder;

    public long generate() {
        long next = sequence.incrementAndGet() & MAX_SEQUENCE;
        return (machineIdHolder.machineId() << SEQUENCE_BITS) | next;
    }

}