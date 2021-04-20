package com.javarush.task.task06.task0610;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Класс ConsoleReader
*/

public class ConsoleReader {
    public static String readString() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        return s;
    }

    public static int readInt() throws Exception {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        return a;

    }

    public static double readDouble() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double f = Double.parseDouble(reader.readLine());
        return f;

    }

    public static boolean readBoolean() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean b = Boolean.parseBoolean(reader.readLine());
        return b;

    }

    public static void main(String[] args) {

    }
}
