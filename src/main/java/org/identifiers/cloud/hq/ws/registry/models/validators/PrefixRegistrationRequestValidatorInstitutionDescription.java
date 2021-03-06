package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterPrefixPayload;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2019-03-18 12:21
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
// TODO I don't think it needs to be of "prototype" scope
@Component
@Scope("prototype")
@Qualifier("PrefixRegistrationRequestValidatorInstitutionDescription")
public class PrefixRegistrationRequestValidatorInstitutionDescription implements PrefixRegistrationRequestValidator {
    // TODO: should be extracted from all description validators to a global config.
    public static final int DESCRIPTION_CONTENT_MIN_CHARS = 50;

    @Override
    public boolean validate(ServiceRequestRegisterPrefixPayload request) throws PrefixRegistrationRequestValidatorException {
        if (request.getInstitutionDescription() == null || request.getInstitutionDescription().length() < DESCRIPTION_CONTENT_MIN_CHARS) {
            // TODO In future iterations, use a different mechanism for reporting back why this is not valid, and leave exceptions for non-recoverable conditions
            throw new PrefixRegistrationRequestValidatorException(String.format("Institution description must be longer than %d characters", DESCRIPTION_CONTENT_MIN_CHARS));
        }
        return true;
    }
}
