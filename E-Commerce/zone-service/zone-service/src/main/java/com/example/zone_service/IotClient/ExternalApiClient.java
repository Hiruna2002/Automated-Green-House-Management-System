package com.example.zone_service.IotClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "external-iot", url = "http://104.211.95.241:8080/api")
public interface ExternalApiClient {
    @PostMapping("/devices")
    DeviceResponse registerDevice(@RequestHeader("Authorization") String bearerToken,
                                  @RequestBody DeviceRequest request);

    record DeviceRequest(String name, String zoneId) {}
    record DeviceResponse(String deviceId, String name, String zoneId) {}
}
