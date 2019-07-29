package org.identifiers.cloud.hq.ws.registry.models;

import org.identifiers.cloud.hq.ws.registry.data.models.*;
import org.identifiers.cloud.hq.ws.registry.data.repositories.ResourceRegistrationRequestRepository;
import org.identifiers.cloud.hq.ws.registry.data.repositories.ResourceRegistrationSessionEventAmendRepository;
import org.identifiers.cloud.hq.ws.registry.data.repositories.ResourceRegistrationSessionEventStartRepository;
import org.identifiers.cloud.hq.ws.registry.data.repositories.ResourceRegistrationSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models
 * Timestamp: 2019-07-29 02:21
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
public class ResourceRegistrationRequestManagementServiceSimpleWorkflow implements ResourceRegistrationRequestManagementService {

    // TODO - Repositories
    @Autowired
    private ResourceRegistrationRequestRepository resourceRegistrationRequestRepository;
    @Autowired
    private ResourceRegistrationSessionRepository resourceRegistrationSessionRepository;
    @Autowired
    private ResourceRegistrationSessionEventStartRepository resourceRegistrationSessionEventStartRepository;
    @Autowired
    private ResourceRegistrationSessionEventAmendRepository resourceRegistrationSessionEventAmendRepository;
    // --- END Repositories ---

    // TODO - Services

    // TODO - Resource registration session completion actions

    // TODO Helpers

    @Override
    public ResourceRegistrationSessionEventStart startRequest(ResourceRegistrationRequest request, String actor,
                                                              String additionalInformation) throws ResourceRegistrationRequestManagementServiceException {
        return null;
    }

    @Override
    public ResourceRegistrationSessionEventAmend amendRequest(ResourceRegistrationSession resourceRegistrationSession
            , ResourceRegistrationRequest amendedRequest, String actor, String additionalInformation) throws ResourceRegistrationRequestManagementServiceException {
        return null;
    }

    @Override
    public ResourceRegistrationSessionEventComment commentRequest(ResourceRegistrationSession resourceRegistrationSession, String comment, String actor, String additionalInformation) throws ResourceRegistrationRequestManagementServiceException {
        return null;
    }

    @Override
    public ResourceRegistrationSessionEventReject rejectRequest(ResourceRegistrationSession resourceRegistrationSession, String rejectionReason, String actor, String additionalInformation) throws ResourceRegistrationRequestManagementServiceException {
        return null;
    }

    @Override
    public ResourceRegistrationSessionEventAccept acceptRequest(ResourceRegistrationSession resourceRegistrationSession, String acceptanceReason, String actor, String additionalInformation) throws ResourceRegistrationRequestManagementServiceException {
        return null;
    }
}
