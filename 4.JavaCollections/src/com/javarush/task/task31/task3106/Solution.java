package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            list.add(args[i]);

        }
        Collections.sort(list);
        Vector files = new Vector(args.length);
        for (String file : list){
            files.add(new FileInputStream(file));
        }


            ZipInputStream zis = new ZipInputStream(new SequenceInputStream(files.elements()));
            FileOutputStream fos = new FileOutputStream(args[0]);
            try {
            while ((zis.getNextEntry()) != null) {
                byte[] bytes = new byte[1024*1024];
                int count = 0;
                while ((count = zis.read(bytes)) != -1) {
                    fos.write(bytes, 0, count);
                }
            }
        }
        catch (Exception e){

        }
        finally {
            zis.close();
            fos.close();
        }




    }
}
