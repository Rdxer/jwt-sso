package com.rdxer.jwtsso.server;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.model.Permission;
import com.rdxer.jwtsso.model.Role;
import com.rdxer.jwtsso.repository.AccountRepository;
import com.rdxer.lib.exception.exceptions.NotAcceptableException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServerTest {

    @Resource
    AccountServer accountServer;
    @Resource
    AccountRepository repository;

    @Resource
    RoleServer roleServer;

    @Resource
    PermissionServer permissionServer;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
//    @Transactional
    public void initialUser() {
        var account = accountServer.findByName("lxf");
        if (account == null) {
            account = accountServer.store(Account.builder().username("lxf").password("123456").build());
        }
        assertNotNull(account);
        account = accountServer.findByName("lxf2");
        if (account == null) {
            account = accountServer.store(Account.builder().username("lxf2").password("123456789").build());
        }
        assertNotNull(account);

        var role = roleServer.findByName("SUPER_ADMIN");
        if (role == null) {
            role = roleServer.store(Role.builder().name("SUPER_ADMIN").build());
        }
        assertNotNull(role);

        role = roleServer.findByName("ADMIN");
        if (role == null) {
            role = roleServer.store(Role.builder().name("ADMIN").build());
        }
        assertNotNull(role);

        role = roleServer.findByName("USER");
        if (role == null) {
            role = roleServer.store(Role.builder().name("USER").build());
        }
        assertNotNull(role);

        var read_user = permissionServer.findByName("READ_USER");
        if (read_user == null){
            read_user = permissionServer.store(Permission.builder().name("READ_USER").build());
        }
        assertNotNull(read_user);
    }

    @Test
    public void findByName() {
        var lxf = accountServer.findByName("lxf");

        assertNotNull(lxf);
    }


    @Test
    @org.springframework.transaction.annotation.Transactional
    public void setRole() {
        var lxf = accountServer.findByName("lxf");
        assertNotNull(lxf);

        var role = roleServer.findByName("USER");
        assertNotNull(role);

        lxf.getRoles().add(role);

//        Account update = accountServer.update(lxf);

        repository.saveAll(Arrays.asList(lxf));


        var lxf2 = accountServer.findByName("lxf");

        Set<Role> roles = lxf2.getRoles();

        assertTrue(roles.size() > 0);

        roles.forEach(v -> System.out.println(v.getName()));

    }

    @Test
    @Transactional
    public void showInfo() {
        var lxf = accountServer.findByName("lxf");
        assertTrue("没有 role ",lxf.getRoles().size() > 0);
    }
}