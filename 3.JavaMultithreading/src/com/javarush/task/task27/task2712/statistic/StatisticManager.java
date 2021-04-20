package com.javarush.task.task27.task2712.statistic;


import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();


    public static StatisticManager getInstance() {
        if (instance == null) {
            instance = new StatisticManager();
        }
        return instance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public Map<String, Double> allAmountPerDay() {
        List<EventDataRow> videoList = new ArrayList<>();
        Map<String, Double> result = new TreeMap<>(Collections.reverseOrder());
        Map<EventType, List<EventDataRow>> map = statisticStorage.getStorage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (Map.Entry<EventType, List<EventDataRow>> entry : map.entrySet()) {
            if (entry.getKey() == EventType.SELECTED_VIDEOS) {
                videoList.addAll(entry.getValue());
            }
        }

        for (EventDataRow e : videoList) {
            VideoSelectedEventDataRow videoSelectedEventDataRow = (VideoSelectedEventDataRow) e;

            double amount =  videoSelectedEventDataRow.getAmount()/100.0;

            Date date = videoSelectedEventDataRow.getDate();

            String dateForm = dateFormat.format(date);

            if (!result.containsKey(dateForm)) {
                result.put(dateForm, amount);
            } else {

                result.put(dateForm, result.get(dateForm) + amount);
            }


        }
        return result;
    }

    public Map<String, Map<String, Integer>> allTimeCooking() {
        List<EventDataRow> cookList = new ArrayList<>();
        Map<String, Map<String, Integer>> cookTime = new TreeMap<>(Collections.reverseOrder());
        Map<EventType, List<EventDataRow>> map = statisticStorage.getStorage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (Map.Entry<EventType, List<EventDataRow>> entry : map.entrySet()) {
            if (entry.getKey() == EventType.COOKED_ORDER) {
                cookList.addAll(entry.getValue());
            }
        }

        for (EventDataRow e : cookList) {
            CookedOrderEventDataRow cookedOrderEventDataRow = (CookedOrderEventDataRow) e;
            int allTime = 0;
            Date date = cookedOrderEventDataRow.getDate();


            Map<String, Integer> nameMap = new TreeMap<>();

            for (EventDataRow e1 : cookList) {
                CookedOrderEventDataRow cookedOrderEventDataRow1 = (CookedOrderEventDataRow) e1;
                if (dateFormat.format(cookedOrderEventDataRow1.getDate()).equals(dateFormat.format(date))) {

                    if (!nameMap.containsKey(cookedOrderEventDataRow1.getCookName())) {
                        allTime = cookedOrderEventDataRow1.getTime()/60;
                        nameMap.put(cookedOrderEventDataRow1.getCookName(), allTime);
                        //cookList.remove(e1);
                    } else {
                        allTime = nameMap.get(cookedOrderEventDataRow1.getCookName()).intValue();

                        nameMap.put(cookedOrderEventDataRow1.getCookName(), allTime + cookedOrderEventDataRow1.getTime()/60);
                        //cookList.remove(e1);
                    }

                }


            }
            cookTime.put(dateFormat.format(date), nameMap);
            //cookList.remove(e);
        }

        return cookTime;
    }


    private class StatisticStorage {

        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType e : EventType.values()) {
                storage.put(e, new ArrayList<EventDataRow>());
            }


        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }


    }
}
