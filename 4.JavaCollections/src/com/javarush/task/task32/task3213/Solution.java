package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null) {
            System.out.println("Строка пуста");
            return "";
        }

        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            String s = line;


            char[] arrS = s.toCharArray();
            for (int i = 0; i < arrS.length; i++) {

                sb = sb.append((char) (arrS[i] + key));
            }




        }
        return sb.toString();
    }

}

