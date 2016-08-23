package org.zero.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zero.base.model.BaseForumSharedBo;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rfang on 2016/8/22.
 */
@Data @EqualsAndHashCode
@Entity
@Table(
    name = "GROUP",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UK__GROUP$OWNER_ID__NAME",
            columnNames = { "OWNER_ID", "NAME" }
        )
    }
)
public class Group extends BaseForumSharedBo {

    @Valid
    @OneToMany
    @JoinTable(
        name = "USERS",
        joinColumns = { @JoinColumn(name = "GROUP_ID") },
        inverseJoinColumns = { @JoinColumn(name = "USER_ID") },
        uniqueConstraints = {
            @UniqueConstraint(
                name = "UK__USERS__USER",
                columnNames = { "USER_ID" }
            )
        },
        indexes = {
            @Index(
                name = "IX__USERS$GROUP_ID",
                columnList = "GROUP_ID"
            )
        }
    )
    @org.hibernate.annotations.ForeignKey(
        name = "FK__USERS__GROUP",
        inverseName = "FK__USERS__USER"
    )
    @OrderBy("POSITION")
    @Size(max = 255)
    private List<User> users = new ArrayList<>();
}
