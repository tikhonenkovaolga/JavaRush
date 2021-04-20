package com.javarush.task.task07.task0713;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Играем в Jолушку
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            Scanner sc = new Scanner(System.in);
            list.add(sc.nextInt());
        }
        ArrayList<Integer> list3 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> listrem = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 3 == 0 && list.get(i) % 2 == 0) {
                list3.add(list.get(i));
                list2.add(list.get(i));
            } else if (list.get(i) % 3 == 0) {
                list3.add(list.get(i));
            }
            else if (list.get(i) % 2 == 0){
                list2.add(list.get(i));
            } else
                listrem.add(list.get(i));
        }

        printList(list3);
        printList(list2);
        printList(listrem);
    }



    public static void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}