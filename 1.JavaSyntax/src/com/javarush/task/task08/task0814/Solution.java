package com.javarush.task.task08.task0814;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/* 
Больше 10? Вы нам не подходите
*/

public class Solution {
    public static HashSet<Integer> createSet() {
        HashSet<Integer> set = new HashSet<>();
        //Random random = new Random();

//        for (int i = 0; i < 20; i++) {
//            set.add(random.nextInt(100));
//        }

        set.add(12);
        set.add(1);
        set.add(11);
        set.add(2);
        set.add(15);
        set.add(7);
        set.add(0);
        set.add(5);
        set.add(14);
        set.add(17);
        set.add(13);
        set.add(8);
        set.add(3);
        set.add(4);
        set.add(6);
        set.add(100);
        set.add(120);
        set.add(500);
        set.add(9);
        set.add(140);

    return set;
    }

    public static HashSet<Integer> removeAllNumbersGreaterThan10(HashSet<Integer> set) {

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            Integer count = iterator.next();
            if (count > 10){
             iterator.remove();
            }
        }
        return set;
    }

    public static void main(String[] args) {

    }
}
