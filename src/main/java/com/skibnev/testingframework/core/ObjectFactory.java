package com.skibnev.testingframework.core;

import com.skibnev.testingframework.annotations.BeforeCreateClass;
import com.skibnev.testingframework.configurations.ObjectConfigurator;
import com.skibnev.testingframework.core.ApplicationContext;
import com.skibnev.testingframework.runners.impl.StartRunner;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectFactory {

    private final ApplicationContext context;
    private final List<ObjectConfigurator> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        beforeCreate(implClass);

        T t = create(implClass);

        configure(t);

        invokeInit(implClass, t);

        return t;
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    private <T> void beforeCreate(Class<T> implClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(BeforeCreateClass.class)) {
                method.invoke(create(implClass));
            }
        }
    }

    private <T> T create(Class<T> implClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
