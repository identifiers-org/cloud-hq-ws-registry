package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.apache.commons.lang3.StringUtils;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterPrefixPayload;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2019-03-14 15:39
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
// TODO I don't think it needs to be of "prototype" scope
@Component
@Scope("prototype")
@Qualifier("PrefixRegistrationRequestValidatorProviderHomeUrl")
public class PrefixRegistrationRequestValidatorProviderHomeUrl implements PrefixRegistrationRequestValidator {

    private final WebPageChecker webPageChecker = WebPageCheckerFactory.getWebPageChecker();

    @Override
    public boolean validate(ServiceRequestRegisterPrefixPayload request) throws PrefixRegistrationRequestValidatorException {
        // TODO - Refactor this code out as a URL checker
        // Home Page URL for the resource is required
        if (request.getProviderHomeUrl() == null) {
        // TODO In future iterations, use a different mechanism for reporting back why this is not valid, and leave exceptions for non-recoverable conditions
            throw new PrefixRegistrationRequestValidatorException("Provider home URL is required");
        } else if (StringUtils.isBlank(request.getProviderHomeUrl())) {
            throw new PrefixRegistrationRequestValidatorException("Home URL cannot be empty");
        }
        boolean valid;
        try {
            valid = webPageChecker.checkWebPageUrl(request.getProviderHomeUrl());
        } catch (WebPageCheckerException e) {
            throw new PrefixRegistrationRequestValidatorException(e.getMessage());
        }
        return valid;
    }
}
