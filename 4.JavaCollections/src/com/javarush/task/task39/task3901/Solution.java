package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Уникальные подстроки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }


    public static int lengthOfLongestUniqueSubstring(String s) {
        int length = 0;
        int tmpLength = 0;
        List<String> tmpList = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return 0;
        } else {
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {

                if (tmpList.contains(String.valueOf(chars[i]).toLowerCase())) {
                    String firstMeet = String.valueOf(chars[i]).toLowerCase();
                    int count = tmpList.indexOf(firstMeet);
                    tmpList.subList(0, count).clear();

                    if (tmpLength > length) length = tmpLength;
                    tmpLength = tmpList.size();
                    continue;

             } else tmpList.add(String.valueOf(chars[i]).toLowerCase());
                tmpLength++;

            }
            if (tmpLength > length) length = tmpLength;

        }

            return length;
        }


    }

