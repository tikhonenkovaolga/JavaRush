package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Smith", 2000);
        map.put("French", 500);
        map.put("Rian", 200);
        map.put("Born", 1500);
        map.put("Korn", 250);
        map.put("Troy", 3500);
        map.put("Dorn", 1000);
        map.put("Veter", 600);
        map.put("Pain", 2000);
        map.put("Case", 400);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            HashMap.Entry<String, Integer> pair = iterator.next();
            String key = pair.getKey();
            Integer value = pair.getValue();
            if (value < 500){
                iterator.remove();
            }
        }
//        Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
//        while (iter.hasNext()){
//            HashMap.Entry<String, Integer> pair = iter.next();
//            String key = pair.getKey();
//            Integer value = pair.getValue();
//            System.out.println(key + " " + value);
//            }

    }

    public static void main(String[] args) {
       // removeItemFromMap(createMap());

    }
}