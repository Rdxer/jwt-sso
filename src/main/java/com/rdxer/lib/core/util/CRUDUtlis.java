package com.rdxer.lib.core.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class CRUDUtlis {

    public static ConcurrentHashMap<Class<?>, Field> fieldCache = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Class<?>, Method> methodCache = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Class<?>, String> nameCache = new ConcurrentHashMap<>();

    public static <ID extends Serializable, T> ID getId(T model) {
        Class<?> aClass = model.getClass();
        Class<? extends Annotation> annotation = Id.class;

        String name = getIdName(model, aClass, annotation);

        BeanWrapper wrappedSource = new BeanWrapperImpl(model);

        return (ID) wrappedSource.getPropertyValue(name);
    }

    public static <ID extends Serializable, T> void setId(ID id, T model) {
        Class<?> aClass = model.getClass();
        Class<? extends Annotation> annotation = Id.class;

        String name = getIdName(model, aClass, annotation);

        BeanWrapper wrappedSource = new BeanWrapperImpl(model);

        wrappedSource.setPropertyValue(name,id);
    }


    private static <T> String getIdName(T model, Class<?> aClass, Class<? extends Annotation> annotation) {
        String name;

        if (nameCache.containsKey(model.getClass())) {
            return nameCache.get(model.getClass());
        }

        Field field = null;
        Method method = null;

        if (fieldCache.containsKey(model.getClass())) {
            field = fieldCache.get(model.getClass());
        }else if (methodCache.containsKey(model.getClass())){
            method = methodCache.get(model.getClass());
        }

        if (field == null){
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field f : declaredFields) {
                if (f.isAnnotationPresent(annotation)) {
                    field = f;
                    fieldCache.put(aClass,f);
                    break;
                }
            }
        }

        if (field == null && method == null) {
            Method[] rs = aClass.getDeclaredMethods();
            for (Method r : rs) {
                if (r.isAnnotationPresent(annotation)) {
                    method = r;
                    methodCache.put(aClass,r);
                    break;
                }
            }
        }

        if (field == null && method == null) {
            throw new RuntimeException("CRUDUtlis 找不到 id 字段");
        }

        if (field != null){
            name = field.getName();
        }else{
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                name = methodName.replaceFirst("get", "");
            }else if (methodName.startsWith("set")) {
                name = methodName.replaceFirst("set", "");
            }else{
                name = methodName;
            }
        }

        nameCache.put(aClass,name);

        return name;
    }


}
