package org.zero.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by rfang on 2016/8/22.
 */
@MappedSuperclass
abstract public class ZeroTimeStamp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Getter @Setter
    @Column(name = "UPDATED_AT")
    @Version
    private Timestamp updatedAt;

    @PrePersist
    protected  void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        if (updatedAt == null) {
            updatedAt = new Timestamp(System.currentTimeMillis());
        }
    }
}
