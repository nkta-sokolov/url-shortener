package com.sokolov.id_generator_service.domain;

import java.net.NetworkInterface;
import java.util.Enumeration;

import org.springframework.stereotype.Service;

import lombok.SneakyThrows;

@Service
public class MachineIdGenerator {

    @SneakyThrows
    public long generateFromMacAddress() {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();

            if (networkInterface.getHardwareAddress() != null && !networkInterface.isLoopback()
                    && networkInterface.isUp()) {
                return generate(networkInterface.getHardwareAddress());
            }
        }

        throw new RuntimeException("No valid network interface found");
    }

    private long generate(byte[] mac) {
        long id = 0;

        for (byte currentByte : mac) {
            id = (id << 8) | (currentByte & 0xFF);
        }

        return id & ((1 << 6) - 1);
    }

}