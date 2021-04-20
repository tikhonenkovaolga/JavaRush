package com.javarush.task.task05.task0529;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Консоль-копилка
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int a = 0;

        while (true) {
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if (s.equals("сумма")) {
                System.out.println(a);
                break;
            }
            a+= Integer.parseInt(s);

        }
    }
}
