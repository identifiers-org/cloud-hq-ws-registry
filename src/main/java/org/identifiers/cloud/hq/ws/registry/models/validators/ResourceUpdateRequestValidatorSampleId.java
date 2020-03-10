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
@Qualifier("ResourceUpdateRequestValidatorSampleId")
public class ResourceUpdateRequestValidatorSampleId implements ResourceUpdateRequestValidator {
    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorSampleId")
    private PrefixRegistrationRequestValidator delegateValidator;

    // Including a Sample ID in a resource update request is optional, but in case it has been provided, we follow
    // similar rules to those followed for additional information in prefix registration requests
    @Override
    public boolean validate(ServiceRequestUpdateResourcePayload request) throws ResourceUpdateRequestValidatorException {
        try {
            return (request.getSampleId() == null)
                    || (delegateValidator.validate(ApiAndDataModelsHelper.getFrom(request)));
        } catch (PrefixRegistrationRequestValidatorException e) {
            throw new ResourceUpdateRequestValidatorException(String.format("Invalid sample ID: '%s', '%s'", request.getSampleId(), e.getMessage()));
        }
    }
}
