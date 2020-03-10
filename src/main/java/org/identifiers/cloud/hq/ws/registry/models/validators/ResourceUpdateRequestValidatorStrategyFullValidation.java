package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2020-03-10 13:16
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
public class ResourceUpdateRequestValidatorStrategyFullValidation implements ResourceUpdateRequestValidatorStrategy {
    @Override
    public List<ResourceUpdateRequestValidator> getValidationChain() {
        return null;
    }
}
