package com.javarush.task.task09.task0906;

/* 
Логирование стек-трейса
*/

public class Solution {
    public static void main(String[] args) {
        log("In main method");
    }

    public static void log(String s) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTraceElements[2];
        System.out.println(element.getClassName() + ": " + element.getMethodName() + ": " + s);
    }
}
//com.javarush.task.task09.task0906.Solution: main: In main method