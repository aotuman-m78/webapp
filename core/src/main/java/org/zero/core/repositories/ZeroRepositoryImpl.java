package org.zero.core.repositories;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.zero.core.model.BaseZeroDo;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by rfang on 2016/8/22.
 */
public class ZeroRepositoryImpl<T extends BaseZeroDo, ID extends Serializable> extends SimpleJpaRepository<T,ID> implements ZeroRepository<T, ID> {

    protected EntityManager em;
    private JpaEntityInformation<T, ?> entityInformation;
    private SimpleJpaRepository<T, ID> target;

    public ZeroRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
        target = new SimpleJpaRepository<T, ID>(this.entityInformation, this.em);
    }

}
