package com.rdxer.jwtsso.controller;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.model.Role;
import com.rdxer.jwtsso.model.TestClassModel;
import com.rdxer.jwtsso.repository.AccountRepository;
import com.rdxer.jwtsso.server.AccountServer;
import com.rdxer.jwtsso.server.PermissionServer;
import com.rdxer.jwtsso.server.RoleServer;
import com.rdxer.jwtsso.server.TestClassModelServer;
import com.rdxer.lib.core.util.CRUDUtlis;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;


@RestController
public class TestApi {

    @Resource
    TestClassModelServer testClassModelServer;

    @GetMapping("/test/{id}")
    Object index2(@PathVariable Long id) {
        TestClassModel show = testClassModelServer.show(id);
        return show;
    }
    @PostMapping("/test/{id}")
    Object index3(@PathVariable Long id,@RequestParam Long pid) {
        TestClassModel show = testClassModelServer.show(id);
        CRUDUtlis.setId(pid,show);
        return CRUDUtlis.getId(show);
    }


    @Resource
    AccountServer accountServer;
    @Resource
    AccountRepository repository;
    @Resource
    RoleServer roleServer;
    @Resource
    PermissionServer permissionServer;

    @PostMapping("/test3")
    Object test3() {

        // 创建


        // 修改
        var lxf = accountServer.findByName("lxf");

        var role = roleServer.findByName("USER");

        lxf.getRoles().add(role);

        accountServer.update(lxf);

        var perms = permissionServer.show(2L);
        lxf.getPermissions().add(perms);

        accountServer.update(lxf);

        // 读取
        var lxf2 = accountServer.findByName("lxf");

        Set<Role> roles = lxf2.getRoles();

        roles.forEach(v -> System.out.println(v.getName()));
        lxf2.getPermissions().forEach(v -> System.out.println(v.getName()));

        return "show";
    }

    @GetMapping("/test3")
    Object test32() {
        var lxf = accountServer.findByName("lxf");
        return lxf;
    }

    @PutMapping("/test3")
    Object test32PutMapping() {
        var lxf = accountServer.findByName("lxf");

        lxf.getPermissions().removeIf(v -> v.getName().equals("READ_USER"));

        Account update = accountServer.update(lxf);

        return accountServer.findByName("lxf");
    }

}
