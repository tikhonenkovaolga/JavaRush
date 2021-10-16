package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        //testStrategy(new FileStorageStrategy(), 10);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new HashMapStorageStrategy(), 10000);


    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set <Long> Ids = new HashSet<>();
        for (String s: strings) {
            Ids.add(shortener.getId(s));
        }
        return Ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strings = new HashSet<>();
        for (Long l:keys) {
            strings.add(shortener.getString(l));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Set<String> testStrings = new HashSet<>();
        System.out.println(strategy.getClass().getSimpleName());
        for (int i = 0; i < elementsNumber; i++) {
            testStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Set<Long> ids = getIds(shortener, testStrings);


        Date beforeIdDate = new Date();
        getIds(shortener, testStrings);
        Date afterIdDate = new Date();
        System.out.println("Время работы метода getIds - " + (afterIdDate.getTime() - beforeIdDate.getTime()));

        Date beforeStringDate = new Date();
        Set<String> strings = getStrings(shortener, ids);
        Date afterStringDate = new Date();
        System.out.println("Время работы метода getStrings - " + (afterStringDate.getTime() - beforeStringDate.getTime()));

        if (testStrings.size() == strings.size()){
            System.out.println("Тест пройден.");
        }
        else System.out.println("Тест не пройден.");





    }



}
