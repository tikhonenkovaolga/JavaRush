package com.javarush.task.task06.task0612;

/* 
Калькулятор
*/

public class Calculator {
    public static int plus(int a, int b) {
        int sum = a + b;
        return sum;
    }

    public static int minus(int a, int b) {
        int diff = a - b;
        return diff;
    }

    public static int multiply(int a, int b) {
        int mult = a * b;
        return mult;
    }

    public static double division(int a, int b) {
        double div = (a * 1.0) /(b * 1.0);
        return div;
    }

    public static double percent(int a, int b) {
        double perc = ((a * 1.0) * (b * 1.0))/100.0;
        return perc;
    }

    public static void main(String[] args) {

        System.out.println(percent(100, 20));
        System.out.println(division(4, 2));

    }
}