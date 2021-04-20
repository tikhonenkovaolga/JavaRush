package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file1 = new FileInputStream(reader.readLine());
        FileOutputStream file2 = new FileOutputStream(reader.readLine());
        byte[] bytesFile1 = new byte[file1.available()];

        while (file1.available() > 0){
           int count = file1.read(bytesFile1);
        }
        file1.close();
        String s = new  String(bytesFile1);
        String[] str = s.split(" ");
        long[] longs = new long[str.length];
        for (int i = 0; i < str.length; i++){
            longs[i] = Math.round(Double.parseDouble(str[i]));
        }
        for (int i = 0; i < str.length; i++){
            str[i] = String.valueOf(longs[i]);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length; i++){
            builder.append(str[i] + " ");
        }
        System.out.println(builder.toString());
        file2.write(builder.toString().getBytes());
        file2.close();
    }


    }

