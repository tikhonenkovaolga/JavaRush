package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);
    static {
       Thread thread1 = new T1();
       threads.add(thread1);
       Thread thread2 = new T2();
       threads.add(thread2);
        Thread thread3 = new T3();
        threads.add(thread3);
        Thread thread4 = new T4();
        threads.add(thread4);
        Thread thread5 = new T5();
        threads.add(thread5);

    }

    public static void main(String[] args) throws InterruptedException{
       threads.get(4).start();

    }
    public static class T1 extends Thread{
        @Override
        public void run() {
            while (true) {
                super.run();
            }
        }
    }
    public static class T2 extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
        }
    }
    public static class T3 extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                   // e.printStackTrace();
                }

            }

        }
    }
    public static class T4 extends Thread implements Message{
     public boolean check = true;

        @Override
        public void run() {
            while (check) {

              //Thread.currentThread().interrupt();
            }
        }
        @Override
        public void showWarning() {
            check = false;

        }
    }
    public static class T5 extends Thread{
        //ArrayList<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int sum = 0;

        @Override
        public void run() {
           try {
            while (true){
                String s = sc.nextLine();
                if (s.equals("N")){
                    break;
                }


                sum += Integer.parseInt(s);
            }
               System.out.println(sum);
            }catch (NumberFormatException e){

            }
                }

    }
}