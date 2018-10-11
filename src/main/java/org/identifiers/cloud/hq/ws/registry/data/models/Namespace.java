package org.identifiers.cloud.hq.ws.registry.data.models;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;
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
public class Namespace {
    @Id private BigInteger id;
    private String name;
    private String description;
    private Timestamp created;
    private Timestamp modified;
}
