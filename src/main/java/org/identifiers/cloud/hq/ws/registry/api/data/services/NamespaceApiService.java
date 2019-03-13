package org.identifiers.cloud.hq.ws.registry.api.data.services;

import org.identifiers.cloud.hq.ws.registry.api.data.models.Namespace;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.api.data.services
 * Timestamp: 2019-03-06 14:29
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This is a service for working with Namespace model within the API models context, i.e. it works with API Namespace
 * model
 */
@Component
public class NamespaceApiService {
    public List<Namespace> getNamespaceTreeDownToLeaves() {
        // TODO
        return new ArrayList<>();
    }
}