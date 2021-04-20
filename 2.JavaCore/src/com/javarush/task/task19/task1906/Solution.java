package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile1 = reader.readLine();
        String nameFile2 = reader.readLine();
        reader.close();
        ArrayList<Integer> listFile1 = new ArrayList<>();
        FileReader fileReader = new FileReader(nameFile1);

        while (fileReader.ready()) {
            listFile1.add(fileReader.read());
        }
        fileReader.close();
//        for (int a : listFile1){
//            System.out.println(a);
//        }
        FileWriter fileWriter = new FileWriter(nameFile2);
        for (int i = 1; i < listFile1.size(); i = i+2){
            fileWriter.write(listFile1.get(i));
        }
        fileWriter.close();

    }
}
