package com.sokolov.id_generator_service.domain.service;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import lombok.SneakyThrows;

@Service
public class NodeIdBuilder {

    private static final int NODE_ID_BITS = 10;

    private static final int MAX_NODE_ID = (int) (Math.pow(2, NODE_ID_BITS) - 1);

    @SneakyThrows
    public int build() {
        Byte[] macAddress = extractMacAddress();
        String formattedMacAddress = formatMacAddress(macAddress);
        return formattedMacAddress.hashCode() & MAX_NODE_ID;
    }

    private Byte[] extractMacAddress() throws SocketException {
        byte[] macAddress = NetworkInterface.networkInterfaces()
                .map(this::getHardwareAddress)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no mac address"));

        return wrapPrimitiveByteArray(macAddress);
    }

    @SneakyThrows
    private byte[] getHardwareAddress(NetworkInterface networkInterface) {
        return networkInterface.getHardwareAddress();
    }

    private Byte[] wrapPrimitiveByteArray(byte[] macAddress) {
        return IntStream.range(0, macAddress.length)
                .mapToObj(i -> macAddress[i])
                .toArray(Byte[]::new);
    }

    private String formatMacAddress(Byte[] macAddress) {
        return Stream.of(macAddress)
                .map(currentByte -> String.format("%02X", currentByte))
                .collect(Collectors.joining());
    }

}