package org.zero.metadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.zero.core.role.Role;
import org.zero.core.role.RoleRight;

/**
 * Created by rfang on 2016/9/9.
 */
@Transactional(readOnly = true)
public interface MetadataRoleRepository extends JpaRepository<MetadataRole, Short> {

    MetadataRole findByRoleAndRoleRight(Role role, RoleRight roleRight);
}
