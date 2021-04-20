package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile1 = reader.readLine();
        String nameFile2 = reader.readLine();
        reader.close();
        BufferedReader buff = new BufferedReader(new FileReader(nameFile1));
        String line;
        String file = "";
        while ((line = buff.readLine()) != null) {
            file = file + line;
        }
        buff.close();

        String[] arrFile_2 = file.split(" ");
        ArrayList<String> counts = new ArrayList<>();
        for (String s :
                arrFile_2) {
            try {
                Integer.parseInt(s);
                counts.add(s);
            } catch (NumberFormatException e) {
                //System.out.println("Wrong string " + s);
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile2));
        for (int i = 0; i < counts.size(); i++){
            writer.write(counts.get(i) + " ");
        }
        writer.close();


    }


}

