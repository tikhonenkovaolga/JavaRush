package com.javarush.task.task10.task1015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* 
Массив списков строк
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String>[] arrayOfStringList = createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList() throws IOException {
        ArrayList<String>[] listAll = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            ArrayList<String> list = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                String s = "poppp";
                list.add(s + i);
            }
            listAll[i] = list;
        }
        System.out.println(listAll);
        return listAll;
    }


    public static void printList(ArrayList<String>[] arrayOfStringList) {
        for (ArrayList<String> list : arrayOfStringList) {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}