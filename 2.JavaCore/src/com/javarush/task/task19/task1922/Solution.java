package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
        reader.close();
        //ArrayList<String> allFile = new ArrayList<>();
        FileReader file = new FileReader(nameFile);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = "";
        String lineFile = "";
        int count = 0;
        while ((line = bufferedReader.readLine()) != null) {
            lineFile = line;
            //allFile.add(line);

            String[] arr = lineFile.split("\\s");
            for (int j = 0; j < arr.length; j++) {
                for (int i = 0; i < words.size(); i++) {
                    if (arr[j].trim().equals(words.get(i))) {
                        count++;
                    }
                }
            }

            if (count == 2) {
                System.out.println(lineFile);
            }
            count = 0;
            lineFile = "";
        }
        bufferedReader.close();


    }
}

