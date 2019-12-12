package com.example.apimodule;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestFailure;
import junit.framework.TestResult;

import java.util.Enumeration;

import static org.junit.Assert.assertEquals;

public class TestResultExample extends TestResult {

    @Override
    public synchronized void addError(Test test, Throwable e) {
        super.addError(test, e);
    }

    @Override
    public synchronized void addFailure(Test test, AssertionFailedError e) {
        super.addFailure(test, e);
    }

    @Override
    public void startTest(Test test) {
        super.startTest(test);
    }

    @Override
    public synchronized Enumeration<TestFailure> errors() {
        return super.errors();
    }


    @org.junit.Test
    public void testCase()
    {
        assertEquals(4, 2 + 2);
    }

    @Override
    public synchronized Enumeration<TestFailure> failures() {
        return super.failures();
    }

    @Override
    protected void run(TestCase test) {
        super.run(test);
    }

    @Override
    public void endTest(Test test) {
        super.endTest(test);
    }

    @Override
    public synchronized void stop() {
        super.stop();
    }
}
