package org.identifiers.cloud.hq.ws.registry.api.controllers;

import org.identifiers.cloud.hq.ws.registry.api.models.PrefixRegistrationRequestApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.api.controllers
 * Timestamp: 2019-03-28 15:26
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This controller supports the API that handles prefix registration requests
 */
@RestController
@RequestMapping(value = "prefixRegistrationApi")
public class PrefixRegistrationRequestApiController {
    // TODO
    @Autowired
    private PrefixRegistrationRequestApiModel model;
    
}
