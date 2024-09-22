package com.sokolov.id_generator_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sokolov.id_generator_service.domain.MachineIdGenerator;
import com.sokolov.id_generator_service.domain.MachineIdHolder;

@Configuration
public class MachineIdHolderConfig {

    private static final int MACHINE_ID_BITS = 6;

    private static final long MAX_MACHINE_ID = (1L << MACHINE_ID_BITS) - 1;

    @Bean
    public MachineIdHolder machineIdHolder(MachineIdGenerator machineIdGenerator) {
        long machineId = machineIdGenerator.generateFromMacAddress();
        if (machineId < 0 || machineId > MAX_MACHINE_ID) {
            throw new IllegalArgumentException("machineId must be between 0 and 63");
        }
        return new MachineIdHolder(machineId);
    }

}