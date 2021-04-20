package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Yang", "Paul");
        map.put("Sweet", "Kate");
        map.put("Ollen", "Dan");
        map.put("Yang", "Man");
        map.put("Send", "Kate");
        map.put("Potter", "Harry");
        map.put("Old", "Paul");
        map.put("Lonne", "Bob");
        map.put("Hidden", "Steve");
        map.put("York", "Jim");
        return map;
    }

    public static void printPeopleList(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
