package org.identifiers.cloud.hq.ws.registry.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Project: hq-registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.models
 * Timestamp: 2019-03-19 11:48
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class PrefixRegistrationSessionEventComment extends PrefixRegistrationSessionEvent {
    // This is the comment we want to add
    @Column(length = 2000)
    private String comment;

    public PrefixRegistrationSessionEventComment() {
        super();
        this.setEventName("COMMENT");
    }
}
