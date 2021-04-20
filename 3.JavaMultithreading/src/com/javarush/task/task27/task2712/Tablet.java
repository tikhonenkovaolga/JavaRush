package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;

import java.io.IOException;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue queue;

    public Tablet(int number) {
        this.number = number;

    }

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    public Order createOrder() {
        Order order = null;
        try {
            order = new Order(this);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } finally {
            if (!order.isEmpty()) {
                queue.offer(order);
            }

            AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
            try {
                advertisementManager.processVideos();
            } catch (NoVideoAvailableException e) {
                StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime()));
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
        }

        return order;
    }

    @Override
    public String toString() {

        return "Tablet{" + "number=" + number + "}";
    }

    public void createTestOrder() {
        TestOrder testOrder = null;
        try {
           testOrder = new TestOrder(this);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } finally {
            if (!testOrder.isEmpty()) {
                queue.offer(testOrder);
            }

            AdvertisementManager advertisementManager = new AdvertisementManager(testOrder.getTotalCookingTime() * 60);
            try {
                advertisementManager.processVideos();
            } catch (NoVideoAvailableException e) {
                StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(testOrder.getTotalCookingTime()));
                logger.log(Level.INFO, "No video is available for the order " + testOrder);
            }
        }


    }






}
