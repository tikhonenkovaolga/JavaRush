package com.javarush.task.task10.task1019;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Функциональности маловато!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> map = new HashMap<>();
        String id;
        String name;

            for (int i = 0; ; i++) {

                id = reader.readLine();
                name = reader.readLine();

                if (id.isEmpty()) {
                    break;
                }
                if (name.isEmpty()){
                    map.put(" ", id);
                    break;
                }

                map.put(name, id);



            }
        for (Map.Entry<String, String> pair : map.entrySet()) {
           name = pair.getKey();
           id = pair.getValue();
           System.out.println(id + " " + name);

        }
       // System.out.println(last);

        }

}
