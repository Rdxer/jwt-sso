package com.rdxer.jwtsso.server.impl;

import com.rdxer.jwtsso.model.TestClassModel;
import com.rdxer.jwtsso.server.TestClassModelServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClassModelServerImplTest {

    @Resource
    TestClassModelServer testClassModelServer;

    @Test
    public void store() {
        TestClassModel model = new TestClassModel();

        model.setUsername("123");

        testClassModelServer.store(model);

        assertNotNull(model.getId());
    }

    @Test
    public void getAll() {
        List<TestClassModel> all = testClassModelServer.getAll();
        assertTrue(all.size() > 0);
    }

    @Test
    public void show() {
        assertTrue(testClassModelServer.show(4L) != null);
    }

    @Test
    public void destroy() {
        testClassModelServer.destroy(4L);
        assertTrue(testClassModelServer.existsById(4L) == false);
    }
}
