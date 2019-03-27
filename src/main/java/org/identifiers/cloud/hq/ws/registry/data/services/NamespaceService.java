package org.identifiers.cloud.hq.ws.registry.data.services;

import org.identifiers.cloud.hq.ws.registry.data.models.Namespace;
import org.identifiers.cloud.hq.ws.registry.data.repositories.NamespaceRepository;
import org.identifiers.cloud.hq.ws.registry.models.MirIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.services
 * Timestamp: 2019-03-25 13:37
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This is service that implements complex persistence operations for Namespace objects
 */
@Service
public class NamespaceService {

    // Repositories
    @Autowired
    private NamespaceRepository namespaceRepository;
    // END - Repositories

    // Services
    @Autowired
    private MirIdService mirIdService;
    // END - Services

    // TODO
    public void registerNewNamespace(Namespace namespace) throws NamespaceServiceException {
        // TODO
        // Check that you're not trying to register an already existing namespace
        if (namespaceRepository.findByPrefix(namespace.getPrefix()) != null) {
            throw new NamespaceServiceException(String.format("CANNOT register namespace '%s', " +
                    "because IT IS ALREADY REGISTERED", namespace.getPrefix()));
        }
        // TODO Check if the person needs to be created or not
        // TODO Get a MIR ID for the new namespace (libapi?)
        // TODO Persist the new namespace
    }
}
