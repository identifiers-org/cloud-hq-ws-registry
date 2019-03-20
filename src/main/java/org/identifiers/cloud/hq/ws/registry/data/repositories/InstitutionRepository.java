package org.identifiers.cloud.hq.ws.registry.data.repositories;

import org.identifiers.cloud.hq.ws.registry.data.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.repositories
 * Timestamp: 2018-10-16 14:11
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    // TODO
    Institution findOneByNameMatches(String name);

    List<Institution> findByName(String name);

    List<Institution> findAllByName(String name);

    List<Institution> findAllByNameContaining(String nameContent);

    List<Institution> findAllByLocationCountryCode(String countryCode);
}
