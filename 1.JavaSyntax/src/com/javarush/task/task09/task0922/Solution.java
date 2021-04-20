package com.javarush.task.task09.task0922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* 
Какое сегодня число?
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Date date = d.parse(reader.readLine());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        System.out.println(dateFormat.format(date).toUpperCase());

    }
}
