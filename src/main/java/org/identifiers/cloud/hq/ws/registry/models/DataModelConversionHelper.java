package org.identifiers.cloud.hq.ws.registry.models;

import org.identifiers.cloud.hq.ws.registry.data.models.*;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models
 * Timestamp: 2019-03-28 13:46
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This helper implements methods for converting between different data models
 */
public class DataModelConversionHelper {

    /**
     * Convert from the data model that represents a prefix registration requests, to a resource data model (provider)
     * @param prefixRegistrationRequest the source for the conversion
     * @return a resource built from the given prefix registration request
     */
    public static Resource getResourceFrom(PrefixRegistrationRequest prefixRegistrationRequest) {
        Resource resource = new Resource();
        Person requester =
                new Person()
                        .setFullName(prefixRegistrationRequest.getRequesterName())
                        .setEmail(prefixRegistrationRequest.getRequesterEmail());
        // Create and fill in the institution information
        resource.setInstitution(new Institution()
                .setName(prefixRegistrationRequest.getInstitutionName())
                .setDescription(prefixRegistrationRequest.getInstitutionDescription())
                .setHomeUrl(prefixRegistrationRequest.getInstitutionHomeUrl())
                .setLocation(new Location().setCountryCode(prefixRegistrationRequest.getInstitutionLocation()))
        );
        // Create and fill in the namespace information
        resource.setNamespace(new Namespace()
                .setPrefix(prefixRegistrationRequest.getRequestedPrefix())
                .setName(prefixRegistrationRequest.getName())
                .setPattern(prefixRegistrationRequest.getIdRegexPattern())
                .setDescription(prefixRegistrationRequest.getDescription())
                .setSampleId(prefixRegistrationRequest.getSampleId())
                .setNamespaceEmbeddedInLui(prefixRegistrationRequest.isNamespaceEmbeddedInLui())
                .setContactPerson(requester)
        );
        // Create and fill in the resource (provider) information
        resource.setLocation(new Location().setCountryCode(prefixRegistrationRequest.getProviderLocation()))
                .setContactPerson(requester)
                .setUrlPattern(prefixRegistrationRequest.getProviderUrlPattern())
                .setName(prefixRegistrationRequest.getProviderName())
                .setDescription(prefixRegistrationRequest.getProviderDescription())
                .setOfficial(true)
                .setProviderCode(prefixRegistrationRequest.getProviderCode())
                .setSampleId(prefixRegistrationRequest.getSampleId())
                .setResourceHomeUrl(prefixRegistrationRequest.getProviderHomeUrl())
                .setAuthHelpDescription(prefixRegistrationRequest.getAuthHelpDescription())
                .setAuthHelpUrl(prefixRegistrationRequest.getAuthHelpUrl())
                .setProtectedUrls(prefixRegistrationRequest.isProtectedUrls())
                .setRenderProtectedLanding(prefixRegistrationRequest.isRenderProtectedLanding());
        return resource;
    }

    /**
     * Convert a data model that represents a resource registration request, to a resource data model
     * @param resourceRegistrationRequest source model conversion
     * @return a resource representation of the given source model
     */
    public static Resource getResourceFrom(ResourceRegistrationRequest resourceRegistrationRequest) {
        Resource resource = new Resource();
        Person requester =
                new Person()
                        .setFullName(resourceRegistrationRequest.getRequesterName())
                        .setEmail(resourceRegistrationRequest.getRequesterEmail());
        // Create and fill in the institution information
        resource.setInstitution(new Institution()
                .setName(resourceRegistrationRequest.getInstitutionName())
                .setDescription(resourceRegistrationRequest.getInstitutionDescription())
                .setHomeUrl(resourceRegistrationRequest.getInstitutionHomeUrl())
                .setLocation(new Location().setCountryCode(resourceRegistrationRequest.getInstitutionLocation()))
        );
        // Create and fill in the resource (provider) information
        resource.setLocation(new Location().setCountryCode(resourceRegistrationRequest.getProviderLocation()))
                .setContactPerson(requester)
                .setUrlPattern(resourceRegistrationRequest.getProviderUrlPattern())
                .setName(resourceRegistrationRequest.getProviderName())
                .setDescription(resourceRegistrationRequest.getProviderDescription())
                .setProviderCode(resourceRegistrationRequest.getProviderCode())
                .setSampleId(resourceRegistrationRequest.getSampleId())
                .setResourceHomeUrl(resourceRegistrationRequest.getProviderHomeUrl())
                .setAuthHelpDescription(resourceRegistrationRequest.getAuthHelpDescription())
                .setAuthHelpUrl(resourceRegistrationRequest.getAuthHelpUrl())
                .setProtectedUrls(resourceRegistrationRequest.isProtectedUrls())
                .setRenderProtectedLanding(resourceRegistrationRequest.isRenderProtectedLanding());
        return resource;
    }
}
