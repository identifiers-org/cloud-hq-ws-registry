package org.identifiers.cloud.hq.ws.registry.api.models;

import lombok.extern.slf4j.Slf4j;
import org.identifiers.cloud.hq.ws.registry.models.validators.PrefixRegistrationRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.api.models
 * Timestamp: 2019-03-18 13:35
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Component
@Slf4j
public class ValidationApiModel {
    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorRequestedPrefix")
    private PrefixRegistrationRequestValidator prefixValidator;

    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorName")
    private PrefixRegistrationRequestValidator nameValidator;

    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorDescription")
    private PrefixRegistrationRequestValidator descriptionValidator;

    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorProviderHomeUrl")
    private PrefixRegistrationRequestValidator providerHomeUrlValidator;

    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorProviderName")
    private PrefixRegistrationRequestValidator providerNameValidator;

    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorProviderDescription")
    private PrefixRegistrationRequestValidator providerDescriptionValidator;

    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorProviderLocation")
    private PrefixRegistrationRequestValidator providerLocationValidator;

    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorProviderCode")
    private PrefixRegistrationRequestValidator providerCodeValidator;

    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorInstitutionName")
    private PrefixRegistrationRequestValidator institutionNameValidator;

    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorInstitutionName")
    private PrefixRegistrationRequestValidator institutionDescriptionValidator;

    @Autowired
    @Qualifier("PrefixRegistrationRequestValidatorInstitutionLocation")
    private PrefixRegistrationRequestValidator institutionLocationValidator;

}
