package org.identifiers.cloud.hq.ws.registry.api.models;

import org.identifiers.cloud.hq.ws.registry.api.ApiCentral;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterResource;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterResourceSessionEvent;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterResourceValidate;
import org.identifiers.cloud.hq.ws.registry.api.responses.*;
import org.identifiers.cloud.hq.ws.registry.data.repositories.ResourceRegistrationRequestRepository;
import org.identifiers.cloud.hq.ws.registry.models.validators.ResourceRegistrationRequestValidator;
import org.identifiers.cloud.hq.ws.registry.models.validators.ResourceRegistrationRequestValidatorException;
import org.identifiers.cloud.hq.ws.registry.models.validators.ResourceRegistrationRequestValidatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.api.models
 * Timestamp: 2019-07-25 12:17
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * Main model for resource management api controller
 */
@Component
public class ResourceManagementApiModel {

    // --- Validators ---
    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorNamespacePrefix")
    private ResourceRegistrationRequestValidator namespacePrefixValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorProviderHomeUrl")
    private ResourceRegistrationRequestValidator providerHomeUrlValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorProviderName")
    private ResourceRegistrationRequestValidator providerNameValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorProviderDescription")
    private ResourceRegistrationRequestValidator providerDescriptionValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorProviderLocation")
    private ResourceRegistrationRequestValidator providerLocationValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorProviderCode")
    private ResourceRegistrationRequestValidator providerCodeValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorInstitutionName")
    private ResourceRegistrationRequestValidator institutionNameValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorInstitutionHomeUrl")
    private ResourceRegistrationRequestValidator institutionHomeUrlValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorInstitutionName")
    private ResourceRegistrationRequestValidator institutionDescriptionValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorInstitutionLocation")
    private ResourceRegistrationRequestValidator institutionLocationValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorProviderUrlPattern")
    private ResourceRegistrationRequestValidator providerUrlPatternValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorCrossedSampleIdProviderUrlPattern")
    private ResourceRegistrationRequestValidator crossedSampleIdProviderUrlPatternValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorAdditionalInformation")
    private ResourceRegistrationRequestValidator additionalInformationValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorRequester")
    private ResourceRegistrationRequestValidator requesterValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorRequesterName")
    private ResourceRegistrationRequestValidator requesterNameValidator;

    @Autowired
    @Qualifier("ResourceRegistrationRequestValidatorRequesterEmail")
    private ResourceRegistrationRequestValidator requesterEmailValidator;

    // Resource Registration Request validation strategy
    @Autowired
    private ResourceRegistrationRequestValidatorStrategy validatorStrategy;

    // Repositories
    @Autowired
    private ResourceRegistrationRequestRepository resourceRegistrationRequestRepository;

    // --- Helpers ---
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

    private ServiceResponseRegisterResource createRegisterResourceDefaultResponse() {
        ServiceResponseRegisterResource response = new ServiceResponseRegisterResource();
        initDefaultResponse(response, new ServiceResponseRegisterResourcePayload());
        return response;
    }

    private ServiceResponseRegisterResourceSessionEvent createRegisterResourceSessionEventDefaultResponse() {
        ServiceResponseRegisterResourceSessionEvent response = new ServiceResponseRegisterResourceSessionEvent();
        initDefaultResponse(response, new ServiceResponseRegisterResourceSessionEventPayload());
        return response;
    }

    private String getAdditionalInformationFrom(ServiceRequestRegisterResourceSessionEvent request) {
        if (request.getPayload().getAdditionalInformation() != null) {
            return request.getPayload().getAdditionalInformation();
        }
        return "No additional information specified";
    }

    private String getCommentFrom(ServiceRequestRegisterResourceSessionEvent request) {
        if (request.getPayload().getComment() != null) {
            return request.getPayload().getComment();
        }
        return "No comment provided";
    }

    private String getRejectionReason(ServiceRequestRegisterResourceSessionEvent request) {
        if (request.getPayload().getRejectionReason() != null) {
            return request.getPayload().getRejectionReason();
        }
        return "No rejection reason provided";
    }

    private String getAcceptanceReason(ServiceRequestRegisterResourceSessionEvent request) {
        if (request.getPayload().getAcceptanceReason() != null) {
            return request.getPayload().getAcceptanceReason();
        }
        return "No acceptance reason provided";
    }

    private ServiceResponseRegisterResourceValidate doValidation(ServiceRequestRegisterResourceValidate request,
                                                                 ResourceRegistrationRequestValidator validator) {
        // TODO - Check API version information?
        ServiceResponseRegisterResourceValidate response = new ServiceResponseRegisterResourceValidate();
        initDefaultResponse(response, new ServiceResponseRegisterResourceValidatePayload());
        // Validate the request
        boolean isValidRequest = false;
        try {
            isValidRequest = validator.validate(request.getPayload());
        } catch (ResourceRegistrationRequestValidatorException e) {
            response.setErrorMessage(e.getMessage());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.getPayload().setComment("VALIDATION FAILED");
        }
        if (isValidRequest) {
            response.getPayload().setComment("VALIDATION OK");
        }
        return response;
    }

    // --- API ---
    // Resource Registration API
    public ServiceResponseRegisterResource registerResource(ServiceRequestRegisterResource requestRegisterResource) {
        // TODO
        return null;
    }

    public ServiceResponseRegisterResourceSessionEvent amendResourceRegistrationRequest(long sessionId, ServiceRequestRegisterResourceSessionEvent request) {
        // TODO
        return null;
    }

    public ServiceResponseRegisterResourceSessionEvent commentResourceRegistrationRequest(long sessionId, ServiceRequestRegisterResourceSessionEvent request) {
        // TODO
        return null;
    }

    public ServiceResponseRegisterResourceSessionEvent rejectResourceRegistrationRequest(long sessionId, ServiceRequestRegisterResourceSessionEvent request) {
        // TODO
        return null;
    }

    public ServiceResponseRegisterResourceSessionEvent acceptResourceRegistrationRequest(long sessionId, ServiceRequestRegisterResourceSessionEvent request) {
        // TODO
        return null;
    }

    // Validation API
    public ServiceResponseRegisterResourceValidate validateProviderHomeUrl(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, providerHomeUrlValidator);
    }

    public ServiceResponseRegisterResourceValidate validateProviderName(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, providerNameValidator);
    }

    public ServiceResponseRegisterResourceValidate validateProviderDescription(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, providerDescriptionValidator);
    }

    public ServiceResponseRegisterResourceValidate validateProviderLocation(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, providerLocationValidator);
    }

    public ServiceResponseRegisterResourceValidate validateProviderCode(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, providerCodeValidator);
    }

    public ServiceResponseRegisterResourceValidate validateInstitutionName(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, institutionNameValidator);
    }

    public ServiceResponseRegisterResourceValidate validateInstitutionHomeUrl(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, institutionHomeUrlValidator);
    }

    public ServiceResponseRegisterResourceValidate validateInstitutionDescription(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, institutionDescriptionValidator);
    }

    public ServiceResponseRegisterResourceValidate validateInstitutionLocation(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, institutionLocationValidator);
    }

    public ServiceResponseRegisterResourceValidate validateProviderUrlPattern(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, providerUrlPatternValidator);
    }

    public ServiceResponseRegisterResourceValidate validateSampleId(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, crossedSampleIdProviderUrlPatternValidator);
    }

    public ServiceResponseRegisterResourceValidate validateAdditionalInformation(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, additionalInformationValidator);
    }

    public ServiceResponseRegisterResourceValidate validateRequester(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, requesterValidator);
    }

    public ServiceResponseRegisterResourceValidate validateRequesterName(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, requesterNameValidator);
    }

    public ServiceResponseRegisterResourceValidate validateRequesterEmail(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, requesterEmailValidator);
    }

    public ServiceResponseRegisterResourceValidate validateNamespacePrefix(ServiceRequestRegisterResourceValidate request) {
        return doValidation(request, namespacePrefixValidator);
    }
}
