package com.javarush.task.task04.task0419;

/* 
Максимум четырех чисел
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
           //напишите тут ваш код
    public static int max1 (int a, int b){
            int m1;
            if (a > b) {
                m1 = a;
            }
            else{
                m1 = b;
            }
            return m1;
        }

    public static int max2 (int c, int d){
        int m2;
        if (c > d) {
            m2 = c;
        }
        else{
            m2 = d;
        }
        return m2;
    }

    public static int max (int m1, int m2){
        int m;
        if (m1 > m2) {
            m = m1;
        }
        else{
            m = m2;
        }
        System.out.println(m);
        return m;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        if (a == b & c == d & b == c) {
            System.out.println(a);
            return;
        }

        max1(a, b);
        max2(c, d);
        max(max1(a, b), max2(c, d));

    }
    }

