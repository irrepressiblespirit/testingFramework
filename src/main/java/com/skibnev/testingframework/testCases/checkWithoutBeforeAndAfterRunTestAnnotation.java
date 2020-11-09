package com.skibnev.testingframework.testCases;
import com.skibnev.testingframework.annotations.RunTest;
import com.skibnev.testingframework.annotations.TestClass;

@TestClass
public class checkWithoutBeforeAndAfterRunTestAnnotation {
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
