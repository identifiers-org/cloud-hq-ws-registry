package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.data.helpers.ApiAndDataModelsHelper;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestUpdateResourcePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2020-03-02 16:46
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
@Qualifier("ResourceUpdateRequestValidatorActionContactPersonUpdate")
public class ResourceUpdateRequestValidatorActionContactPersonUpdate implements ResourceUpdateRequestValidator {
    // TODO
    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorRequester")
    private PrefixRegistrationRequestValidator delegateValidator;

    // Requesting this action, i.e. if true in the request, calls for cross validation with the presence of requester
    // details, otherwise it makes no ses
    @Override
    public boolean validate(ServiceRequestUpdateResourcePayload request) throws ResourceUpdateRequestValidatorException {
        if (request.isActionContactPersonUpdate()) {
            return (request.getRequester() != null)
                    && (delegateValidator.validate(ApiAndDataModelsHelper.getFrom(request)));
        }
        return true;
    }
}
