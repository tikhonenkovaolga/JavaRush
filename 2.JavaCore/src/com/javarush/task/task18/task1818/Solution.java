package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream file1 = new FileOutputStream(reader.readLine());
        FileInputStream file2 = new FileInputStream(reader.readLine());
        FileInputStream file3 = new FileInputStream(reader.readLine());
        byte[] bytesFile2 = new byte[file2.available()];
        byte[] bytesFile3 = new byte[file3.available()];
        int countFile2;
        int countFile3;
        while (file2.available() > 0){
            countFile2 = file2.read(bytesFile2);
            file1.write(bytesFile2);
        }
        file2.close();
        while (file3.available() > 0){
            countFile3 = file3.read(bytesFile3);
            file1.write(bytesFile3);
        }
        file3.close();
        file1.close();

    }

}
