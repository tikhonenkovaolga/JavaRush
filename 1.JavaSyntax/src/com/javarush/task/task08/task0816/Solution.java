package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static HashMap<String, Date> createMap() throws ParseException {
        DateFormat df = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", df.parse("JUNE 1 1980"));
        map.put("Smith", df.parse("JULY 2 1954"));
        map.put("West", df.parse("MAY 20 1970"));
        map.put("East", df.parse("AUGUST 3 1964"));
        map.put("Lone", df.parse("JANUARY 31 1940"));
        map.put("Moore", df.parse("MARCH 24 1981"));
        map.put("Swift", df.parse("SEPTEMBER 1 1989"));
        map.put("Respect", df.parse("APRIL 18 2012"));
        map.put("Amigo", df.parse("JUNE 4 1980"));
        map.put("Jane", df.parse("DECEMBER 21 2016"));

        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map) {
       Iterator<HashMap.Entry<String, Date>> iterator = map.entrySet().iterator();
       while (iterator.hasNext()){
           HashMap.Entry<String, Date> pair = iterator.next();
           String key = pair.getKey();
           Date value = pair.getValue();
           if (value.toString().contains("Jun") | value.toString().contains("Jul") | value.toString().contains("Aug")){
               iterator.remove();
           }
       }
    }

    public static void main(String[] args) throws ParseException {
      // System.out.println(createMap());
//        System.out.println(removeAllSummerPeople(createMap()));

    }
}
