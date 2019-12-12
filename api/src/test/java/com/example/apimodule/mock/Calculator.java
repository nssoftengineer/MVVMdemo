package com.example.apimodule.mock;

public class Calculator {

    CalculatorService calculatorService;


    public Calculator(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public int addition(int x, int y) {
        return calculatorService.add(x,y);
    }

    public int divide(int x, int y) {
        return x / y;
    }

    public int multiplication(int x, int y) {
        return x * y;
    }

    public int minus(int x, int y) {
        return x - y;
    }
}
