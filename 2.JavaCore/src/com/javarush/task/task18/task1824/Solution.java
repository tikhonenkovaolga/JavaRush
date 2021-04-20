package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";

        while (true) {
            try {
                fileName = reader.readLine();
                FileInputStream file = new FileInputStream(fileName);
                file.close();
            } catch (FileNotFoundException e) {
                System.out.println(fileName);
                break;
            }

        }
        reader.close();
    }

}