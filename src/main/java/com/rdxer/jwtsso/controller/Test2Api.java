package com.rdxer.jwtsso.controller;

import com.rdxer.jwtsso.model.TestClassModel;
import com.rdxer.jwtsso.server.TestClassModelServer;
import com.rdxer.lib.core.base.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test11")
public class Test2Api extends BaseCrudController<TestClassModel,Long, TestClassModelServer> {

}
