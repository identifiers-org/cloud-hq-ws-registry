package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.apache.commons.lang3.StringUtils;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterPrefixPayload;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2019-03-14 15:49
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
// TODO I don't think it needs to be of "prototype" scope
@Component
@Scope("prototype")
@Qualifier("PrefixRegistrationRequestValidatorAuthHelpUrl")
public class PrefixRegistrationRequestValidatorAuthHelpUrl implements PrefixRegistrationRequestValidator {
    @Override
    public boolean validate(ServiceRequestRegisterPrefixPayload request) throws PrefixRegistrationRequestValidatorException {
        if (request.isProtectedUrls()) {
            if (StringUtils.isBlank(request.getAuthHelpUrl())) {
                throw new PrefixRegistrationRequestValidatorException("MISSING required authentication info URL");
            }
            if (!request.getAuthHelpUrl().toLowerCase().startsWith("http")) {
                throw new PrefixRegistrationRequestValidatorException("INVALID authentication info URL, it must be a HTTP(S) URL");
            }
            WebPageChecker webPageChecker = WebPageCheckerFactory.getWebPageChecker();
            if (!webPageChecker.checkForValidUrl(request.getAuthHelpUrl())) {
                throw new PrefixRegistrationRequestValidatorException(String.format("Invalid URL '%s'", request.getAuthHelpUrl()));
            }
        }
        return true;
    }
}
