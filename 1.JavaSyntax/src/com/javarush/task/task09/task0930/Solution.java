package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        String[] array = list.toArray(new String[0]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        ArrayList<Integer> ints = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();


        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])) {    //уже возвращает число
                ints.add(Integer.parseInt(array[i]));
            } else {
                strings.add(array[i]);
            }
        }
// сортировка ints
        Collections.sort(ints);
        Collections.reverse(ints);

        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.size()-1; j++) {
                if (isGreaterThan(strings.get(j), strings.get(j + 1))) {
                    String str = strings.get(j);
                    strings.set(j, strings.get(j + 1));
                    strings.set(j + 1, str);
                }
            }
        }

        int counterInts = 0;
        int counterStrings = 0;
        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])) {    //уже возвращает число
                array[i] = ints.get(counterInts++).toString();
            } else {
                array[i] = strings.get(counterStrings++);
            }
        }

    }


    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {

        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String s) {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // Строка содержит '-'
                    || (!Character.isDigit(c) && c != '-') // или не цифра и не начинается с '-'
                    || (chars.length == 1 && c == '-')) // или одиночный '-'
            {
                return false;
            }
        }
        return true;
    }
}
