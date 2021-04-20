package com.javarush.task.task09.task0921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Метод в try..catch
*/

public class Solution {
    public static void main(String[] args) {

        readData();
    }

    public static void readData() {
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 0; i < 10; i++) {
                list.add(Integer.parseInt(reader.readLine()));
            }
        }
         catch(IOException | NumberFormatException e){
            e.printStackTrace();
            for (int j = 0; j < list.size(); j++){
                System.out.println(list.get(j));
            }

               }

           }

        }


