package com.javarush.task.task07.task0722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Это конец
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

       ArrayList<String> list = new ArrayList<>();
       for (int i = 0; ; i++){
           list.add(reader.readLine());
           if(list.get(i).equals("end")){
               list.remove("end");
               break;
           }
           }
       for (int j = 0; j < list.size(); j++){
            System.out.println(list.get(j));
       }
    }
}