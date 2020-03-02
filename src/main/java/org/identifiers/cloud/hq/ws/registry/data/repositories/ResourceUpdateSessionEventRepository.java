package org.identifiers.cloud.hq.ws.registry.data.repositories;

import org.identifiers.cloud.hq.ws.registry.data.models.ResourceUpdateSessionEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.repositories
 * Timestamp: 2020-03-02 15:12
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
public interface ResourceUpdateSessionEventRepository extends JpaRepository<ResourceUpdateSessionEvent, Long> {
    // TODO
    List<ResourceUpdateSessionEvent> findByResourceUpdateSessionId(long id);
}
