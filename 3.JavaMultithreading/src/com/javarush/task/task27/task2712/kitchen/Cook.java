package com.javarush.task.task27.task2712.kitchen;


import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;


public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue queue;

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public void startCookingOrder(Order order) {
        busy = true;
        System.out.println("Start cooking - " + order);

        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
        setChanged();
        notifyObservers(order);
        try {

            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }


        busy = false;
    }

    @Override
    public void run() {
        Thread daemonThread = new Thread();
        try {

            while (true) {
                while (queue.isEmpty()) {
                    Thread.sleep(10);
                }

                if (!this.isBusy() && !queue.isEmpty()) {
                    Order order = (Order) queue.poll();
                    if (order != null){
                        this.startCookingOrder(order);
                    }

                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        daemonThread.setDaemon(true);
        daemonThread.start();

    }
}
