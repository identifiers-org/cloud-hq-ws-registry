package org.identifiers.cloud.hq.ws.registry.api.data.helpers;

import org.identifiers.cloud.hq.ws.registry.api.data.models.Institution;
import org.identifiers.cloud.hq.ws.registry.api.data.models.Location;
import org.identifiers.cloud.hq.ws.registry.api.data.models.Namespace;
import org.identifiers.cloud.hq.ws.registry.api.data.models.Resource;
import org.identifiers.cloud.hq.ws.registry.api.requests.ServiceRequestRegisterPrefixPayload;
import org.identifiers.cloud.hq.ws.registry.data.models.PrefixRegistrationRequest;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.helpers
 * Timestamp: 2019-03-20 13:49
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This helper provides methods for transformations between api models and data models.
 */
public class ApiAndDataModelsHelper {
    public static PrefixRegistrationRequest getPrefixRegistrationRequest(ServiceRequestRegisterPrefixPayload sourceModel) {
        String references = "";
        if (sourceModel.getReferences() != null) {
            references = "".join(",", sourceModel.getReferences());
        }
        String additionalInformation = "--- No additional information provided ---";
        if (sourceModel.getAdditionalInformation() != null) {
            additionalInformation = sourceModel.getAdditionalInformation();
        }
        return new PrefixRegistrationRequest()
                .setName(sourceModel.getName())
                .setDescription(sourceModel.getDescription())
                .setProviderHomeUrl(sourceModel.getProviderHomeUrl())
                .setProviderName(sourceModel.getProviderName())
                .setProviderDescription(sourceModel.getProviderDescription())
                .setProviderLocation(sourceModel.getProviderLocation())
                .setProviderCode(sourceModel.getProviderCode())
                .setInstitutionName(sourceModel.getInstitutionName())
                .setInstitutionDescription(sourceModel.getInstitutionDescription())
                .setInstitutionLocation(sourceModel.getInstitutionLocation())
                .setInstitutionHomeUrl(sourceModel.getInstitutionHomeUrl())
                .setRequestedPrefix(sourceModel.getRequestedPrefix())
                .setProviderUrlPattern(sourceModel.getProviderUrlPattern())
                .setSampleId(sourceModel.getSampleId())
                .setIdRegexPattern(sourceModel.getIdRegexPattern())
                .setSupportingReferences(references)
                .setAdditionalInformation(additionalInformation)
                .setRequesterName(sourceModel.getRequester().getName())
                .setRequesterEmail(sourceModel.getRequester().getEmail());
    }

    // NOTE - I don't totally like to have two models with the same name, as it makes the coding more prone to
    // making mistakes, but, at the same time, this is the meaning of it, and that's why we have packages,
    // right?

    public static Location getLocationFrom(org.identifiers.cloud.hq.ws.registry.data.models.Location location) {
        return new Location()
                .setCountryCode(location.getCountryCode())
                .setCountryName(location.getCountryName());
    }

    public static Institution getInstitutionFrom(org.identifiers.cloud.hq.ws.registry.data.models.Institution institution) {
        return new Institution()
                .setId(institution.getId())
                .setName(institution.getName())
                .setDescription(institution.getDescription())
                .setLocation(getLocationFrom(institution.getLocation()));
    }

    public static Resource getResourceFrom(org.identifiers.cloud.hq.ws.registry.data.models.Resource resource) {
        return new Resource()
                .setId(resource.getId())
                .setMirId(resource.getMirId())
                .setUrlPattern(resource.getUrlPattern())
                .setName(resource.getName())
                .setDescription(resource.getDescription())
                .setOfficial(resource.isOfficial())
                .setProviderCode(resource.getProviderCode())
                .setSampleId(resource.getSampleId())
                .setResourceHomeUrl(resource.getResourceHomeUrl())
                .setInstitution(getInstitutionFrom(resource.getInstitution()))
                .setLocation(getLocationFrom(resource.getLocation()));
    }

    public static Namespace getNamespaceFrom(org.identifiers.cloud.hq.ws.registry.data.models.Namespace namespace) {
        return new Namespace()
                .setId(namespace.getId())
                .setMirId(namespace.getMirId())
                .setPrefix(namespace.getPrefix())
                .setName(namespace.getName())
                .setPattern(namespace.getPattern())
                .setDescription(namespace.getDescription())
                .setCreated(namespace.getCreated())
                .setModified(namespace.getModified())
                .setSampleId(namespace.getSampleId());
    }

}
