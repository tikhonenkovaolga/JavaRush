package com.javarush.task.task08.task0827;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) throws ParseException {

        System.out.println(isDateOdd("MAY 1 2013"));
    }

    public static boolean isDateOdd(String date) {
        Date d = new Date(date);
        SimpleDateFormat d1 = new SimpleDateFormat("D");
       // System.out.println(Integer.parseInt(d1.format(d)));
        if (Integer.parseInt(d1.format(d)) %2 == 0) {
            return false;
        }
        else
            return true;
        }
    }

