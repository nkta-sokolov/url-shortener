package com.sokolov.analytics_service.domain.service;

import java.net.InetAddress;

import org.springframework.stereotype.Service;

import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.sokolov.analytics_service.domain.model.kafka.ClickDetails;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ua_parser.Client;
import ua_parser.Parser;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClickDetailsService {

    public void process(ClickDetails clickDetails) {
        String cityName = getCityName(clickDetails.getIp());
        log.debug("City name = {}", cityName);
        Client client = getClient(clickDetails);
        log.debug("Client = {}", client);
    }

    @SneakyThrows
    public String getCityName(String ip) {
        try (WebServiceClient client = new WebServiceClient.Builder(957666, "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")
                .host("geolite.info")
                .build()) {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse cityResponse = client.city(ipAddress);
            City city = cityResponse.getCity();
            return city.getName();
        }
    }

    private Client getClient(ClickDetails clickDetails) {
        Parser userAgentParser = new Parser();
        return userAgentParser.parse(clickDetails.getUserAgent());
    }

}