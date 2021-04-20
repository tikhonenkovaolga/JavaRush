package com.javarush.task.task04.task0442;


/* 
Суммирование
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        Scanner sc = new Scanner(System.in);
        int a = 0;
        int number = sc.nextInt();
        while (true) {

            if (number != -1){
                a = a + number;
                number = sc.nextInt();

            }

            if (number == -1) {
                a = a - 1;
                System.out.println(a);
                break;
            }

        }

    }
}
