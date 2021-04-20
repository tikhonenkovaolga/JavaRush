package com.javarush.task.task04.task0415;

/* 
Правило треугольника
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] triangle = new int[3];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < triangle.length; i++){
            triangle[i] = Integer.parseInt(reader.readLine());
        }

        int a = triangle[0];
        int b = triangle[1];
        int c = triangle[2];

        if (a == 0 | b == 0 | c == 0){
            System.out.println("Треугольник не существует.");
        } else if ((a + b) <= c | (a + c) <= b | (b + c) <=a){
            System.out.println("Треугольник не существует.");
        } else {
            System.out.println("Треугольник существует.");
        }
    }
}