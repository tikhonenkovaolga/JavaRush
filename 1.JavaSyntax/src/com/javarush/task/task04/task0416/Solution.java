package com.javarush.task.task04.task0416;

/* 
Переходим дорогу вслепую
*/

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double t = Double.parseDouble(reader.readLine());

        t = t%5;
        System.out.println(t);
        if (t >= 0.0 && t < 3.0){
            System.out.println("зелёный");
        }

        if (t >= 3.0 && t < 4.0) {
            System.out.println("жёлтый");
        }

        if (t >= 4.0 && t < 5.0) {
            System.out.println("красный");
        }

        if (t == 5) {
            System.out.println("зелёный");
        }

    }
}
