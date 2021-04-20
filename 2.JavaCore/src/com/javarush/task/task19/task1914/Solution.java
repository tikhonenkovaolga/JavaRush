package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        String result = outputStream.toString();
        System.setOut(consoleStream);
        int res = 0;
        String[]arrResult = result.split(" ");
        if (arrResult[1].equals("+")){
            res = Integer.parseInt(arrResult[0]) + Integer.parseInt(arrResult[2]);
        }
        else if (arrResult[1].equals("-")){
            res = Integer.parseInt(arrResult[0]) - Integer.parseInt(arrResult[2]);
        }
        else if (arrResult[1].equals("*")){
            res = Integer.parseInt(arrResult[0]) * Integer.parseInt(arrResult[2]);
        }
                    result = result.replaceAll("\r\n", "");
            System.out.print(result + res);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

