package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        String file = "";
        FileReader fileReader = new FileReader(fileName);
        while (fileReader.ready()){
            file = file + String.valueOf((char) fileReader.read());
        }
        fileReader.close();
        String[] words = file.split("\\s*(\\s|,|!|\\.)\\s*");
        int countOfWorld = 0;
        for (int i = 0; i < words.length; i++){
            if (words[i].equals("world")){
                countOfWorld++;
            }
        }
        System.out.println(countOfWorld);
    }
}
