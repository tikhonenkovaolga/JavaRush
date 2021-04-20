package com.javarush.task.task07.task0702;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Массив из строчек в обратном порядке
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String[] array = new String[10];

        for (int i = 0; i < 8; i++) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            array[i] = reader.readLine();
        }
        //array[8] = null;
       // array[9] = null;

        for (int i = array.length-1; i >= 0; i--)
        {
            System.out.println(array[i]);
        }
    }
}