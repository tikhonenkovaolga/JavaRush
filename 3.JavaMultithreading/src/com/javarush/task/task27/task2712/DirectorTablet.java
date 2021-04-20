package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.*;
import java.util.stream.Stream;

public class DirectorTablet {

    public void printAdvertisementProfit() {     //какую сумму заработали на рекламе, сгруппировать по дням
        Map<String, Double> listAllDates = StatisticManager.getInstance().allAmountPerDay();
        Double total = 0.0;
        for (Map.Entry<String, Double> entry : listAllDates.entrySet()) {

            ConsoleHelper.writeMessage(String.format("%s - %.2f", entry.getKey(), entry.getValue()).replaceAll(",", "."));
            total += entry.getValue();
        }


        if (total > 0) {

            ConsoleHelper.writeMessage(String.format("Total - %.2f", total).replaceAll(",", "."));
        }

    }

    public void printCookWorkloading() {         //рабочее время повара, сгруппировать по дням
        Map<String, Map<String, Integer>> mapAllCooking = StatisticManager.getInstance().allTimeCooking();

        for (Map.Entry<String, Map<String, Integer>> entry : mapAllCooking.entrySet()) {
            ConsoleHelper.writeMessage(entry.getKey());
            for (Map.Entry<String, Integer> entry1 : entry.getValue().entrySet()) {
                if (entry1.getValue() > 0) {
                    ConsoleHelper.writeMessage(entry1.getKey() + " - " + entry1.getValue() + " min");

                }

            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {
        //список активных роликов и оставшееся количество показов по каждому;
        List<Advertisement> active = StatisticAdvertisementManager.getInstance().active();

        active.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        for (Advertisement a : active) {
            ConsoleHelper.writeMessage(a.getName() + " - " + a.getHits());
        }

        ConsoleHelper.writeMessage("");

    }


    public void printArchivedVideoSet() {        //список неактивных роликов (с оставшемся количеством показов равным нулю)

        List<Advertisement> archive = StatisticAdvertisementManager.getInstance().archive();
        archive.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        for (Advertisement a : archive) {
            ConsoleHelper.writeMessage(a.getName());
        }


    }
}

