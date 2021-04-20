package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream(""));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter result = new StringWriter();
        if (is == null) {
            return result;
        }

        BufferedInputStream bis = new BufferedInputStream(is);

        int i;
        while ((i = bis.read()) != -1) {
            result.write((char) i);
        }


        return result;
    }
}