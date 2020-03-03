package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.data.helpers.ApiAndDataModelsHelper;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestUpdateResourcePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2020-03-02 16:40
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
@Qualifier("ResourceUpdateRequestValidatorProviderHomeUrl")
public class ResourceUpdateRequestValidatorProviderHomeUrl implements ResourceUpdateRequestValidator {
    // TODO
    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorProviderHomeUrl")
    private PrefixRegistrationRequestValidator delegateValidator;

    // In a resource update request, providing a provider description is optional, as it may not be the subject of the
    // request. If provided, this validator will only check this attribute in itself, not request wide.

    @Override
    public boolean validate(ServiceRequestUpdateResourcePayload request) throws ResourceUpdateRequestValidatorException {
        try {
            return (request.getProviderHomeUrl() == null)
                    || (delegateValidator.validate(ApiAndDataModelsHelper.getFrom(request)));
        } catch (PrefixRegistrationRequestValidatorException e) {
            throw new ResourceUpdateRequestValidatorException(String.format("INVALID Provider Home URL '%s', '%s'", request.getProviderHomeUrl(), e.getMessage()));
        }
    }
}
