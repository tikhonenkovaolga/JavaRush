package com.javarush.task.task09.task0927;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        HashMap<String, Cat> map = new HashMap<>();
        map.put("Ray", new Cat("Ray"));
        map.put("Murz", new Cat("Murz"));
        map.put("John", new Cat("John"));
        map.put("Sansa", new Cat("Sansa"));
        map.put("Arya", new Cat("Arya"));
        map.put("Varis", new Cat("Varis"));
        map.put("Belyash", new Cat("Belyash"));
        map.put("Deny", new Cat("Deny"));
        map.put("Old", new Cat("Old"));
        map.put("Yang", new Cat("Yang"));

        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        HashSet<Cat> set = new HashSet<>(map.values());
        return set;
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
