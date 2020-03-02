package org.identifiers.cloud.hq.ws.registry.data.repositories;

import org.identifiers.cloud.hq.ws.registry.data.models.ResourceUpdateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.repositories
 * Timestamp: 2020-03-02 15:07
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
public interface ResourceUpdateRequestRepository extends JpaRepository<ResourceUpdateRequest, Long> {
}
