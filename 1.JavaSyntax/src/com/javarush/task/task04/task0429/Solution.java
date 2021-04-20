package com.javarush.task.task04.task0429;

/* 
Положительные и отрицательные числа
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int negativeNumber = 0;
        int positiveNumber = 0;

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++){
            int current = sc.nextInt();
            if (current > 0) {
                positiveNumber++;
            } else if (current < 0){
                negativeNumber++;
            }
        }

//        int a = sc.nextInt();
//        int b = sc.nextInt();
//        int c = sc.nextInt();
//        int x = 0;
//
//        if (a > 0){
//            x++;
//        }
//        else if (a < 0){
//            negativeNumber++;
//        }
//        if (b > 0){
//            x++;
//        }
//        else if (b < 0){
//            negativeNumber++;
//        }
//
//        if (c > 0){
//            x++;
//        }
//        else if (c < 0){
//            negativeNumber++;
//        }

        System.out.println("количество отрицательных чисел: " + negativeNumber);
        System.out.println("количество положительных чисел: " + positiveNumber);

    }
}
