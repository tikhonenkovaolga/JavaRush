package com.javarush.task.task07.task0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Минимаксы в массивах
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maximum = 0;
        int minimum = 0;
        int[] array = new int[] {1, 2, 2,2,3,4,5,6,7,8,9,90,56,3,34,655675};


        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(reader.readLine());
             maximum = array[0];
             minimum = array[0];
        }
        for (int i : array) {
            if (i > maximum) {
                maximum = i;
            }
            if (i < minimum) {
                minimum = i;
            }
        }

        System.out.print(maximum + " " + minimum);
    }
}
