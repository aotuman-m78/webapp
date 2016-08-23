package org.zero.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.zero.base.repositories.BaseDatabaseTest;
import org.zero.model.User;

/**
 * Created by rfang on 2016/8/23.
 */
@ContextConfiguration({"classpath:data-test-context.xml"})
public class UserRepositoryTest extends BaseDatabaseTest {

    @Autowired private UserRepository rep;

    @Test
    public void test() {
        User user = new User();
        user.setName("user");
        user.setOwnerId(102L);
        user = rep.saveAndFlush(user);

        System.out.println(user.getId());
    }

}
