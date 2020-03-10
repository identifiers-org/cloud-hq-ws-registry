package org.identifiers.cloud.hq.ws.registry.models.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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
    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorProviderMirId")
    private ResourceUpdateRequestValidator validatorProviderMirId;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorProviderHomeUrl")
    private ResourceUpdateRequestValidator validatorProviderHomeUrl;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorProviderName")
    private ResourceUpdateRequestValidator validatorProviderName;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorProviderDescription")
    private ResourceUpdateRequestValidator validatorProviderDescription;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorProviderLocation")
    private ResourceUpdateRequestValidator validatorProviderLocation;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorProviderCode")
    private ResourceUpdateRequestValidator validatorProviderCode;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorProviderUrlPattern")
    private ResourceUpdateRequestValidator validatorProviderUrlPattern;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorSampleId")
    private ResourceUpdateRequestValidator validatorSampleId;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorInstitutionName")
    private ResourceUpdateRequestValidator validatorInstitutionName;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorInstitutionHomeUrl")
    private ResourceUpdateRequestValidator validatorInstitutionHomeUrl;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorInstitutionDescription")
    private ResourceUpdateRequestValidator validatorInstitutionDescription;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorInstitutionLocation")
    private ResourceUpdateRequestValidator validatorInstitutionLocation;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorInstitutionRorId")
    private ResourceUpdateRequestValidator validatorInstitutionRorId;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorAdditionalInformation")
    private ResourceUpdateRequestValidator validatorAdditionalInformation;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorRequester")
    private ResourceUpdateRequestValidator validatorRequester;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorActionTogglePrimaryFlag")
    private ResourceUpdateRequestValidator validatorActionTogglePrimaryFlag;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorActionDeprecateResource")
    private ResourceUpdateRequestValidator validatorActionDeprecateResource;

    @Autowired
    @Qualifier("ResourceUpdateRequestValidatorActionContactPersonUpdate")
    private ResourceUpdateRequestValidator validatorContactPersonUpdate;

    @Override
    public List<ResourceUpdateRequestValidator> getValidationChain() {
        return Arrays.asList(
                validatorProviderMirId,
                validatorProviderHomeUrl,
                validatorProviderName,
                validatorProviderDescription,
                validatorProviderLocation,
                validatorProviderCode,
                validatorProviderUrlPattern,
                validatorSampleId,
                validatorInstitutionName,
                validatorInstitutionHomeUrl,
                validatorInstitutionDescription,
                validatorInstitutionLocation,
                validatorInstitutionRorId,
                validatorAdditionalInformation,
                validatorRequester,
                validatorActionTogglePrimaryFlag,
                validatorActionDeprecateResource,
                validatorContactPersonUpdate
        );
    }
}
