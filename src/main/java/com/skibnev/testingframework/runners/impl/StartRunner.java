package com.skibnev.testingframework.runners.impl;

import com.skibnev.testingframework.annotations.InjectByType;
import com.skibnev.testingframework.runners.TestRunner;
import lombok.Setter;
import lombok.SneakyThrows;

public class StartRunner implements Runnable {

    @Setter
    private Object clazz;

    @InjectByType
    private TestRunner testRunner;

    @SneakyThrows
    @Override
    public void run() {
//        try {
//            semaphore.acquire();
//            testRunner.runTests(clazz);
//            semaphore.release();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        testRunner.runTests(clazz);

    }
}
