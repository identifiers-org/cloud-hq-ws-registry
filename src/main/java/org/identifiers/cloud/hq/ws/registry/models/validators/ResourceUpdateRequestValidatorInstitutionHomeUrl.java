package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.data.helpers.ApiAndDataModelsHelper;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestUpdateResourcePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2020-03-02 16:42
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
@Qualifier("ResourceUpdateRequestValidatorInstitutionHomeUrl")
public class ResourceUpdateRequestValidatorInstitutionHomeUrl implements ResourceUpdateRequestValidator {
    // TODO
    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorInstitutionHomeUrl")
    private PrefixRegistrationRequestValidator delegateValidator;

    // In a resource update request, providing a home URL for the associated institution is optional as it may not be
    // the subject of the request. If provided, this validator will only check this attribute in itself, not request wide.

    @Override
    public boolean validate(ServiceRequestUpdateResourcePayload request) throws ResourceUpdateRequestValidatorException {
        try {
            return (request.getInstitutionHomeUrl() == null)
                    || (delegateValidator.validate(ApiAndDataModelsHelper.getFrom(request)));
        } catch (PrefixRegistrationRequestValidatorException e) {
            throw new ResourceUpdateRequestValidatorException(String.format("INVALID Institution Home URL, '%s', '%s'", request.getInstitutionHomeUrl(), e.getMessage()));
        }
    }
}
