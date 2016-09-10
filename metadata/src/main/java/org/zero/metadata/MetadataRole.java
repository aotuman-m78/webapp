package org.zero.metadata;

import lombok.Data;
import org.zero.core.role.Role;
import org.zero.core.role.RoleRight;

import javax.persistence.*;

/**
 * Created by rfang on 2016/9/9.
 */
@Data
@Entity
@Table(
    name = "METADATA_ROLE",
    indexes = {
        @Index(
            //embedded database could not support '$' syntax
            name = "IX__METADATA_ROLE",
            columnList = "ROLE"
        )
    }
)
public class MetadataRole {

    @Id
    @GeneratedValue
    private Short id;

    @Column(name = "ROLE", length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "ROLE_RIGHT", length = 32, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleRight roleRight;

    @Column(name = "value", length = 16, nullable = true)
    private String value;
}
