package com.javarush.task.task08.task0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Омовение Рамы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String s1 = s.trim();

        char[] str = s1.toCharArray();
        str[0] = Character.toUpperCase(str[0]);
        for (int i = 0; i < str.length; i++){
            if (str[i] ==(' ')){
                str[i + 1] = Character.toUpperCase(str[i + 1]);
            }
        }
        String s2 = new String(str);
        System.out.println(s2);

    }
}
