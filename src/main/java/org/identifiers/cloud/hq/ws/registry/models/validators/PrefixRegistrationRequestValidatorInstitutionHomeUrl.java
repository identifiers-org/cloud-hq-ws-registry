package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.apache.commons.lang3.StringUtils;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterPrefixPayload;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2019-03-28 10:30
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
// TODO I don't think it needs to be of "prototype" scope
@Component
@Scope("prototype")
@Qualifier("PrefixRegistrationRequestValidatorInstitutionHomeUrl")
public class PrefixRegistrationRequestValidatorInstitutionHomeUrl implements PrefixRegistrationRequestValidator {

    private final WebPageChecker webPageChecker = WebPageCheckerFactory.getWebPageChecker();

    @Override
    public boolean validate(ServiceRequestRegisterPrefixPayload request) throws PrefixRegistrationRequestValidatorException {
        // TODO - Refactor this code out as a URL checker
        // Home Page URL for the resource is required
        if (request.getInstitutionHomeUrl() == null) {
            throw new PrefixRegistrationRequestValidatorException("MISSING URL for a Institution");
        } else if (StringUtils.isBlank(request.getInstitutionHomeUrl())) {
            throw new PrefixRegistrationRequestValidatorException("Home URL cannot be empty");
        }
        boolean valid;
        try {
            valid = webPageChecker.checkWebPageUrl(request.getInstitutionHomeUrl());
        } catch (WebPageCheckerException e) {
            throw new PrefixRegistrationRequestValidatorException(e.getMessage());
        }
        return valid;
    }
}
