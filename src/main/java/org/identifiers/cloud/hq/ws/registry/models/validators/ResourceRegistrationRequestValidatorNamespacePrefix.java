package org.identifiers.cloud.hq.ws.registry.models.validators;

import lombok.extern.slf4j.Slf4j;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterResourcePayload;
import org.identifiers.cloud.hq.ws.registry.data.models.Namespace;
import org.identifiers.cloud.hq.ws.registry.data.repositories.NamespaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2019-07-29 19:07
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Slf4j
@Component
@Qualifier("ResourceRegistrationRequestValidatorNamespacePrefix")
public class ResourceRegistrationRequestValidatorNamespacePrefix implements ResourceRegistrationRequestValidator {
    // Repositories
    @Autowired
    private NamespaceRepository namespaceRepository;

    @Override
    public boolean validate(ServiceRequestRegisterResourcePayload request) throws ResourceRegistrationRequestValidatorException {
        // TODO I may need to re-think this method, to have a way of sending back multiple error messages
        // Check that the parameter is not null
        if (request.getNamespacePrefix() == null) {
            throw new ResourceRegistrationRequestValidatorException("MISSING Namespace Prefix");
        }
        // TODO - Check the namespace prefix corresponds to a valid active namespace
        String errorMessage;
        String shortErrorMessage;
        try {
            Namespace foundNamespace = namespaceRepository.findByPrefix(request.getNamespacePrefix());
            if (foundNamespace != null) {
                if (foundNamespace.isDeprecated()) {
                    shortErrorMessage = String.format("Prefix '%s' is deprecated", request.getNamespacePrefix());
                    errorMessage = shortErrorMessage;
                    log.error(errorMessage);
                    throw new ResourceRegistrationRequestValidatorException(shortErrorMessage);
                }
                // Valid, just let it flow to the default 'true' return
            } else {
                // It doesn't even exist
                errorMessage = String.format("Prefix '%s' IS NOT REGISTERED", request.getNamespacePrefix());
                shortErrorMessage = errorMessage;
                log.error(errorMessage);
                throw new ResourceRegistrationRequestValidatorException(shortErrorMessage);
            }
        } catch (RuntimeException e) {
            errorMessage = String.format("While validating prefix '%s', the following error occurred: '%s'",
                    request.getNamespacePrefix(), e.getMessage());
            shortErrorMessage = e.getMessage();
            log.error(errorMessage);
            throw new ResourceRegistrationRequestValidatorException(shortErrorMessage);
        }
        return true;
    }
}
