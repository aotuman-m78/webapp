package org.zero.metadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zero.core.role.Role;
import org.zero.core.role.RoleRight;

/**
 * Created by rfang on 2016/9/9.
 */
@Component
public class MetadataRoleService {

    @Autowired private MetadataRoleRepository rep;

    public void showRoleAndRight() {
        MetadataRole role = rep.findByRoleAndRoleRight(Role.Manager, RoleRight.supportDeletePost);
        System.out.println(role.getValue());
    }
}
