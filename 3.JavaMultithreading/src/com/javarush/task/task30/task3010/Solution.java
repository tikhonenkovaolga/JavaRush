package com.javarush.task.task30.task3010;

import java.math.BigInteger;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        try {
        String arg0 = args[0];
       if (!arg0.matches("\\w*") || arg0.length() > 255){
                System.out.println("incorrect");
                return;
            }

            else {
                int min = 36;
                for (int i = 36; i >= 2; i--) {
                    try {
                        new BigInteger(arg0, i);

                    } catch (Exception e) {
                        continue;
                    }
                    if (min > i) {
                        min = i;
                    }
                }
                System.out.println(min);
            }

        }
        catch (Exception e) {

        }


    }


}