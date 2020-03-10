package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.data.helpers.ApiAndDataModelsHelper;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestUpdateResourcePayload;
import org.springframework.beans.factory.annotation.Autowired;
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
@Qualifier("ResourceUpdateRequestValidatorRequesterName")
public class ResourceUpdateRequestValidatorRequesterName implements ResourceUpdateRequestValidator {
    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorRequesterName")
    private PrefixRegistrationRequestValidator delegateValidator;

    @Override
    public boolean validate(ServiceRequestUpdateResourcePayload request) throws ResourceUpdateRequestValidatorException {
        try {
            return (request.getRequester().getName() == null)
                    || (delegateValidator.validate(ApiAndDataModelsHelper.getFrom(request)));
        } catch (PrefixRegistrationRequestValidatorException e) {
            throw new ResourceUpdateRequestValidatorException(String.format("Invalid requester name, '%s', '%s'", request.getRequester().getName(), e.getMessage()));
        }
    }
}
