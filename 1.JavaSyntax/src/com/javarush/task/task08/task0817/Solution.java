package com.javarush.task.task08.task0817;

import java.util.*;

/* 
Нам повторы не нужны
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
        map.put("Franco", "James");
        map.put("Boyle", "Emma");
        return map;
    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {

        ArrayList<String> list = new ArrayList<>(map.values());
       for (int i = 0; i < list.size(); i++){
           for (int j = i + 1; j < list.size(); j++)  {
               if (list.get(i).equals(list.get(j))){
                   removeItemFromMapByValue(map, list.get(j));
               }
           }

       }
//        Iterator<HashMap.Entry<String, String>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            HashMap.Entry<String, String> pair = iterator.next();
//            String key = pair.getKey();
//            String value = pair.getValue();
//            System.out.println(key + " - " + value);
//        }

    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {
//       System.out.println(createMap());
//        removeTheFirstNameDuplicates(createMap());

    }
}
