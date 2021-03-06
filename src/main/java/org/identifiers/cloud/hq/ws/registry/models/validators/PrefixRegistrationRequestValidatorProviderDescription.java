package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterPrefixPayload;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2019-03-18 11:30
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
// TODO I don't think it needs to be of "prototype" scope
@Component
@Scope("prototype")
@Qualifier("PrefixRegistrationRequestValidatorProviderDescription")
public class PrefixRegistrationRequestValidatorProviderDescription implements PrefixRegistrationRequestValidator {
    public static final int DESCRIPTIONI_CONTENT_MIN_LENGTH = 50;

    @Override
    public boolean validate(ServiceRequestRegisterPrefixPayload request) throws PrefixRegistrationRequestValidatorException {
        if ((request.getProviderDescription() == null) || (request.getProviderDescription().length() < DESCRIPTIONI_CONTENT_MIN_LENGTH)) {
            // TODO In future iterations, use a different mechanism for reporting back why this is not valid, and leave exceptions for non-recoverable conditions
            throw new PrefixRegistrationRequestValidatorException(String.format("Provider description MUST BE " +
                    "present, and longer than %d characters", DESCRIPTIONI_CONTENT_MIN_LENGTH));
        }
        return true;
    }
}
