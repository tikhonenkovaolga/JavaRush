package com.javarush.task.task08.task0815;

import java.util.*;

/* 
Перепись населения
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Smith", "John");
        map.put("Fallon", "Paul");
        map.put("Mara", "Runy");
        map.put("Gaga", "Lady");
        map.put("Snow", "Johnny");
        map.put("Stark", "Sansa");
        map.put("Brown", "James");
        map.put("Sinatra", "Frank");
        map.put("Franco", "Jamess");
        map.put("Boyle", "Emma");
        return map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name) {
        int count2 = 0;
        for (String value : map.values()) {
            if (value.equals(name)) {
                count2++;
            }
        }
        return count2;

    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName) {



        int count1 = 0;
        for (String key : map.keySet()) {
            if (key.equals(lastName)) {
                count1++;
            }
        }
        return count1;
    }


    public static void main(String[] args) {
//        HashMap<String, String> map = createMap();
//        System.out.println(getCountTheSameFirstName(map, "Sansa"));
//        System.out.println(getCountTheSameLastName(map, "Snow"));

    }
}
