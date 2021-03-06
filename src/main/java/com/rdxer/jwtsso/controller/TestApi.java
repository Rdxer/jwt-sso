package com.rdxer.jwtsso.controller;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.model.Permission;
import com.rdxer.jwtsso.model.Role;
import com.rdxer.jwtsso.model.TestClassModel;
import com.rdxer.jwtsso.repository.AccountRepository;
import com.rdxer.jwtsso.server.AccountServer;
import com.rdxer.jwtsso.server.PermissionServer;
import com.rdxer.jwtsso.server.RoleServer;
import com.rdxer.jwtsso.server.TestClassModelServer;
import com.rdxer.lib.core.util.CRUDUtlis;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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
        var lxf2 = accountServer.findByName("lxf1");

        Set<Role> roles = lxf2.getRoles();

        roles.forEach(v -> System.out.println(v.getName()));
        lxf2.getPermissions().forEach(v -> System.out.println(v.getName()));

        return "show";
    }

    @GetMapping("/test3")
    Account test32() {
        var lxf = accountServer.findByName("lxf1");
        return lxf;
    }

    @PutMapping("/test3")
    Account test32PutMapping() {
        var lxf = accountServer.findByName("lxf2");

        Role super_admin = roleServer.findByName("SUPER_ADMIN");

        lxf.getRoles().add(super_admin);

        Account update = accountServer.update(lxf);

        return accountServer.findByName("lxf2");
    }

    @DeleteMapping("/test3")
    Account test323PutMapping() {
        var lxf = accountServer.findByName("lxf2");

        Role super_admin = roleServer.findByName("SUPER_ADMIN");

        lxf.getRoles().remove(super_admin);

        lxf.setPhone(Math.random()+"");

        Account update = accountServer.update(lxf);

        return accountServer.findByName("lxf2");
    }

    @PutMapping("/test4")
    Account test42PutMapping() {
        var lxf = accountServer.findByName("lxf2");

        Permission byName = permissionServer.findByName(Permission.NAME.USER_ALL.name());

        lxf.getPermissions().add(byName);

        Account update = accountServer.update(lxf);

        return accountServer.findByName("lxf2");
    }


    @PostMapping("/adduser")
    Account adduser() {
        Account acc = Account.builder().username("lxf111").password("123").build();
        accountServer.store(acc);
        return acc;
    }






    @GetMapping("/user/info")
    Object userInfo(@AuthenticationPrincipal UserDetails user){
        return accountServer.findByName(user.getUsername());
    }
    @GetMapping("/admin/info")
    Object adminInfo(@AuthenticationPrincipal UserDetails user){
        return accountServer.getAll();
    }
    @GetMapping("/super_admin/info")
    Object super_admin(@AuthenticationPrincipal UserDetails user){
        return "super_admin";
    }


    @GetMapping("/normal")
    @PreAuthorize("hasRole(\"USER\")")
    @PostFilter("filterObject.username != authentication.name")
    List<Account> normal(@AuthenticationPrincipal UserDetails user){
        return accountServer.getAll();
    }

}
