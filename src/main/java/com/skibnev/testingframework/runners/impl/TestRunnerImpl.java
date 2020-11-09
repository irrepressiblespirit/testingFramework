package com.skibnev.testingframework.runners.impl;

import com.skibnev.testingframework.annotations.AfterRunTest;
import com.skibnev.testingframework.annotations.BeforeRunTest;
import com.skibnev.testingframework.annotations.RunTest;
import com.skibnev.testingframework.annotations.Singleton;
import com.skibnev.testingframework.runners.TestRunner;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Singleton
public class TestRunnerImpl implements TestRunner {

    @Override
    public synchronized void runTests(Object t) {
        getMethodsWithAnnotation(t, RunTest.class).forEach(methodWithRunAnnotation ->
        {
            invokeMethods(getMethodsWithAnnotation(t, BeforeRunTest.class), t);
            invokeMethods(Collections.singletonList(methodWithRunAnnotation), t);
            invokeMethods(getMethodsWithAnnotation(t, AfterRunTest.class), t);
        });
    }

    private CopyOnWriteArrayList<Method> getMethodsWithAnnotation(Object t, Class<? extends Annotation> annotation) {
        return new CopyOnWriteArrayList(Arrays.stream(t.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toList()));
    }

    private void invokeMethods(List<Method> methodsList, Object object) {
        methodsList.forEach(method -> {
            try {
                method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}
