package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws Exception {

        FileReader file = new FileReader(args[0]);
        BufferedReader buf = new BufferedReader(file);
        String line = "";
        String strFile = "";
        while ((line = buf.readLine()) != null) {
            strFile = line + " " + strFile;
        }
        buf.close();
        file.close();
        String[] arrFile = strFile.split("\\s");

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> counts = new ArrayList<>();

        for (int i = 0; i < arrFile.length; i = i + 2) {
            names.add(arrFile[i]);
        }
        for (int i = 1; i < arrFile.length; i = i + 2) {
            counts.add(Double.parseDouble(arrFile[i]));
        }

        TreeMap<String, Double> treeMap = new TreeMap<>();
        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {
                if (names.get(i).equals(names.get(j))) {
                    counts.set(i, counts.get(i) + counts.get(j));
                    names.remove(j);
                    counts.remove(j);

                }

            }

        }

        for (int i = 0; i < names.size(); i++){
            treeMap.put(names.get(i), counts.get(i));
        }


        for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }


        }
    }

