package org.identifiers.cloud.hq.ws.registry.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

/**
 * Project: cloud-hq-ws-registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.models
 * Timestamp: 2020-02-26 13:09
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 * <p>
 * This model represents an "AMEND" event in the processing of a resource update request
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class ResourceUpdateSessionEventAmend extends ResourceUpdateSessionEvent {
    public ResourceUpdateSessionEventAmend() {
        this.setEventName("AMEND");
    }
}
