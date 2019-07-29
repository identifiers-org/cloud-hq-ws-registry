package org.identifiers.cloud.hq.ws.registry.models;

import org.identifiers.cloud.hq.ws.registry.data.models.*;
import org.identifiers.cloud.hq.ws.registry.data.repositories.*;
import org.identifiers.cloud.hq.ws.registry.data.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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
    @Autowired
    private ResourceRegistrationSessionEventRejectRepository resourceRegistrationSessionEventRejectRepository;
    @Autowired
    private ResourceRegistrationSessionEventAcceptRepository resourceRegistrationSessionEventAcceptRepository;
    @Autowired
    private ResourceRegistrationSessionEventCommentRepository resourceRegistrationSessionEventCommentRepository;
    // --- END Repositories ---

    // TODO - Services
    @Autowired
    private ResourceService resourceService;

    // TODO - Resource registration session completion actions

    // Helpers
    private boolean isResourceRegistrationSessionOpen(ResourceRegistrationSession session) {
        // Check there is no 'reject' event
        if (resourceRegistrationSessionEventRejectRepository.findByResourceRegistrationSessionId(session.getId()) != null) {
            return false;
        }
        // Check there no 'accept' event
        if (resourceRegistrationSessionEventAcceptRepository.findByResourceRegistrationSessionId(session.getId()) != null) {
            return false;
        }
        return true;
    }
    // END - Helpers

    @Transactional
    @Override
    public ResourceRegistrationSessionEventStart startRequest(ResourceRegistrationRequest request, String actor,
                                                              String additionalInformation) throws ResourceRegistrationRequestManagementServiceException {
        try {
            // Persist the given resource registration request
            ResourceRegistrationRequest savedRequest = resourceRegistrationRequestRepository.save(request);
            // Open a new resource registration session
            // Set the given prefix registration request
            ResourceRegistrationSession session =
                    new ResourceRegistrationSession().setResourceRegistrationRequest(savedRequest);
            // TODO This is the place where some kind of generated hash should be introduced, for the user to be able to
            //  check the status of the resource registration request in the future
            session = resourceRegistrationSessionRepository.save(session);
            // Create a 'start' event
            ResourceRegistrationSessionEventStart sessionEventStart = new ResourceRegistrationSessionEventStart();
            sessionEventStart.setActor(actor)
                    .setAdditionalInformation(additionalInformation)
                    .setResourceRegistrationRequest(savedRequest)
                    .setResourceRegistrationSession(session);
            // Return the event
            return resourceRegistrationSessionEventStartRepository.save(sessionEventStart);
        } catch (RuntimeException e) {
            throw new ResourceRegistrationRequestManagementServiceException(
                    String.format("While starting a resource registration session for resource registration request " +
                                    "on '%s' provider, the following error occurred: '%s'",
                            request.getProviderName(), e.getMessage()));
        }
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