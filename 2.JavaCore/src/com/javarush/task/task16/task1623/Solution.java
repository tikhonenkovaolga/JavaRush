package com.javarush.task.task16.task1623;

/* 
Рекурсивное создание нитей
*/

public class Solution {
    static int count = 15;
    static volatile int createdThreadCount;

    public static void main(String[] args) {
        System.out.println(new GenerateThread());
    }

    public static class GenerateThread extends Thread{
        public GenerateThread() {
            super(String.valueOf(++createdThreadCount));
            start();
        }

        @Override
        public void run() {

                //System.out.println(this);
                if (createdThreadCount < count) {
                   System.out.println(new GenerateThread());

                }
               // else return;

        }

        @Override
        public String toString() {
            return getName() + " created";
        }
    }
}
