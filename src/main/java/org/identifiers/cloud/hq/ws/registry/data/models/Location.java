package org.identifiers.cloud.hq.ws.registry.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

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
@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {@Index(name = "idx_country_name", columnList = "countryName")})
public class Location {

    // ISO 3166/MA Alpha-2 Country Codes
    // TODO Use an internationalization library on maven (com.neovisionaries.nv-i18n) in the setter for further checks.
    // Country codes are supposed to be unique, so I can use them as primary key for this entity
    // And it makes sense that we should not be able to modify a country code once it's in the system, because it comes
    // from the ISO that already has all of them
    @Id
    @Column(updatable = false)
    private String countryCode;

    @Column(nullable = false, updatable = false)
    private String countryName;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(countryCode, location.countryCode) && Objects.equals(countryName, location.countryName) && Objects.equals(created, location.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, countryName, created);
    }
}
