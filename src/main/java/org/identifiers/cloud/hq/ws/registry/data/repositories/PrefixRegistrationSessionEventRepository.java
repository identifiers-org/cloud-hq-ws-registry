package org.identifiers.cloud.hq.ws.registry.data.repositories;

import org.identifiers.cloud.hq.ws.registry.data.models.PrefixRegistrationSessionEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project: hq-registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.repositories
 * Timestamp: 2019-03-19 13:15
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
public interface PrefixRegistrationSessionEventRepository extends JpaRepository<PrefixRegistrationSessionEvent, Long> {
    // TODO
    List<PrefixRegistrationSessionEvent> findByPrefixRegistrationSessionId(long id);
}
