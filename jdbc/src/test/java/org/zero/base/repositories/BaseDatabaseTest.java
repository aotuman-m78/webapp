package org.zero.base.repositories;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rfang on 2016/8/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
abstract public class BaseDatabaseTest {
}
