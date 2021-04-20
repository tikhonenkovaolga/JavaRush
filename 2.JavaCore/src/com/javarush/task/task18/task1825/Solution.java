package com.javarush.task.task18.task1825;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String result = fileName.substring(0, fileName.lastIndexOf("."));
        //System.out.println(result);
        TreeMap<String, byte[]> treeMap = new TreeMap<>();


         while (!fileName.equals("end")) {
             FileInputStream file = new FileInputStream(fileName);
             byte[]bytes = new byte[file.available()];
             while (file.available() > 0){
                 int count = file.read(bytes);
             }
             file.close();
             treeMap.put(fileName, bytes);
             fileName = reader.readLine();
             if (fileName.equals("end")) {
                 break;
             }
         }

         FileOutputStream allFiles = new FileOutputStream(result);
        for (Map.Entry<String, byte[]> entry : treeMap.entrySet()){
            allFiles.write(entry.getValue());

        }
        allFiles.close();


    }
}
