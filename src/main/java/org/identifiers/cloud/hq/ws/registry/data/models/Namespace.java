package org.identifiers.cloud.hq.ws.registry.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.models
 * Timestamp: 2018-10-11 11:57
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This entity models a Prefix (or namespace) in the registry
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
// TODO - refactoring to relational
public class Namespace {
    private long id;
    private String prefix;
    private String mirId;
    private String name;
    private String pattern;
    private String description;
    private Timestamp created;
    private Timestamp modified;
    private boolean deprecated = false;
    private Timestamp deprecationDate;
    private String sampleId;
}
