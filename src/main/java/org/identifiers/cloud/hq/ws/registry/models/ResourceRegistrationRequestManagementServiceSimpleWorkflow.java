package org.identifiers.cloud.hq.ws.registry.models;

import org.identifiers.cloud.hq.ws.registry.data.models.*;
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
