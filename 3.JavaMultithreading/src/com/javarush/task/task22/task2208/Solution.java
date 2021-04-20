package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("nukll", "null");
        map.put("null", "null");
        map.put("city", "null");
        map.put("age", "ccc");
        System.out.println(getQuery(map));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        String result = "";
        if (params.isEmpty()){
            result = "";
        }
        for (Map.Entry<String, String> entry : params.entrySet()){

            if (entry.getKey()!=null && entry.getValue()!=null && !entry.getKey().equals("null") && !entry.getValue().equals("null")){
                result = (sb.append(entry.getKey()).append(" = ").append("'").append(entry.getValue()).append("' and ")).toString();
                result = result.substring(0, result.lastIndexOf(" and "));
            }

        }



        return result;
    }
}




//name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'