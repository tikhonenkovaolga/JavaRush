package com.javarush.task.task07.task0705;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Один большой массив и два маленьких
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        int[] array = new int[20];

        for (int i = 0; i < 20; i++){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            array[i] = Integer.parseInt(reader.readLine());
        }

        int[] array1 = new int[10];
        int[] array2 = new int[10];

        for (int j = 0; j < 10; j++){
            array1[j] = array[j];
        }

        for (int k = 0; k < 10; k++){
            array2[k] = array[k + 10];
            System.out.println(array2[k]);
        }

    }
}
