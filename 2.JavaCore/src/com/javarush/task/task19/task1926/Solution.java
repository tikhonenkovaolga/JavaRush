package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
        reader.close();
        FileReader file  = new FileReader(nameFile);
        BufferedReader buf = new BufferedReader(file);
        String line = "";
        ArrayList<String> fileList = new ArrayList<>();
        while ((line = buf.readLine()) != null){
           fileList.add(line);

        }
        file.close();

        for (int i = 0; i < fileList.size(); i++){
            String lineReverse = new StringBuffer(fileList.get(i)).reverse().toString();
            System.out.println(lineReverse);
            lineReverse = "";
            }



        }


}
