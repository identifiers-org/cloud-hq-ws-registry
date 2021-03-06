package org.identifiers.cloud.hq.ws.registry.models;

import org.identifiers.cloud.hq.ws.registry.data.models.*;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models
 * Timestamp: 2019-03-15 10:26
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * Attending a prefix registration request involves registering the request itself, making possible amendments to it,
 * possibly associating comments to the request, and closing the request by either accepting it or rejecting it.
 *
 * A prefix registration request management service implements these operations to support this workflow, and it follows
 * a strategy pattern.
 *
 */
public interface PrefixRegistrationRequestManagementService {
    /**
     * This method starts the prefix registration process for the given prefix registration request.
     *
     * In the current iteration of this microservice it uses the prefix registration request model from the API straight
     * away, which is ok, but it may be refactored later, including some model transformation code.
     * @param request the prefix registration requests to use for starting the process
     * @param actor the actor that has triggered this action
     * @param additionalInformation possible additional information related to this action
     * @return the prefix registration event registered as a consequence of executing this action
     * @throws PrefixRegistrationRequestManagementServiceException
     */
    PrefixRegistrationSessionEventStart startRequest(PrefixRegistrationRequest request,
                                                     String actor,
                                                     String additionalInformation) throws PrefixRegistrationRequestManagementServiceException;

    /**
     * Amend the prefix registration request being processed in the given prefix registration session.
     *
     * @param prefixRegistrationSession opened prefix registration session where the request is being amended
     * @param amendedRequest amended prefix registration request, which is a copy of the latest version of the prefix
     *                       registration request, with some changes applied to it.
     * @param actor the actor that has triggered this action
     * @param additionalInformation possible additional information related to this action
     * @return the prefix registration event registered as a consequence of executing this action
     * @throws PrefixRegistrationRequestManagementServiceException
     */
    PrefixRegistrationSessionEventAmend amendRequest(PrefixRegistrationSession prefixRegistrationSession,
                                                     PrefixRegistrationRequest amendedRequest,
                                                     String actor,
                                                     String additionalInformation) throws PrefixRegistrationRequestManagementServiceException;

    /**
     * Log a comment on the given prefix registration session.
     *
     * @param prefixRegistrationSession opened prefix registration session where this action is being triggered
     * @param comment the comment to be logged for this resource registration session
     * @param actor the actor that has triggered this action
     * @param additionalInformation possible additional information related to this action
     * @return the prefix registration event registered as a consequence of executing this action
     * @throws PrefixRegistrationRequestManagementServiceException
     */
    PrefixRegistrationSessionEventComment commentRequest(PrefixRegistrationSession prefixRegistrationSession,
                                                  String comment,
                                                  String actor,
                                                  String additionalInformation) throws PrefixRegistrationRequestManagementServiceException;

    /**
     * Close a prefix registration session, by rejecting its prefix registration request.
     *
     * @param prefixRegistrationSession opened prefix registration session where this action is being triggered
     * @param rejectionReason the reason why the prefix registration request was rejected
     * @param actor the actor that has triggered this action
     * @param additionalInformation possible additional information related to this action
     * @return the prefix registration event registered as a consequence of executing this action
     * @throws PrefixRegistrationRequestManagementServiceException
     */
    PrefixRegistrationSessionEventReject rejectRequest(PrefixRegistrationSession prefixRegistrationSession,
                                                 String rejectionReason,
                                                 String actor,
                                                 String additionalInformation) throws PrefixRegistrationRequestManagementServiceException;

    /**
     * Close a prefix registration session, by accepting its prefix registration request.
     *
     * @param prefixRegistrationSession opened prefix registration session where this action is being triggered
     * @param acceptanceReason the reason why the prefix registration request was accepted
     * @param actor the actor that has triggered this action
     * @param additionalInformation possible additional information related to this action
     * @return the prefix registration event registered as a consequence of executing this action
     * @throws PrefixRegistrationRequestManagementServiceException
     */
    PrefixRegistrationSessionEventAccept acceptRequest(PrefixRegistrationSession prefixRegistrationSession,
                                                 String acceptanceReason,
                                                 String actor,
                                                 String additionalInformation) throws PrefixRegistrationRequestManagementServiceException;
}
