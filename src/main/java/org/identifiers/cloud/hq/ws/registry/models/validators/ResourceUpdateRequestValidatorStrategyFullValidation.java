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
    // Wire in the validators
    private ResourceUpdateRequestValidator validatorProviderId;
    private ResourceUpdateRequestValidator validatorProviderMirId;
    private ResourceUpdateRequestValidator validatorProviderHomeUrl;
    private ResourceUpdateRequestValidator validatorProviderName;
    private ResourceUpdateRequestValidator validatorProviderDescription;
    private ResourceUpdateRequestValidator validatorProviderLocation;
    private ResourceUpdateRequestValidator validatorProviderCode;
    private ResourceUpdateRequestValidator validatorProviderUrlPattern;
    private ResourceUpdateRequestValidator validatorSampleId;
    private ResourceUpdateRequestValidator validatorInstitutionName;
    private ResourceUpdateRequestValidator validatorInstitutionHomeUrl;
    private ResourceUpdateRequestValidator validatorInstitutionDescription;
    private ResourceUpdateRequestValidator validatorInstitutionLocation;
    private ResourceUpdateRequestValidator validatorInstitutionRorId;
    private ResourceUpdateRequestValidator validatorAdditionalInformation;
    private ResourceUpdateRequestValidator validatorRequester;
    private ResourceUpdateRequestValidator validatorActionTogllePrimaryFlag;
    private ResourceUpdateRequestValidator validatorActionDeprecateResource;
    private ResourceUpdateRequestValidator validatorContactPersonUpdate;

    @Override
    public List<ResourceUpdateRequestValidator> getValidationChain() {
        return null;
    }
}
