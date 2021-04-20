package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader(args[0]);
        BufferedReader buf = new BufferedReader(file);
        FileWriter fileWriter = new FileWriter(args[1]);
        String line = "";
        while ((line = buf.readLine()) != null) {
            String[]arrLine = line.split("\\s");
            for (int i = 0; i < arrLine.length; i++) {
                for (int j = 0; j < arrLine[i].length(); j++){
                    if (Character.isDigit(arrLine[i].charAt(j))) {
                        fileWriter.write(arrLine[i] + " ");
                        arrLine[i] = "";
                }

                }

            }
        }
            file.close();
            buf.close();

            fileWriter.close();


        }
    }

