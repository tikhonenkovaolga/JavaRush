package com.javarush.task.task05.task0532;

import java.io.*;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maximum = Integer.MIN_VALUE;
        int n = Integer.parseInt(reader.readLine());
        if (n < 0){
            return;
        }

        for (int i = 1; i <= n; i++){
            int a = Integer.parseInt(reader.readLine());
            if (a > maximum) {
                maximum = a;
            }

        }


        System.out.println(maximum);
    }
}
