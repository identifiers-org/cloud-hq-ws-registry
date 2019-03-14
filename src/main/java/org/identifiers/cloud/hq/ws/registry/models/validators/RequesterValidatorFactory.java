package org.identifiers.cloud.hq.ws.registry.models.validators;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2019-03-14 15:26
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
public class RequesterValidatorFactory {
    public static RequesterValidator getDefaultValidator() {
        return new RequesterValidatorFullValidator();
    }
}