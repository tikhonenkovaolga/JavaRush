package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {

        String romanStr = s.toUpperCase();
        int result = 0;
        char[] symbols = romanStr.toCharArray();
        int[] counts = new int[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            switch (symbols[i]) {

                case 'I':
                    counts[i] = 1;
                    break;
                case 'V':
                    counts[i] = 5;
                    break;
                case 'X':
                    counts[i] = 10;
                    break;
                case 'L':
                    counts[i] = 50;
                    break;
                case 'C':
                    counts[i] = 100;
                    break;
                case 'D':
                    counts[i] = 500;
                    break;
                case 'M':
                    counts[i] = 1000;
                    break;

            }
        }
        for (int i = 0; i < counts.length; i++) {
            try {
                if (counts[i] >= counts[i + 1]) {
                    result += counts[i];
                } else {
                    result += counts[i + 1] - (counts[i]);

                    i++;
                }
            }catch (Exception e){
                result += counts[counts.length - 1];
            }
        }


        return result;
    }
}
