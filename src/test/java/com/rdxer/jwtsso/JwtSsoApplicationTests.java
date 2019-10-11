package com.rdxer.jwtsso;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.model.Role;
import com.rdxer.jwtsso.server.AccountServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtSsoApplicationTests {

    @Resource
    AccountServer accountServer;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRoles() {
        var lxf = accountServer.findByName("lxf");
        Set<Role> roles = lxf.getRoles();
        System.out.println(roles);
    }



}
