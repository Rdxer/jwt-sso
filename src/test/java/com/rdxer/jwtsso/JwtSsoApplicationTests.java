package com.rdxer.jwtsso;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.model.Permission;
import com.rdxer.jwtsso.model.Role;
import com.rdxer.jwtsso.server.AccountServer;
import com.rdxer.jwtsso.server.PermissionServer;
import com.rdxer.jwtsso.server.RoleServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtSsoApplicationTests {

    @Resource
    AccountServer accountServer;
    @Resource
    RoleServer roleServer;
    @Resource
    PermissionServer permissionServer;
    @Resource
    PasswordEncoder bCryptPasswordEncoder;


    @Test
    public void contextLoads() {

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void initialData() {
        initAccount();
        initRole();
        initPerms();

        check();

        setRole();
    }

    private void setRole() {
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
