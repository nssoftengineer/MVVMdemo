package com.example.apimodule;

import com.example.apimodule.testmodel.TestStudent;

import junit.framework.TestResult;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        //Result result = JUnitCore.runClasses(ApiTest.class,ApiTest2.class);
        //Result result = JUnitCore.runClasses(TestResultExample.class);
        Result result = JUnitCore.runClasses(TestStudent.class);

        for(Failure failure:result.getFailures())
        {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
