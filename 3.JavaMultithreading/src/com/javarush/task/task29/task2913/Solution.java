package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        if (a < b) {

            for (int i = a; i < b; i++) {
                System.out.print( i + " ");
            }

        }

//        else if (a == b) {
//            return Integer.toString(a);
//        }

            if (a > b){
            for (int i = a; i > b; i--) {
                System.out.print(i + " ");
            }
        }
            return Integer.toString(b);
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        //System.out.println(numberA + " " + numberB);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}