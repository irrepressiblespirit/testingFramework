package com.skibnev.testingframework.testCases;

import com.skibnev.testingframework.annotations.*;

@TestClass
public class checkWithoutAfterRunTestAnnotation {

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

   @RunTest
    public void testStartThirdTest() {System.out.println("Third test is done !!!");}
}
