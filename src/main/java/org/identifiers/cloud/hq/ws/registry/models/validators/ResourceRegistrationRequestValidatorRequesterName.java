package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.data.helpers.ApiAndDataModelsHelper;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterResourcePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2019-07-26 13:33
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
@Qualifier("ResourceRegistrationRequestValidatorRequesterName")
public class ResourceRegistrationRequestValidatorRequesterName implements ResourceRegistrationRequestValidator {
    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorRequesterName")
    private PrefixRegistrationRequestValidator delegateValidator;

    @Override
    public boolean validate(ServiceRequestRegisterResourcePayload request) throws ResourceRegistrationRequestValidatorException {
        try {
            return delegateValidator.validate(ApiAndDataModelsHelper.getFrom(request));
        } catch (PrefixRegistrationRequestValidatorException e) {
            throw new ResourceRegistrationRequestValidatorException(e.getMessage());
        }
    }
}
