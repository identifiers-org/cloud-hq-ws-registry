package org.identifiers.cloud.hq.ws.registry.api.controllers;

import org.identifiers.cloud.hq.ws.registry.api.models.ResourceManagementApiModel;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterResource;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterResourceSessionEvent;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterResourceValidate;
import org.identifiers.cloud.hq.ws.registry.api.responses.ServiceResponseRegisterResourceValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.api.controllers
 * Timestamp: 2019-07-25 12:11
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This API is intended for management of resources at a more complex level than just the REST repositories.
 */
@RestController
@RequestMapping("resourceManagementApi")
public class ResourceManagementApi {

    @Autowired
    private ResourceManagementApiModel model;


    // TODO --- Resource Registration Request Management ---
    // TODO Resource Registration Request
    @PostMapping(value = "/registerResource")
    public ResponseEntity<?> registerPrefix(@RequestBody ServiceRequestRegisterResource request) {
//        ServiceResponseRegisterResource response = model.registerResource(request);
//        return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO Resource Registration Session, amend
    @PostMapping(value = "/amendResourceRegistrationRequest/{sessionId}")
    public ResponseEntity<?> amendPrefixRegistrationRequest(@PathVariable long sessionId, @RequestBody ServiceRequestRegisterResourceSessionEvent request) {
//        ServiceResponseRegisterResource response = model.amendResourceRegistrationRequest(sessionId, request);
//        return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO Resource Registration Session, comment
    @PostMapping(value = "/commentResourceRegistrationRequest/{sessionId}")
    public ResponseEntity<?> commentPrefixRegistrationRequest(@PathVariable long sessionId, @RequestBody ServiceRequestRegisterResourceSessionEvent request) {
//        ServiceResponseRegisterResourceSessionEvent response = model.commentResourceRegistrationRequest(sessionId, request);
//        return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO Resource Registration Session, reject
    @PostMapping(value = "/rejectResourceRegistrationRequest/{sessionId}")
    public ResponseEntity<?> rejectPrefixRegistrationRequest(@PathVariable long sessionId, @RequestBody ServiceRequestRegisterResourceSessionEvent request) {
//        ServiceResponseRegisterResourceSessionEvent response = model.rejectResourceRegistrationRequest(sessionId, request);
//        return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO Resource Registration Session, accept
    @PostMapping(value = "/acceptResourceRegistrationRequest/{sessionId}")
    public ResponseEntity<?> acceptPrefixRegistrationRequest(@PathVariable long sessionId, @RequestBody ServiceRequestRegisterResourceSessionEvent request) {
//        ServiceResponseRegisterResourceSessionEvent response = model.acceptResourceRegistrationRequest(sessionId, request);
//        return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO --- Resource Registration Request Validation ---

    @PostMapping(value = "/validateProviderHomeUrl")
    public ResponseEntity<?> validateProviderHomeUrl(@RequestBody ServiceRequestRegisterResourceValidate request) {
        ServiceResponseRegisterResourceValidate response = model.validateProviderHomeUrl(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping(value = "/validateProviderName")
    public ResponseEntity<?> validateProviderName(@RequestBody ServiceRequestRegisterResourceValidate request) {
        ServiceResponseRegisterResourceValidate response = model.validateProviderName(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping(value = "/validateProviderDescription")
    public ResponseEntity<?> validateProviderDescription(@RequestBody ServiceRequestRegisterResourceValidate request) {
        ServiceResponseRegisterResourceValidate response = model.validateProviderDescription(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping(value = "/validateProviderLocation")
    public ResponseEntity<?> validateProviderLocation(@RequestBody ServiceRequestRegisterResourceValidate request) {
        ServiceResponseRegisterResourceValidate response = model.validateProviderLocation(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping(value = "/validateProviderCode")
    public ResponseEntity<?> validateProviderCode(@RequestBody ServiceRequestRegisterResourceValidate request) {
        ServiceResponseRegisterResourceValidate response = model.validateProviderCode(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping(value = "/validateInstitutionName")
    public ResponseEntity<?> validateInstitutionName(@RequestBody ServiceRequestRegisterResourceValidate request) {
        ServiceResponseRegisterResourceValidate response = model.validateInstitutionName(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping(value = "/validateInstitutionHomeUrl")
    public ResponseEntity<?> validateInstitutionHomeUrl(@RequestBody ServiceRequestRegisterResourceValidate request) {
        ServiceResponseRegisterResourceValidate response = model.validateInstitutionHomeUrl(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping(value = "/validateInstitutionDescription")
    public ResponseEntity<?> validateInstitutionDescription(@RequestBody ServiceRequestRegisterResourceValidate request) {
        ServiceResponseRegisterResourceValidate response = model.validateInstitutionDescription(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping(value = "/validateInstitutionLocation")
    public ResponseEntity<?> validateInstitutionLocation(@RequestBody ServiceRequestRegisterResourceValidate request) {
        ServiceResponseRegisterResourceValidate response = model.validateInstitutionLocation(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping(value = "/validateProviderUrlPattern")
    public ResponseEntity<?> validateProviderUrlPattern(@RequestBody ServiceRequestRegisterResourceValidate request) {
        // ServiceResponseRegisterResourceValidate response = model.validateProviderUrlPattern(request);
        // return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(value = "/validateSampleId")
    public ResponseEntity<?> validateSampleId(@RequestBody ServiceRequestRegisterResourceValidate request) {
        // ServiceResponseRegisterResourceValidate response = model.validateSampleId(request);
        // return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(value = "/validateAdditionalInformation")
    public ResponseEntity<?> validateAdditionalInformation(@RequestBody ServiceRequestRegisterResourceValidate request) {
        // ServiceResponseRegisterResourceValidate response = model.validateAdditionalInformation(request);
        // return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(value = "/validateRequester")
    public ResponseEntity<?> validateRequester(@RequestBody ServiceRequestRegisterResourceValidate request) {
        // ServiceResponseRegisterResourceValidate response = model.validateRequester(request);
        // return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(value = "/validateRequesterName")
    public ResponseEntity<?> validateRequesterName(@RequestBody ServiceRequestRegisterResourceValidate request) {
        // ServiceResponseRegisterResourceValidate response = model.validateRequesterName(request);
        // return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(value = "/validateRequesterEmail")
    public ResponseEntity<?> validateRequesterEmail(@RequestBody ServiceRequestRegisterResourceValidate request) {
        // ServiceResponseRegisterResourceValidate response = model.validateRequesterEmail(request);
        // return new ResponseEntity<>(response, response.getHttpStatus());
        return new ResponseEntity<>("WORK IN PROGRESS", HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO --- Resource Lifecycle Management
    // TODO
}
