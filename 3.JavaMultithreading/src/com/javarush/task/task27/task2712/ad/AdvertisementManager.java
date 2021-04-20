package com.javarush.task.task27.task2712.ad;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
import java.util.logging.Logger;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    Logger logger = Logger.getLogger(AdvertisementManager.class.getName());
    private List<Advertisement> listVideos = new ArrayList<>();
    private List<Advertisement> sortList = storage.list();
    private Map<Integer, List<Advertisement>> variants = new HashMap<>();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {

        if (sortList.isEmpty()) {
            throw new NoVideoAvailableException();

        }


        Collections.sort(sortList, (o1, o2) -> (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying()));


       variants(sortList);
       chooseMax(variants);
       chooseBestList(variants);

        if (listVideos.isEmpty()) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        } else {
            StatisticManager.getInstance().register(new VideoSelectedEventDataRow(listVideos, (long) calcPrice(listVideos), calcTime(listVideos)));
        }

        Collections.sort(listVideos, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
            }
        }.thenComparing((o1, o2) -> (int) (o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration() - o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration())));


        for (Advertisement a : listVideos) {
            ConsoleHelper.writeMessage(a.getName() + " is displaying... " + a.getAmountPerOneDisplaying() + ", " + a.getAmountPerOneDisplaying() * 1000 / a.getDuration());

            a.revalidate();
        }


    }

    private int calcTime(List<Advertisement> list) {
        int sumTime = 0;
        for (Advertisement a : list) {
            sumTime += a.getDuration();
        }
        return sumTime;
    }

    private int calcPrice(List<Advertisement> list) {
        int sumPrice = 0;
        for (Advertisement a : list) {
            sumPrice += a.getAmountPerOneDisplaying();
        }
        return sumPrice;
    }



    public void variants(List<Advertisement> list) {

        int key = 0;
        for (int i = 0; i < list.size()-1; i++) {
            List<Advertisement> listForMap = new ArrayList<>();
            listForMap.add(list.get(i));
            if (list.get(i).getDuration() > timeSeconds){
                listForMap.remove(list.get(i));
            }
            if (list.get(i).getHits() <= 0){
                listForMap.remove(list.get(i));
            }
            for (int j = i + 1; j < list.size(); j++) {
                listForMap.add(list.get(j));
                if (calcTime(listForMap) > timeSeconds) {
                    listForMap.remove(list.get(j));

                }
                if (list.get(j).getHits() <= 0){
                    listForMap.remove(list.get(j));
                }

            }
            variants.put(key, listForMap);
            key += 1;
        }


    }

    private int maxPrice = 0;
    private int maxTime = 0;
    private int minSize = Integer.MAX_VALUE;

    public void chooseMax(Map<Integer, List<Advertisement>> map) {

        //максимальная стоимость
        for (Map.Entry<Integer, List<Advertisement>> entry : map.entrySet()) {

            if (calcPrice(entry.getValue()) > maxPrice) {
                maxPrice = calcPrice(entry.getValue());
            }
        }

        //максимальное время
        for (Map.Entry<Integer, List<Advertisement>> entry : map.entrySet()) {
            if (calcTime(entry.getValue()) > maxTime) {
                maxTime = calcTime(entry.getValue());
            }
        }

        //минимальное количество роликов
        for (Map.Entry<Integer, List<Advertisement>> entry : map.entrySet()) {
            if (entry.getValue().size() < minSize) {
                minSize = entry.getValue().size();
            }
        }

    }

    private List<Advertisement> bestList = new ArrayList<>();
    private List<Advertisement> tmp = new ArrayList<>();
    private List<Advertisement> tmp1 = new ArrayList<>();


    public List<Advertisement> chooseBestList(Map<Integer, List<Advertisement>> map){

        for (Map.Entry<Integer, List<Advertisement>> entry : map.entrySet()) {
            if (calcPrice(entry.getValue()) == maxPrice){
                listVideos = new ArrayList<>(entry.getValue());
                break;
            }
        }

        for (Map.Entry<Integer, List<Advertisement>> entry : map.entrySet()) {
            if (calcPrice(entry.getValue()) == maxPrice && calcTime(entry.getValue()) == maxTime) {
                listVideos = new ArrayList<>(entry.getValue());
                break;
            }
        }

        for (Map.Entry<Integer, List<Advertisement>> entry : map.entrySet()) {
            if (calcPrice(entry.getValue()) == maxPrice && calcTime(entry.getValue()) == maxTime && entry.getValue().size() == minSize) {
                listVideos = new ArrayList<>(entry.getValue());
                break;
            }
        }

         return listVideos;
    }




}
