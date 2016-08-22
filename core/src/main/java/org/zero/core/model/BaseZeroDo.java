package org.zero.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by rfang on 2016/8/22.
 */
@Data @EqualsAndHashCode(callSuper = false, of = {})
abstract public class BaseZeroDo<PK extends Serializable> extends ZeroTimeStamp {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private PK id;
}
