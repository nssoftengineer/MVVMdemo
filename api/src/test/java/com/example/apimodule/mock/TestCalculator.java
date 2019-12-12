package com.example.apimodule.mock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.example.apimodule.testmodel.Student.DISTINCTION;
import static com.example.apimodule.testmodel.Student.FAIL;
import static com.example.apimodule.testmodel.Student.FIRST_CLASS_PASS;
import static com.example.apimodule.testmodel.Student.PASS;
import static com.example.apimodule.testmodel.Student.SECOND_CLASS_PASS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class TestCalculator {

    private static final String TAG = "TestStudent";
    private Calculator calculator;

    private CalculatorService calculatorService = Mockito.mock(CalculatorService.class);

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass: ");
    }

    @Before
    public void setUp() {
        System.out.println("setUp:.........................................................");
        calculator = new Calculator(calculatorService);
    }

    @Test
    public void TestPass() {
        System.out.println("TestPass Method");
        when(calculatorService.add(5,10)).thenReturn(15);
        assertEquals(15, calculator.addition(5, 10));
        verify(calculatorService).add(5,10);
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
