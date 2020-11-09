package com.skibnev.testingframework;

import com.skibnev.testingframework.annotations.TestClass;
import com.skibnev.testingframework.core.Application;
import com.skibnev.testingframework.core.ApplicationContext;
import com.skibnev.testingframework.runners.TestRunner;
import com.skibnev.testingframework.runners.impl.StartRunner;
import com.skibnev.testingframework.runners.impl.TestRunnerImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        ApplicationContext context = Application.run("com.skibnev.testingframework", new HashMap<>(Collections.singletonMap(TestRunner.class, TestRunnerImpl.class)));
        Set<Class<?>> testClasses = context.getTypesByAnnotation(TestClass.class);
        List<?> instances = testClasses.stream().map(context::getObject).collect(Collectors.toList());
        //TestRunner testRunner = context.getObject(TestRunner.class);
        //instances.forEach(instance -> new Thread(context.getObject(StartRunner.class, (Class<?>)instance, (Class<?>) semaphore)).start());
        instances.forEach(instance -> {
            StartRunner startRunner = context.getObject(StartRunner.class);
            startRunner.setClazz(instance);
            new Thread(startRunner).start();
        });
        //instances.forEach(testRunner::runTests);
    }

}
