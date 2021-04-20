package com.javarush.task.task07.task0727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Меняем функциональность
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList<String>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

//        ArrayList<String> listUpperCase = new ArrayList<String>();
//        for (int i = 0; i < list.size(); i++) {
//            String s = list.get(i);
//            listUpperCase.add(s.toUpperCase());
        //}

        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).length()%2 == 0){
                list1.add(list.get(i) + " " + list.get(i));
            }
            else
                list1.add(list.get(i) + " " + list.get(i) + " " + list.get(i));
        }

        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
    }
}

//1. Программа должна считывать данные с клавиатуры.
//        2. Программа должна вывести столько же строк, сколько было введено.
//        3. Если в строке было четное число букв, то необходимо удвоить строку (как в примере).
//        4. Если в строке было нечетное число букв, то необходимо утроить строку (как в примере).
//        5. Программа должна прекращать считывать данные с клавиатуры как только пользователь введет пустую строку и нажмет Enter.
