package org.zero.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zero.base.model.BaseForumSharedBo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 * Created by rfang on 2016/8/22.
 */
@Data @EqualsAndHashCode
@Entity
@Table(
    name = "Z_USER",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UK__USER$OWNER_ID__NAME",
            columnNames = { "OWNER_ID", "NAME"}
        )
    }
)
public class ZUser extends BaseForumSharedBo {

    @Size(min = 13, max = 13)
    @Column(name = "TEL")
    private String tel;

    public static String buildCacheKey(ZUser user) {
        StringBuilder sb = new StringBuilder();
        return sb.append(user.getOwnerId()).append(":").append(user.getName()).toString();
    }
}
