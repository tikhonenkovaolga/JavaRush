package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream(args[0]);
        ArrayList<Character>list = new ArrayList<>();
        TreeMap<Character, Integer> map = new TreeMap<>();
        while (file.available() > 0){
            list.add((char)file.read());
        }
        file.close();

        Collections.sort(list);
//        ArrayList<Character>list1 = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++){
//            list1.add((Character) list.get(i));
//        }
        int count = 0;
        for (int i = 0; i < list.size(); i++){
            for (int j = 0; j < list.size(); j++){
                if (list.get(i) == list.get(j)){
                    count = Collections.frequency(list, list.get(i));
                }
            }
            map.put(list.get(i), count);
        }
        for (HashMap.Entry<Character, Integer> entry : map.entrySet()){
                  System.out.println(entry.getKey() + " " + entry.getValue());

        }






    }
}
