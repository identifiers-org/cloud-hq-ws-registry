package org.identifiers.cloud.hq.ws.registry.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.models
 * Timestamp: 2020-02-26 13:03
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 * <p>
 * This model represents the information around and acceptance action on a resource update request
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class ResourceUpdateSessionEventAccept extends ResourceUpdateSessionEvent {

    // Optional information on the acceptance of the request
    @Column(length = 2000)
    private String acceptanceReason;

    public ResourceUpdateSessionEventAccept() {
        super();
        this.setEventName("ACCEPT");
    }
}
