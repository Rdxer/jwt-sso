package com.rdxer.lib.core.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

public abstract class BaseCrudController<ModelT,IdT extends Serializable,ServerT extends CRUDExServiceInterface<ModelT,IdT>> {

    @Autowired
    protected ServerT service;

    @GetMapping("/")
    public List<ModelT> index() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ModelT show(@PathVariable("id") IdT id) {
        return service.show(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModelT save(@RequestBody ModelT t) {
        return service.store(t);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ModelT update(@PathVariable("id") IdT id, @RequestBody ModelT t) {
        return service.update(id,t);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") IdT id) {
        service.destroy(id);
    }
}