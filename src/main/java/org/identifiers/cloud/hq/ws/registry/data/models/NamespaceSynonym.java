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
 * Timestamp: 2018-10-11 12:08
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * Data model for namespace synonyms
 */
@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {@Index(name = "idx_synonym", columnList = "synonym", unique = true)})
public class NamespaceSynonym {

    @Id
    @GeneratedValue
    private long id;

    // This is the Synonym itself, obviously, it must be unique, otherwise we could not go from the synonym back to the
    // namespace
    @Column(nullable = false, unique = true)
    private String synonym;

    // The namespace this is a synonym of
    @ManyToOne(optional = false)
    private Namespace namespace;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamespaceSynonym that = (NamespaceSynonym) o;
        return id == that.id && Objects.equals(synonym, that.synonym) && Objects.equals(namespace, that.namespace) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, synonym, namespace, created);
    }
}
