package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestUpdateResourcePayload;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2020-03-10 13:06
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
public interface ResourceUpdateRequestValidatorStrategy extends ResourceUpdateRequestValidator {
    // TODO
    default List<ResourceUpdateRequestValidator> getValidationChain() {
        return new ArrayList<>();
    }

    @Override
    default boolean validate(ServiceRequestUpdateResourcePayload request) throws ResourceUpdateRequestValidatorException {
        // Run all the validators collecting all the possible error messages
        List<String> errors = getValidationChain()
                .parallelStream()
                .map(validator -> {
                    try {
                        validator.validate(request);
                    } catch (ResourceUpdateRequestValidatorException e) {
                        return e.getMessage();
                    }
                    return null;
                })
                .filter(Objects::nonNull).collect(Collectors.toList());
        if (!errors.isEmpty()) {
            // Report the errors
            throw new ResourceUpdateRequestValidatorException(String.join("\n", errors));
        }
        return true;
    }
}
