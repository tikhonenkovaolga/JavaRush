 package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(fileName);
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        while (inputStream.available() > 0){
            list.add(inputStream.read());
        }
        inputStream.close();
        for (int i = 0; i < list.size(); i++){
            for (int j = 1; j < list.size(); j++){
                if (list.get(i) == list.get(j)){
                    count = Collections.frequency(list, list.get(i));
                }
            }
            map.put(list.get(i), count);
        }
        int minValue = Collections.min(map.values());
        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()){
            if (entry.getValue() == minValue){
                System.out.print(entry.getKey() + " ");
            }
        }

    }
}
