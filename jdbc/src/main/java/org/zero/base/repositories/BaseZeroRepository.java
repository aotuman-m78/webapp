package org.zero.base.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.zero.core.model.BaseZeroDo;
import org.zero.core.repositories.ZeroRepository;

/**
 * Created by rfang on 2016/8/22.
 */
@NoRepositoryBean
public interface BaseZeroRepository<T extends BaseZeroDo> extends ZeroRepository<T, Long> {
}
