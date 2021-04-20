package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;

import java.io.IOException;
import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        FileInputStream file = new FileInputStream(fileName);
        byte[] bytes = new byte[file.available()];
        int spaceCount = 0;
        int count = 0;
        while (file.available() > 0){
            count = file.read(bytes);
            }
        for (int b : bytes){
             if (b == 32) {
                 spaceCount++;
             }
    }
        file.close();
        double result;
        if (count == 0){
         result = 0;
        }
        result = (double)spaceCount/bytes.length*100;

        System.out.printf("%.2f", result);



    }
}
