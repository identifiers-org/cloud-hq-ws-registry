package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.data.helpers.ApiAndDataModelsHelper;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestUpdateResourcePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2020-03-02 16:43
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
public class ResourceUpdateRequestValidatorInstitutionDescription implements ResourceUpdateRequestValidator {
    // TODO

    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorInstitutionDescription")
    private PrefixRegistrationRequestValidator delegateValidator;

    // In a resource update request, providing a description of the associated institution is optional, as it may not
    // be the subject of the request. If provided, this validator will only check at 'institutionDescription' attribute
    // level, e.g. it will only check that the provided content for institution description is compliant with the
    // guidelines on what this attribute should contain, but it won't check beyond this point to find out, for example,
    // whether the request makes sense itself, e.g. if institutional description has been provided but nothing else,
    // it may not be a resource update request, but an institution update request.

    @Override
    public boolean validate(ServiceRequestUpdateResourcePayload request) throws ResourceUpdateRequestValidatorException {
        try {
            return (request.getInstitutionDescription() == null)
                    || (delegateValidator.validate(ApiAndDataModelsHelper.getFrom(request)));
        } catch (PrefixRegistrationRequestValidatorException e) {
            throw new ResourceUpdateRequestValidatorException(String.format("INVALID institution description '%s', '%s'", request.getInstitutionDescription(), e.getMessage()));
        }
    }
}
