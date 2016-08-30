package org.zero.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.zero.base.repositories.BaseDatabaseTest;
import org.zero.model.ZUser;

import java.sql.SQLException;

/**
 * Created by rfang on 2016/8/23.
 */
@ContextConfiguration({"classpath:data-test-context.xml"})
public class ZUserRepositoryTest extends BaseDatabaseTest {

    @Autowired private ZUserRepository rep;

    @Test
    public void test() throws SQLException, ClassNotFoundException {

        ZUser user = new ZUser();
        user.setName("use2rftt");
        user.setOwnerId(102L);
        user = rep.saveAndFlush(user);

        System.out.println(user.getId());
    }

    @Test
    public void testDeleteInCache() {
        ZUser user = rep.findByOwnerIdAndName(102L, "use2rf");
        rep.delete(user);
    }

}unless
