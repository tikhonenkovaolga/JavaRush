package com.javarush.task.task07.task0703;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Общение одиноких массивов
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String [] array1 = new String[10];
        Integer [] array2 = new Integer[10];

        for (int i = 0; i < 10; i++){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            array1[i] = reader.readLine();
        }

        for (int j = 0; j < 10; j++){
            array2[j] = array1[j].length();
            System.out.println(array2[j]);
        }

    }
}
