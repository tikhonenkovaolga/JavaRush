package com.javarush.task.task07.task0709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Выражаемся покороче
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            list.add(reader.readLine());
        }

        int minLength = list.get(0).length();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).length() < minLength){
                minLength = list.get(i).length();
            }
        }

        for (int i = 0; i < list.size(); i++){
            if (list.get(i).length() == minLength){
                System.out.println(list.get(i));
            }
        }
    }
}
