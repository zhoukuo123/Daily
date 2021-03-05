package com.zk.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator cal = new Calculator();
    @Test
    void add() {
        int result = cal.add(1, 2);
        System.out.println(result);
    }

    @Test
    void subtract() {
        int result = cal.subtract(1, 2);
        System.out.println(result);
    }

    @Test
    void multiply() {
        int result = cal.multiply(1, 2);
        System.out.println(result);
    }

    @Test
    void divide() {
        float result = cal.divide(1, 2);
        System.out.println(result);
    }
}