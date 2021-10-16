package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("asdsde"));
    }

    public static boolean isPalindromePermutation(String s) {
        boolean result = true;
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        ArrayList<Character> list = new ArrayList<>();

        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i]== chars[j] & chars[i] != 0) {
                    list.add(chars[i]);

                    chars[i] = 0;
                    chars[j] = 0;
                }
            }

        }
        if (list.size() != chars.length / 2) result = false;
        return result;
    }
}
