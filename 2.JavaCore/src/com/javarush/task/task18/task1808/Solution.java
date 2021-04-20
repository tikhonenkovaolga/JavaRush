package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;
import java.util.Arrays;


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        FileInputStream file1 = new FileInputStream(reader.readLine());
        FileOutputStream file2 = new FileOutputStream(reader.readLine());
        FileOutputStream file3 = new FileOutputStream(reader.readLine());

        byte[] buff = new byte[file1.available()];
        byte[] buff1 = new byte[file1.available()/2];
        byte[] buff2 = new byte[file1.available()/2 + 1];
        byte[] buff3 = new byte[file1.available()/2];

        while (file1.available() > 0) {
            int count = file1.read(buff);
            System.arraycopy(buff, 0, buff1, 0, count / 2);
            System.arraycopy(buff, 0, buff2, 0, count / 2 + count%2);
            System.arraycopy(buff, count/2 + count%2, buff3, 0, count/2);
            if (count%2 == 0){
                file2.write(buff1);
                file3.write(buff3);
            }
            else {
                file2.write(buff2);
                file3.write(buff3);
            }
        }
//        for (int i = 0; i < buff.length; i++){
//            System.out.println(buff[i]);
//        }
//        System.out.println("buff");
//        for (int i = 0; i < buff1.length; i++){
//            System.out.println(buff1[i]);
//        }
//        System.out.println("buff1");
//        for (int i = 0; i < buff2.length; i++){
//            System.out.println(buff2[i]);
//        }
//        System.out.println("buff2");
//        for (int i = 0; i < buff3.length; i++){
//            System.out.println(buff3[i]);
//        }
//        System.out.println("buff3");



        file1.close();
        file2.close();
        file3.close();
    }
}




