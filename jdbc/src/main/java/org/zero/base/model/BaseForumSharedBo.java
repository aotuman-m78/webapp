package org.zero.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * Created by rfang on 2016/8/23.
 */
@Data @EqualsAndHashCode(callSuper = false)
@MappedSuperclass
abstract public class BaseForumSharedBo extends BaseForumBo {

    @NotBlank
    @Size(min = 1, max = 32)
    @Column(name = "NAME")
    private String name;

    @Size(min = 1, max = 64)
    @Column(name = "DESCRIPTION")
    private String description;
}
