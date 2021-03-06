package org.zero.metadata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rfang on 2016/9/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
@ContextConfiguration({"classpath:metadata-context.xml"})
public class MetadataRoleServiceTest {

    @Autowired private MetadataRoleService service;

    @Test
    public void test() {
        service.showRoleAndRight();
    }
}
