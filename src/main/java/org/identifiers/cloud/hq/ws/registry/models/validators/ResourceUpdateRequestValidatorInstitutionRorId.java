package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestUpdateResourcePayload;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2020-03-02 16:44
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
@Qualifier("ResourceUpdateRequestValidatorInstitutionRorId")
public class ResourceUpdateRequestValidatorInstitutionRorId implements ResourceUpdateRequestValidator {
    // TODO

    // In a resource update request, providing a ROR ID for the associated institution is optional, as it may not be the
    // subject of the request. If provided, this validator will only check this attribute in itself, not request wide.

    @Override
    public boolean validate(ServiceRequestUpdateResourcePayload request) throws ResourceUpdateRequestValidatorException {
        // TODO This validator could rely on the existing ROR ID machinery behind the registry ROR ID API to check
        //  whether a given ROR ID is valid or not.
        return true;
    }
}
