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
 * Timestamp: 2020-02-26 12:44
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * Root of the data model tree that represents the events that are part of a resource update request session.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
public class ResourceUpdateSessionEvent {

    @Id
    @GeneratedValue
    private long id;

    // This part is interpreted mainly on the client side
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String eventName;

    // This attribute should contain information on who triggered / created a particular event (it will usually be the
    // 'curator' that's working on the associated prefix registration session)
    @Column(nullable = false)
    private String actor;

    // Additional information on this event is useful, but optional
    private String additionalInformation;

    // Link to the resource update session this event belongs to
    @ManyToOne(optional = false)
    private ResourceUpdateSession resourceUpdateSession;

    // Resource update request that may be associated with a specialised instance of this event
    @ManyToOne
    private ResourceUpdateRequest resourceUpdateRequest;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date created;
}
