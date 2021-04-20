package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        FileInputStream file1 = new FileInputStream(fileName1);
        FileInputStream file2 = new FileInputStream(fileName2);

        byte[] bytesFile1 = new byte[file1.available()];
        byte[] bytesFile2 = new byte[file2.available()];
        while (file1.available() > 0){
            int countFile1 = file1.read(bytesFile1);
        }
        file1.close();

        while (file2.available() > 0){
            int countFile2 = file2.read(bytesFile2);
        }
        file2.close();
        FileOutputStream file12 = new FileOutputStream(fileName1);
        file12.write(bytesFile2);
        file12.write(bytesFile1);
        file12.close();
    }
}
