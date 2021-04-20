package com.javarush.task.task04.task0428;

/* 
Положительное число
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
        int x = 0;

        if (a > 0){
            x++;
        }

        if (b > 0){
            x++;
        }

        if (c > 0){
            x++;
        }

            System.out.println(x);
        }




    }

