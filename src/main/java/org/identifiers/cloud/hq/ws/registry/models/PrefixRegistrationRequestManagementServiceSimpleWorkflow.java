package org.identifiers.cloud.hq.ws.registry.models;

import lombok.extern.slf4j.Slf4j;
import org.identifiers.cloud.hq.ws.registry.data.models.PrefixRegistrationRequest;
import org.identifiers.cloud.hq.ws.registry.data.models.PrefixRegistrationSession;
import org.identifiers.cloud.hq.ws.registry.data.models.PrefixRegistrationSessionEvent;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models
 * Timestamp: 2019-03-20 11:53
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
@Slf4j
public class PrefixRegistrationRequestManagementServiceSimpleWorkflow implements PrefixRegistrationRequestManagementService {
    @Override
    public PrefixRegistrationSessionEvent startRequest(PrefixRegistrationRequest request, String actor,
                                                       String additionalInformation) throws PrefixRegistrationRequestManagementServiceException {
        // TODO Open a new prefix registration session
        // TODO Set the given prefix registration request
        // TODO Create a 'start' event
        // TODO Return the event
        return null;
    }

    @Override
    public PrefixRegistrationSessionEvent amendRequest(PrefixRegistrationSession prefixRegistrationSession,
                                                       PrefixRegistrationRequest amendedRequest, String actor,
                                                       String additionalInformation) throws PrefixRegistrationRequestManagementServiceException {
        // TODO Check the prefix registration session is open
        // TODO Create the event
        // TODO Persist the amended request
        // TODO Reference the amended request in the newly created event
        // TODO Update the prefix registration request referenced at session level
        return null;
    }

    @Override
    public PrefixRegistrationSessionEvent commentRequest(PrefixRegistrationSession prefixRegistrationSession,
                                                         String actor, String additionalInformation) throws PrefixRegistrationRequestManagementServiceException {
        return null;
    }

    @Override
    public PrefixRegistrationSessionEvent rejectRequest(PrefixRegistrationSession prefixRegistrationSession,
                                                        String actor, String additionalInformation) throws PrefixRegistrationRequestManagementServiceException {
        return null;
    }

    @Override
    public PrefixRegistrationSessionEvent acceptRequest(PrefixRegistrationSession prefixRegistrationSession, String actor, String additionalInformation) throws PrefixRegistrationRequestManagementServiceException {
        return null;
    }
}
