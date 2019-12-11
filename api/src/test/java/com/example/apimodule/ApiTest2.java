package com.example.apimodule;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ApiTest2 {
    private static final String TAG = "ApiTest2";
    
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
        assertEquals(4, 2 + 2);
        System.out.println("Test1: ");
    }

    @Test
    public void Test2() {
        assertEquals(4, 2 + 2);
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
