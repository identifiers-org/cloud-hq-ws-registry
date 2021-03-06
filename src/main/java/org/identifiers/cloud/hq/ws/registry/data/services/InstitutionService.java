package org.identifiers.cloud.hq.ws.registry.data.services;

import lombok.extern.slf4j.Slf4j;
import org.identifiers.cloud.hq.ws.registry.data.models.Institution;
import org.identifiers.cloud.hq.ws.registry.data.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.services
 * Timestamp: 2019-03-28 10:41
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 * <p>
 * This Service implements complex persistence logic for Institutions.
 */
@Service
@Slf4j
public class InstitutionService {
    // Repository
    @Autowired
    private InstitutionRepository repository;

    // Services
    @Autowired
    private LocationService locationService;

    /**
     * Register an institution if not registered.
     * @param institution the institution to register
     * @return the registered institution
     * @throws InstitutionServiceException
     */
    @Transactional
    public Institution registerInstitution(Institution institution) throws InstitutionServiceException {
        Institution registeredInstitution = repository.findByName(institution.getName());
        if (registeredInstitution == null) {
            // TODO - Run validations, probably through Repository Event listeners
            institution.setLocation(locationService.registerLocation(institution.getLocation()));
            registeredInstitution = repository.save(institution);
            log.info(String.format("Registered Institution with ID '%d', name '%s'",
                    registeredInstitution.getId(), registeredInstitution.getName()));
        }
        return registeredInstitution;
    }
}
