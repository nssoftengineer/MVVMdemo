package com.example.apimodule;

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

public class AssertExample {
    private static final String TAG = "AssertExample";
    private int number;
    private String name, temp;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass: ");
    }

    @Before
    public void setUp() {
        System.out.println("setUp: ");
        number = 5;
        name = "Neeraj";
        temp = "";
    }

    @Test
    public void Test1() {
        assertEquals("Neeraj", name);
        assertFalse(number > 6);
        assertNotNull(temp);
        assertNull(null);
        assertTrue(number == 5);
        System.out.println("Test1: ");
    }

    @Test
    public void Test2() {
        assertEquals("Ravi", name);
        assertFalse(number > 5);
        assertNotNull(temp);
        assertNull(name);
        assertTrue(number == 6);
        System.out.println("Test2: ");
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
