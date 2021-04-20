package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileReader file1 = new FileReader(args[0]);
        BufferedReader buf = new BufferedReader(file1);
        String line = "";
        String result = "";
        while ((line = buf.readLine()) != null) {
            String[] arrLine = line.split("\\s");
            for (int i = 0; i < arrLine.length; i++) {
                if (arrLine[i].trim().length() > 6) {
                    result = result + arrLine[i] + ",";
                }
            }

        }
        buf.close();
        file1.close();
        result = result.substring(0, result.length() - 1);
        FileWriter file2 = new FileWriter(args[1]);
        file2.write(result);
        file2.close();


    }
}
