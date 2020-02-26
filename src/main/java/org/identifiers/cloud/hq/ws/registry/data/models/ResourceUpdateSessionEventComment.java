package org.identifiers.cloud.hq.ws.registry.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.models
 * Timestamp: 2020-02-26 13:12
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 * <p>
 * This data model represents a "COMMENT" event in the processing of a resource update request
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class ResourceUpdateSessionEventComment extends ResourceUpdateSessionEvent {
    // Comments collected in the event
    @Column(length = 2000)
    private String comment;

    public ResourceUpdateSessionEventComment() {
        super();
        this.setEventName("COMMENT");
    }
}
