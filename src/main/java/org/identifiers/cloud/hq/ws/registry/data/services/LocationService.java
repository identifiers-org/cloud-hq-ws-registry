package org.identifiers.cloud.hq.ws.registry.data.services;

import lombok.extern.slf4j.Slf4j;
import org.identifiers.cloud.hq.ws.registry.data.models.Location;
import org.identifiers.cloud.hq.ws.registry.data.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.services
 * Timestamp: 2019-03-28 10:56
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This class implements complex persistence logic for Location.
 */
@Service
@Slf4j
public class LocationService {
    // TODO
    // Repository
    @Autowired
    private LocationRepository repository;

    public Location registerLocation(Location location) throws LocationServiceException {
        // TODO
        // TODO Do check for ISO 3166/MA Alpha-2 Country Codes compliance
        Location registeredLocation = repository.findByCountryCode(location.getCountryCode());
        if (registeredLocation == null) {
            // TODO
            log.info(String.format("Registering Location, country code '%s', country name '%s'", location.getCountryCode(), location.getCountryName()));
            registeredLocation = repository.save(location);
        }
        return registeredLocation;
    }
}
