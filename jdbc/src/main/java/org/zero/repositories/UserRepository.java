package org.zero.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zero.base.repositories.BaseZeroRepository;
import org.zero.core.repositories.ZeroRepository;
import org.zero.model.User;

/**
 * Created by rfang on 2016/8/22.
 */
public interface UserRepository extends BaseZeroRepository<User> {

}
