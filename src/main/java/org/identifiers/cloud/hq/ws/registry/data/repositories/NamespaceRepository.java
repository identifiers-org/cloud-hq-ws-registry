package org.identifiers.cloud.hq.ws.registry.data.repositories;

import org.identifiers.cloud.hq.ws.registry.data.models.Namespace;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.repositories
 * Timestamp: 2018-10-16 14:15
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
// TODO - refactoring to JPA repository
public interface NamespaceRepository extends MongoRepository<Namespace, String> {
}
