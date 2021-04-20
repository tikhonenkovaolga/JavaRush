package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            list.add(reader.readLine());
        }

        int maxLength = list.get(0).length();
        for (int i = 0; i < 10; i++) {
            if (list.get(i).length() > maxLength) {
                maxLength = list.get(i).length();
            }
        }

        int minLength = list.get(0).length();
        for (int i = 0; i < 10; i++) {
            if (list.get(i).length() < minLength) {
                minLength = list.get(i).length();
            }
        }

        for (int i = 0; i < 10; i++){
            if (list.get(i).length() == maxLength){
                System.out.println(list.get(i));
                break;
            }
            else if(list.get(i).length() == minLength){
                System.out.println(list.get(i));
                break;
            }
        }
    }
}
