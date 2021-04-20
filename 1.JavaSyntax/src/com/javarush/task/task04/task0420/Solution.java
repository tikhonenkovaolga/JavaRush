package com.javarush.task.task04.task0420;

/* 
Сортировка трех чисел
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        if (a == b && b == c){
            System.out.println("числа равны между собой");
        }

        else if (a >= b && b > c){
            System.out.println(a + " " + b + " " + c);
        }

        else if (a > b & c >= b & a >= c){
            System.out.println(a + " " + c + " " + b);
        }

        else if (a < b & c < b & a >= c){
            System.out.println(b + " " + a + " " + c);
        }

        else if (a < b & c <= b & a <= c){
            System.out.println(b + " " + c + " " + a);
        }

        else if (a <= b & c > b & a < c){
            System.out.println(c + " " + b + " " + a);
        }

        else if (a >= b & c > b & a < c){
            System.out.println(c + " " + a + " " + b);
        }



    }
}
