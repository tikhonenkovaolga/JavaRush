package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file1 = new FileInputStream(reader.readLine());
        FileOutputStream file2 = new FileOutputStream(reader.readLine());
        byte[] buff = new byte[file1.available()];
        byte[] buffRev = new byte[file1.available()];
        while (file1.available() > 0){
            int count = file1.read(buff);

            for (int i = 0; i < count; i++){
                buffRev[i] = buff[count-1 - i];
            }
//            for (int i = 0; i < buffRev.length; i++){
//
//                System.out.println(buffRev[i]);
//            }

        }
        file1.close();
        file2.write(buffRev);
        file2.close();

    }
}
