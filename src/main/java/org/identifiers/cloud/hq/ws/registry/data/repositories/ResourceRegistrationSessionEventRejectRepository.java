package org.identifiers.cloud.hq.ws.registry.data.repositories;

import org.identifiers.cloud.hq.ws.registry.data.models.ResourceRegistrationSessionEventReject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.repositories
 * Timestamp: 2019-07-29 08:32
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
public interface ResourceRegistrationSessionEventRejectRepository extends JpaRepository<ResourceRegistrationSessionEventReject, Long> {
    // TODO
    ResourceRegistrationSessionEventReject findByResourceRegistrationSessionId(long id);
}
