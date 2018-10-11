package org.identifiers.cloud.hq.ws.registry.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.data.models
 * Timestamp: 2018-10-11 11:55
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This data model holds just enough information about a location, used in the registry
 */
@Document
public class Location {
    @Id private BigInteger id;
    @Indexed private String countryCode;

    public BigInteger getId() {
        return id;
    }

    public Location setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Location setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }
}
