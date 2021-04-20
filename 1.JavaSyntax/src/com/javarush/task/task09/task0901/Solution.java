package com.javarush.task.task09.task0901;

/* 
Возвращаем StackTrace
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        method1();
    }

    public static StackTraceElement[] method1() {
        method2();
        StackTraceElement[] stackTraceElement1 = Thread.currentThread().getStackTrace();

        return stackTraceElement1;
    }

    public static StackTraceElement[] method2() {
        method3();
        StackTraceElement[] stackTraceElement2 = Thread.currentThread().getStackTrace();

        return stackTraceElement2;
    }

    public static StackTraceElement[] method3() {
        method4();
        StackTraceElement[] stackTraceElement3 = Thread.currentThread().getStackTrace();

        return stackTraceElement3;
    }

    public static StackTraceElement[] method4() {
        method5();
        StackTraceElement[] stackTraceElement4 = Thread.currentThread().getStackTrace();

        return stackTraceElement4;
    }

    public static StackTraceElement[] method5() {
        StackTraceElement[] stackTraceElement5 = Thread.currentThread().getStackTrace();

        return stackTraceElement5;
    }
}
