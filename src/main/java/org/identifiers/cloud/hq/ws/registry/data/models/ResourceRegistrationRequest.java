package org.identifiers.cloud.hq.ws.registry.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.models
 * Timestamp: 2019-07-29 00:58
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ResourceRegistrationRequest {
    @Id
    @GeneratedValue
    private long id;

    // Namespace 'prefix' where this resource is being registered
    @Column(nullable = false)
    private String namespacePrefix;

    // Home URL for the provider being registered
    @Column(nullable = false, length = 2000)
    private String providerHomeUrl;

    // Name for the provider being registered
    @Column(nullable = false)
    private String providerName;

    // Description for the provider being registered
    @Column(nullable = false, length = 2000)
    private String providerDescription;

    // Location Associated with the provider being registered
    @Column(nullable = false)
    private String providerLocation;

    // Unique identifier for this provider within the namespace for provider selection when resolving compact identifiers
    @Column(nullable = false)
    private String providerCode;

    // This is the name of the institution that owns the resource that's being registered
    @Column(nullable = false)
    private String institutionName;

    // A description related to the institution that owns the resource that's being registered
    @Column(nullable = false, length = 2000)
    private String institutionDescription;

    // Home URL for the institution (this is a new requirement)
    @Column(nullable = false, length = 2000)
    private String institutionHomeUrl;

    // Location of the institution that owns the resource that's being registered
    @Column(nullable = false)
    private String institutionLocation;

    // ROR ID for this institution
    @Column(length = 2000)
    private String institutionRorId;

    // Originally called 'resourceAccessRule', ported from the original identifiers.org form at https://identifiers.org/request/prefix
    @Column(nullable = false, length = 2000)
    private String providerUrlPattern;

    // Originally called 'exampleIdentifier', ported from the original identifiers.org form at https://identifiers.org/request/prefix
    @Column(nullable = false)
    private String sampleId;

    // Additional information to be included as part of the request, ported from the original identifiers.org form at
    // https://identifiers.org/request/prefix
    // Optional
    @Column(length = 2000)
    private String additionalInformation;

    // Contact person for this request, ported from the original identifiers.org form at
    // https://identifiers.org/request/prefix
    @Column(nullable = false)
    private String requesterName;

    // Contact person for this request, ported from the original identifiers.org form at
    // https://identifiers.org/request/prefix
    @Column(nullable = false)
    private String requesterEmail;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date created;
}
