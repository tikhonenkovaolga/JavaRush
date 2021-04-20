package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException{
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        raf.seek(Long.parseLong(args[1]));
        byte[] bytes = new byte[args[2].length()];
        raf.read(bytes, 0, args[2].length());
        String textFile = new String(bytes);
        System.out.println(textFile);
        long endOfFile = raf.length();
        raf.seek(endOfFile);
        if (textFile.equals(args[2])){
           raf.write("true".getBytes());
        }
        else raf.write("false".getBytes());
        raf.close();
    }
}
