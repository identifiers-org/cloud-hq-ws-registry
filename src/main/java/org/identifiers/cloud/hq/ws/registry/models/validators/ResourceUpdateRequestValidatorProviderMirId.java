package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestUpdateResourcePayload;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.validators
 * Timestamp: 2020-03-02 16:40
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
@Qualifier("ResourceUpdateRequestValidatorProviderMirId")
public class ResourceUpdateRequestValidatorProviderMirId implements ResourceUpdateRequestValidator {
    // TODO
    private static final String REGEX_PATTERN_MIR_ID = "^MIR:\\d{8,}$";

    // Including a MIR ID in the resource update request is optional.
    @Override
    public boolean validate(ServiceRequestUpdateResourcePayload request) throws ResourceUpdateRequestValidatorException {
        // We know the format of MIR IDs, so we can actually make sure it conforms to the format
        if (request.getProviderMirId() != null) {
            Pattern pattern = Pattern.compile(REGEX_PATTERN_MIR_ID);
            return pattern.matcher(request.getProviderMirId()).matches();
        }
        return true;
    }
}
