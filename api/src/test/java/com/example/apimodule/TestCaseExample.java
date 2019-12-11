package com.example.apimodule;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestCaseExample extends TestCase {
    private static final String TAG = "AssertExample";

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass: ");
    }

    @Before
    public void setUp() {
        System.out.println("setUp: ");
    }


   @Test
    public void Test1() {

        System.out.println("test case count "+this.countTestCases());

        System.out.println("test case name "+this.getName());

        this.setName("Neeraj");

        System.out.println("test case name after change "+this.getName());
        assertEquals(4, 2 + 2);
    }


    @After
    public void tearDown() {
        System.out.println("tearDown: ");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("tearDownClass: ");
    }
}
