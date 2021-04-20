package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> result = new HashSet<>();
        int count = 0;
        try{
            count = Integer.parseInt(number, 10);
        }
        catch (NumberFormatException e){
            return result;
        }


        String count1 = "";
        for (int i = 2; i <= 36; i++){
            try{
               count1 = Integer.toString(count, i);
            }
            catch (NumberFormatException e){
                result = null;
                continue;
            }

           String s = String.valueOf(count1);
           StringBuilder sbCount = new StringBuilder(s);
           if (s.equals(sbCount.reverse().toString())){
               result.add(i);
           }
        }
        return result;
    }
}