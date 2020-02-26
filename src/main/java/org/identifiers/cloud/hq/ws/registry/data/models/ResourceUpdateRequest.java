package org.identifiers.cloud.hq.ws.registry.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.models
 * Timestamp: 2020-02-26 10:19
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 * <p>
 * This is the persistence data model for update requests on resources (providers) in the registry.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ResourceUpdateRequest {
    @Id
    @GeneratedValue
    private long id;

    // Registry internal ID for the resource, it is mandatory to provide a reference to an existing resource, on which
    // an update is being requested
    @ManyToOne(optional = false)
    private Resource resource;

    // --- Updatable Provider details ---
    // Resource MIR ID, optional, as it may not be part of the update request
    private String providerMirId;

    // Home URL for the resource, optional, as it may not be part of the update request
    @Column(length = 2000)
    private String providerHomeUrl;

    // Name update for the provider, optional, as it may not be part of the update request
    private String providerName;

    // Description for the provider being updated, optional, as it may not be part of the update request
    @Column(length = 2000)
    private String providerDescription;

    // Location Associated with the provider being updated, it references a supported location in the registry, but the
    // attribute is optional as it may not be the subject of the request
    private String providerLocation;

    // Unique identifier for this provider within the namespace for provider selection when resolving compact
    // identifiers, optional, as it may not be part of the update request
    private String providerCode;

    // URL pattern for the provider being updated, optional, as it may not be part of the update request
    @Column(length = 2000)
    private String providerUrlPattern;

    // This is a sample LUI that is covered by the resource being registered, optional, as it may not be part of the update request
    private String sampleId;

    // --- Associated Institution ---
    // This is the name of the institution that owns the resource that's being updated, optional, as it may not be part of the update request
    private String institutionName;

    // Home URL for the institution that operates the resource being updated, optional, as it may not be part of the update request
    @Column(length = 2000)
    private String institutionHomeUrl;

    // A description related to the institution that operates the resource that's being updated, optional, as it may not be part of the update request
    @Column(length = 2000)
    private String institutionDescription;

    // Location of the institution that owns the resource that's being updated, optional, as it may not be part of the update request
    private String institutionLocation;

    // ROR ID for this institution, optional, as it may not be part of the update request
    private String institutionRorId;

    // --- UPDATE REQUEST Details ---
    // It is not mandatory to provide additional information attached to the resource update request
    @Column(length = 4000)
    private String additionalInformation;

    // Information about the contact details for the requester is required
    @Column(nullable = false)
    private String requesterName;
    @Column(nullable = false)
    private String requesterEmail;

    // Other request action types
    private boolean actionTogglePrimaryFlag = false;
    private boolean actionDeprecateResource = false;
    private boolean actionContactPersonUpdate = false;

    // --- Additional Details on the request
    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date created;
}
