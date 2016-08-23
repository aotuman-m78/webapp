package org.zero.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zero.core.model.BaseZeroDo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Created by rfang on 2016/8/23.
 */
@Data @EqualsAndHashCode(callSuper = true)
@MappedSuperclass
abstract public class BaseForumBo extends BaseZeroDo<Long> {

    @NotNull
    @Column(name = "OWNER_ID", updatable = false)
    protected Long ownerId;
}
