package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestUpdateResourcePayload;
import org.identifiers.cloud.hq.ws.registry.data.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2020-03-10 15:21
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
@Qualifier("ResourceUpdateRequestValidatorProviderId")
public class ResourceUpdateRequestValidatorProviderId implements ResourceUpdateRequestValidator {
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public boolean validate(ServiceRequestUpdateResourcePayload request) throws ResourceUpdateRequestValidatorException {
        try {
            return resourceRepository.findById(request.getProviderId()).isPresent();
        } catch (RuntimeException e) {
            throw new ResourceUpdateRequestValidatorException(String.format("There was a problem when locating resource with ID '%d', '%s'", request.getProviderId(), e.getMessage()));
        }
    }
}
