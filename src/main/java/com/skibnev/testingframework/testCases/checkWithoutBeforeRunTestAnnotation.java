package com.skibnev.testingframework.testCases;

import com.skibnev.testingframework.annotations.*;

import javax.annotation.PostConstruct;

@TestClass
public class checkWithoutBeforeRunTestAnnotation {

    @RunTest
    public void testStartFirstTest() {
        System.out.println("Firs test is done !!!");
    }

    @RunTest
    public void testStartSecondTest() {
        System.out.println("Second test is done !!!");
    }

    @AfterRunTest
    public void testAfterRunTest() {
        System.out.println("After Run test");
    }

    @RunTest
    public void testStartThirdTest() {System.out.println("Third test is done !!!");}
}
