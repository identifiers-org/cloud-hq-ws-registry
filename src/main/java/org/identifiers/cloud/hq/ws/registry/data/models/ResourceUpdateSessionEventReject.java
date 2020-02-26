package org.identifiers.cloud.hq.ws.registry.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.models
 * Timestamp: 2020-02-26 13:20
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 * <p>
 * This data model represents a "REJECT" event in a resource update request processing session
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class ResourceUpdateSessionEventReject extends ResourceUpdateSessionEvent {
    // Optional information regarding the rationale behind the rejection of the resource update request
    @Column(length = 2000)
    private String rejectionReason;

    public ResourceUpdateSessionEventReject() {
        super();
        this.setEventName("REJECT");
    }
}
