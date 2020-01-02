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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;
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
    RoleServer roleServer;
    @Resource
    PermissionServer permissionServer;
    @Resource
    PasswordEncoder bCryptPasswordEncoder;


    @Resource
    AccountRepository repository;

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
            account = accountServer.register(Account.builder().username("lxf").password("123456").build());
        }
        assertNotNull(account);
        account = accountServer.findByName("lxf2");
        if (account == null) {
            account = accountServer.register(Account.builder().username("lxf2").password("123456789").build());
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
        if (read_user == null) {
            read_user = permissionServer.store(Permission.builder().name("READ_USER").build());
        }
        assertNotNull(read_user);
    }

    @Test
    public void addUser() {
        var account = accountServer.store(Account.builder().username("lxf100").password("123456").build());

        assertNotNull(account);
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
        assertTrue("没有 role ", lxf.getRoles().size() > 0);
    }


















    @Test
    @Transactional
    @Rollback(value = false)
    public void initialData() {
        initAccount();
        initRole();
        initPerms();

        check();

        setRoleToAccount();
    }

    private void setRoleToAccount() {
        Account lxf = accountServer.findByName("lxf");
        Role byName = roleServer.findByName(Role.NAME.SUPER_ADMIN.name());
        lxf.getRoles().add(byName);
        accountServer.update(lxf);
    }

    private void check() {
        List<Account> all = accountServer.getAll();
        assertEquals(2, all.size());
        var roles = roleServer.getAll();
        assertEquals(3, roles.size());
        List<Permission> permissionServerAll = permissionServer.getAll();
        assertEquals(3,permissionServerAll.size());
    }

    private void initPerms() {
        Permission permission = Permission.builder().name(Permission.NAME.USER_ALL.name()).build();
        permissionServer.store(permission);

        permission = Permission.builder().name(Permission.NAME.USER_READ.name()).build();
        permissionServer.store(permission);

        permission = Permission.builder().name(Permission.NAME.USER_WRITER.name()).build();
        permissionServer.store(permission);

    }

    private void initRole() {
        Role role = Role.builder().name(Role.NAME.SUPER_ADMIN.name()).build();
        roleServer.store(role);

        role = Role.builder().name(Role.NAME.ADMIN.name()).build();
        roleServer.store(role);

        role = Role.builder().name(Role.NAME.USER.name()).build();
        roleServer.store(role);
    }

    private void initAccount() {
        Account account = Account.builder()
                .username("lxf1")
                .password(bCryptPasswordEncoder.encode("123456"))
                .build();
        account = accountServer.store(account);


        account = Account.builder()
                .username("lxf2")
                .password(bCryptPasswordEncoder.encode("123456"))
                .build();

        account = accountServer.store(account);

    }
}