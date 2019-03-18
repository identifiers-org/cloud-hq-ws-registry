package org.identifiers.cloud.hq.ws.registry.api.models;

import org.identifiers.cloud.hq.ws.registry.api.ApiCentral;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestValidate;
import org.identifiers.cloud.hq.ws.registry.api.responses.ServiceResponse;
import org.identifiers.cloud.hq.ws.registry.api.responses.ServiceResponseRegisterPrefixPayload;
import org.identifiers.cloud.hq.ws.registry.api.responses.ServiceResponseValidateRequest;
import org.identifiers.cloud.hq.ws.registry.models.validators.PrefixRegistrationRequestValidator;
import org.identifiers.cloud.hq.ws.registry.models.validators.PrefixRegistrationRequestValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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
public class PrefixRegistrationRequestValidationApiModel {
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

    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorProviderUrlPattern")
    private PrefixRegistrationRequestValidator providerUrlPatternValidator;

    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorCrossedSampleIdProviderUrlPattern")
    private PrefixRegistrationRequestValidator crossedSampleIdProviderUrlPatternValidator;

    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorCrossedIdRegexPatternAndSampleId")
    private PrefixRegistrationRequestValidator crossedIdRegexPatternAndSampleIdValidator;

    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorReferences")
    private PrefixRegistrationRequestValidator referencesValidator;

    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorAdditionalInformation")
    private PrefixRegistrationRequestValidator additionalInformationValidator;

    @Autowired
    @Qualifier("prefixRegistrationRequestValidatorRequester")
    private PrefixRegistrationRequestValidator requesterValidator;

    // -- Helpers --

    /**
     * Initialize a response with the default values and the given payload.
     * @param response response to initialize
     * @param payload payload to set in the response
     * @param <T> the type of payload
     */
    private <T> void initDefaultResponse(ServiceResponse<T> response, T payload) {
        response.setApiVersion(ApiCentral.apiVersion)
                .setHttpStatus(HttpStatus.OK);
        response.setPayload(payload);
    }

    private ServiceResponseValidateRequest doValidation(ServiceRequestValidate request,
                                                        PrefixRegistrationRequestValidator validator) {
        // TODO - Check API version information?
        ServiceResponseValidateRequest response = new ServiceResponseValidateRequest();
        initDefaultResponse(response, new ServiceResponseRegisterPrefixPayload());
        // Validate the request
        boolean isValidRequest = false;
        try {
            isValidRequest = validator.validate(request.getPayload());
        } catch (PrefixRegistrationRequestValidatorException e) {
            response.setErrorMessage(e.getMessage());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.getPayload().setComment("VALIDATION FAILED");
        }
        if (isValidRequest) {
            response.getPayload().setComment("VALIDATION OK");
        }
        return response;
    }

    // -- API --
    public ServiceResponseValidateRequest validateName(ServiceRequestValidate request) {
        return doValidation(request, nameValidator);
    }

    public ServiceResponseValidateRequest validateDescription(ServiceRequestValidate request) {
        return doValidation(request, descriptionValidator);
    }

    public ServiceResponseValidateRequest validateProviderHomeUrl(ServiceRequestValidate request) {
        return doValidation(request, providerHomeUrlValidator);
    }

    public ServiceResponseValidateRequest validateProviderName(ServiceRequestValidate request) {
        return doValidation(request, providerNameValidator);
    }

    public ServiceResponseValidateRequest validateProviderDescription(ServiceRequestValidate request) {
        return doValidation(request, providerDescriptionValidator);
    }

    public ServiceResponseValidateRequest validateLocation(ServiceRequestValidate request) {
        return doValidation(request, providerLocationValidator);
    }

    public ServiceResponseValidateRequest validateProviderCode(ServiceRequestValidate request) {
        return doValidation(request, providerLocationValidator);
    }

    public ServiceResponseValidateRequest validateInstitutionName(ServiceRequestValidate request) {
        return doValidation(request, institutionNameValidator);
    }

    public ServiceResponseValidateRequest validateInstitutionDescription(ServiceRequestValidate request) {
        return doValidation(request, institutionDescriptionValidator);
    }

    public ServiceResponseValidateRequest validateInstitutionLocation(ServiceRequestValidate request) {
        return doValidation(request, institutionLocationValidator);
    }

    public ServiceResponseValidateRequest validateRequestedPrefix(ServiceRequestValidate request) {
        return doValidation(request, prefixValidator);
    }

    public ServiceResponseValidateRequest validateProviderUrlPattern(ServiceRequestValidate request) {
        return doValidation(request, providerUrlPatternValidator);
    }

    public ServiceResponseValidateRequest validateSampleId(ServiceRequestValidate request) {
        return doValidation(request, crossedSampleIdProviderUrlPatternValidator);
    }

    public ServiceResponseValidateRequest validateIdRegexPattern(ServiceRequestValidate request) {
        return doValidation(request, crossedIdRegexPatternAndSampleIdValidator);
    }

    public ServiceResponseValidateRequest validateReferences(ServiceRequestValidate request) {
        return doValidation(request, referencesValidator);
    }

    public ServiceResponseValidateRequest validateAdditionalInformation(ServiceRequestValidate request) {
        return doValidation(request, additionalInformationValidator);
    }

    public ServiceResponseValidateRequest validateRequester(ServiceRequestValidate request) {
        return doValidation(request, requesterValidator);
    }
}