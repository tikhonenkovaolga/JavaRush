package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        SortedSet<Integer> sortedSet = new TreeSet<>();
        FileInputStream inputStream = new FileInputStream(fileName);
        while (inputStream.available() > 0){
            sortedSet.add(inputStream.read());
        }
        inputStream.close();

        for (Integer set : sortedSet ){
            System.out.print(set + " ");
        }
    }
}
