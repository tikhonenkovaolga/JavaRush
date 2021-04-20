package com.javarush.task.task27.task2712;



import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;


public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        Cook cook1 = new Cook("One");
        Thread thread1 = new Thread(cook1);
        thread1.setDaemon(true);
        thread1.start();
        Cook cook2 = new Cook("Two");
        Thread thread2 = new Thread(cook2);
        thread2.setDaemon(true);
        thread2.start();
        cook1.setQueue(ORDER_QUEUE);
        cook2.setQueue(ORDER_QUEUE);

        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= 5; i++){
            Tablet tablet = new Tablet(i);
            tablet.setQueue(ORDER_QUEUE);
            tablets.add(tablet);
        }

       
        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));

        thread.start();
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }
}


