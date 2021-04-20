package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance() {
        if (ourInstance == null){
            ourInstance = new StatisticAdvertisementManager();
        }
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public List<Advertisement> active(){
        List<Advertisement> allNotNullHits = new ArrayList<>();
        for (Advertisement a : storage.list()) {
            if (a.getHits() >= 1){
                allNotNullHits.add(a);
            }

        }
        return allNotNullHits;
    }

    public List<Advertisement> archive(){
        List<Advertisement> allNullHits = new ArrayList<>();
        for (Advertisement a : storage.list()) {
            if (a.getHits() <= 0){
                allNullHits.add(a);
            }

        }
        return allNullHits;
    }



}
