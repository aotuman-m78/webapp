package org.zero.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.zero.base.repositories.BaseDatabaseTest;
import org.zero.model.ZUser;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rfang on 2016/8/23.
 */
@ContextConfiguration({"classpath:data-test-context.xml"})
public class ZUserRepositoryTest extends BaseDatabaseTest implements ApplicationContextAware, InitializingBean {

    @Autowired private ZUserRepository rep;
    private ThreadLocal<Map<String, Object>> local = new ThreadLocal<>();

    private ApplicationContext context;

    {
        System.out.println(rep);
    }

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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Test
    public void testAutowired() {
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        Map<String, Object> map = new HashMap<>();
        local.set(map);
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(rep);
    }

    @PostConstruct
    public void init() {
        System.out.println(rep);
    }
}
