package com.javarush.task.task22.task2211;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Charset windows1251 = Charset.forName("Windows-1251");
        Charset utf8 = Charset.forName("UTF-8");
        File file1 = new File(args[0]);
        File file2 = new File(args[1]);
        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);
        byte [] buffer = new byte[fis.available()];
        fis.read(buffer, 0, buffer.length);
        String s = new String(buffer, windows1251);
        buffer = s.getBytes(utf8);
        fos.write(buffer);
        fis.close();
        fos.close();
    }
}
