package org.identifiers.cloud.hq.ws.registry.models.schemaorg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.schemaorg
 * Timestamp: 2019-08-19 09:51
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Organization extends SchemaOrgNode implements Serializable {
    // Node Attributes
    private String name;
    private String url;
    private ContactPoint contactPoint;

    // Set node type
    @Override
    public String delegateGetNodeType() {
        return "Organization";
    }
}
