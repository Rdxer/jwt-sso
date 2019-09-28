package com.rdxer.lib.core.base;

import com.rdxer.lib.exception.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.io.Serializable;
import java.util.Optional;

public interface CRUDServiceInterface<T, ID extends Serializable> {

    @NotNull
    public JpaRepository<T,ID> getRepository();

    default T show(ID id){
        Optional<T> r = getRepository().findById(id);
        if (r.isEmpty()){
            throw new NotFoundException();
        }
        return r.get();
    }

    default void destroy(ID id){
        boolean b = getRepository().existsById(id);
        if (b == false){
            throw new NotFoundException();
        }
        getRepository().deleteById(id);
    }

    default T store(T model){
        return getRepository().saveAndFlush(model);
    }

    T update(ID id,T model);

    T updateOfPatch(ID id, T model);

    default List<T> getAll(){
        return getRepository().findAll();
    }
}

