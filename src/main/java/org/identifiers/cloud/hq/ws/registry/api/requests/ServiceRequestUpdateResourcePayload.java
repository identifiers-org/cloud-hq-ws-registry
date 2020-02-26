package org.identifiers.cloud.hq.ws.registry.api.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.identifiers.cloud.hq.ws.registry.api.data.models.Requester;

import java.io.Serializable;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.api.requests
 * Timestamp: 2020-02-17 14:29
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * Data model for requests on updating resources
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceRequestUpdateResourcePayload implements Serializable {
    // Registry internal ID for the resource
    private long providerId;

    // --- Updatable Provider details ---
    // Resource MIR ID
    private String providerMirId;

    // Home URL for the resource
    private String providerHomeUrl;

    // Name update for the provider
    private String providerName;

    // Description for the provider being updated
    private String providerDescription;

    // Location Associated with the provider being updated
    private String providerLocation;

    // Unique identifier for this provider within the namespace for provider selection when resolving compact identifiers
    private String providerCode;

    // URL pattern for the provider being updated
    private String providerUrlPattern;

    // This is a sample LUI that is covered by the resource being registered
    private String sampleId;

    // --- Associated Institution ---
    // This is the name of the institution that owns the resource that's being updated
    private String institutionName;

    // Home URL for the institution that operates the resource being updated
    private String institutionHomeUrl;

    // A description related to the institution that operates the resource that's being updated
    private String institutionDescription;

    // Location of the institution that owns the resource that's being updated
    private String institutionLocation;

    // ROR ID for this institution
    private String institutionRorId;

    // --- UPDATE REQUEST Details ---
    private String additionalInformation;
    private Requester requester;
    // Other request action types
    private boolean actionTogglePrimaryFlag = false;
    private boolean actionDeprecateResource = false;
    private boolean actionContactPersonUpdate = false;
}
