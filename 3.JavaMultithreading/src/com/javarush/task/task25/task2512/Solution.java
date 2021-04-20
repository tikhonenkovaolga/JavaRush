package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        ArrayList<Throwable> list = new ArrayList<>();
        list.add(e);
//        for (Throwable a :list ) {
//            System.out.println(a);
//        }

        while (list.get(list.size()-1).getCause() != null){
            list.add(list.get(list.size()-1).getCause());
        }
        Collections.reverse(list);
        for (Throwable a :list ) {
            System.out.println(a);
        }

    }

    public static void main(String[] args) {
        new Solution().uncaughtException(new Thread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));

    }
}
