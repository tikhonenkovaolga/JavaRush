package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file = new FileInputStream(reader.readLine());
       byte[]bytes = new  byte[file.available()];
        while (file.available() > 0){
            int count = file.read(bytes);
        }
        file.close();
        String s = new String(bytes);

        String str[] = s.split("\n");
        //System.out.println(str[0]);

       for (int i = 0; i < str.length; i++){
           if (str[i].startsWith(args[0] + " ")){
               System.out.println(str[i]);
       }

        }

        }


    }

