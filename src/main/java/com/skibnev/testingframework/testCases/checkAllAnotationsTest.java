package com.skibnev.testingframework.testCases;

import com.skibnev.testingframework.annotations.*;

import javax.annotation.PostConstruct;

@TestClass
public class checkAllAnotationsTest {

    @InjectProperty("message")
    private String someMessage;

    @BeforeCreateClass
    public void testBeforeCreateClass() {
        System.out.println("-----------Before create class which checks all annotations---------------------------");
    }

    @BeforeRunTest
    public void testBeforeRunTest() {
        System.out.println("Before Run test");
    }

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

    @PostConstruct
    public void testAfterCreateClass() {
        System.out.println("After create class i print message: " + someMessage + " and start tests");
    }

}
