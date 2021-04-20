package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile1 = reader.readLine();
        String nameFile2 = reader.readLine();
        reader.close();

        BufferedReader buff = new BufferedReader(new FileReader(nameFile1));
        String line;
        String file = "";
        while ((line = buff.readLine()) != null) {
            file = file + line;
        }
        buff.close();
        System.out.println(file);
        file = file.replaceAll("[^a-zA-Z0-9\\\\d\\s[\\n]]", "");

        System.out.println(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile2));
        writer.write(file);
        writer.close();

    }

}
