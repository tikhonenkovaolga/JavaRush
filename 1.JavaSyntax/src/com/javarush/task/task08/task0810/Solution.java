package com.javarush.task.task08.task0810;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/* 
Время для 10 тысяч вызовов get
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getGetTimeInMs(fill(new ArrayList())));
        System.out.println(getGetTimeInMs(fill(new LinkedList())));
    }

    public static List fill(List list) {
        for (int i = 0; i < 10000; i++) {
            list.add(new Object());
        }
        return list;
    }

    public static long getGetTimeInMs(List list) {
       // Solution.fill(new ArrayList());
        //Solution.fill(new LinkedList());
        Date startTime = new Date();
        get10000(list);
        Date endTime = new Date();
        long timeInMs = endTime.getTime() - startTime.getTime();

        return timeInMs;

    }

    public static void get10000(List list) {
        if (list.isEmpty()) return;
        int x = list.size() / 2;

        for (int i = 0; i < 10000; i++) {
            list.get(x);
        }
    }
}

//Требования:
//        1. Программа должна выводить числа на экран.
//        2. Метод main должен вызывать метод getGetTimeInMs только два раза.
//        3. Метод main должен вызывать метод fill только два раза.
//        4. Метод fill(List list) должен вставлять 10 тысяч элементов в список.
//        5. Метод get10000(List list) должен производить 10 тысяч вызовов get для списка.
//        6. Метод getGetTimeInMs должен вызывать метод get10000(List list) только один раз.
//        7. Метод getGetTimeInMs должен вернуть время в миллисекундах, которое занимает 10 тысяч вызовов get для списка.
