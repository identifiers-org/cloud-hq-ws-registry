package org.identifiers.cloud.hq.ws.registry.api.controllers;

import org.identifiers.cloud.hq.ws.registry.api.models.ResourceManagementApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.api.controllers
 * Timestamp: 2019-07-25 12:11
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This API is intended for management of resources at a more complex level than just the REST repositories.
 */
@RestController
@RequestMapping("resourceManagementApi")
public class ResourceManagementApi {

    @Autowired
    private ResourceManagementApiModel model;
    
    // TODO
}
