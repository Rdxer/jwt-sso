package com.rdxer.jwtsso.controller;

import com.rdxer.jwtsso.model.TestClassModel;
import com.rdxer.jwtsso.server.TestClassModelServer;
import com.rdxer.lib.core.base.BaseCrudController;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test11")
public class Test2Api extends BaseCrudController<TestClassModel,Long, TestClassModelServer> {

}
