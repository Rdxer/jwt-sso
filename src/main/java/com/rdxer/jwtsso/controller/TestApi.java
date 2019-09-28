package com.rdxer.jwtsso.controller;

import com.rdxer.jwtsso.model.TestClassModel;
import com.rdxer.jwtsso.server.AccountServer;
import com.rdxer.jwtsso.server.TestClassModelServer;
import com.rdxer.lib.core.util.CRUDUtlis;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


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


}
