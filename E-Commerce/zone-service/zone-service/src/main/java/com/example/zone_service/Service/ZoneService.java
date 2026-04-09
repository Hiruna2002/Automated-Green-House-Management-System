package com.example.zone_service.Service;

import com.example.zone_service.IotClient.ExternalApiClient;
import com.example.zone_service.IotClient.ExternalTokenManager;
import com.example.zone_service.Entity.Zone;
import com.example.zone_service.Ripository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoneService {
    private final ZoneRepository zoneRepository;
    private final ExternalApiClient externalApiClient;
    private final ExternalTokenManager tokenManager;

    public Zone createZone(Zone zone) {
        String token = tokenManager.getValidToken();
        ExternalApiClient.DeviceResponse resp = externalApiClient.registerDevice(
                "Bearer " + token,
                new ExternalApiClient.DeviceRequest(zone.getName(), zone.getName().replace(" ", "-"))
        );
        zone.setDeviceId(resp.deviceId());
        return zoneRepository.save(zone);
    }

    public Zone getZone(Long id) { return zoneRepository.findById(id).orElseThrow(); }
    public Zone updateZone(Long id, Zone zone) { zone.setId(id); return zoneRepository.save(zone); }
    public void deleteZone(Long id) { zoneRepository.deleteById(id); }
    public List<Zone> getAllZones() { return zoneRepository.findAll(); }
}
