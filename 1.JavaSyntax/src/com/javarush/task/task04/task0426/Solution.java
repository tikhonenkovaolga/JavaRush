package com.javarush.task.task04.task0426;

/* 
Ярлыки и числа
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        if (a < 0 && a%2 == 0){
            System.out.println("отрицательное четное число");
        }
        else if (a < 0 && a%2 != 0){
            System.out.println("отрицательное нечетное число");
        }
        else if (a == 0){
            System.out.println("ноль");
        }
        else if (a > 0 && a%2 == 0){
            System.out.println("положительное четное число");
        }
        else  {
            System.out.println("положительное нечетное число");
        }
    }
}



//Требования:
 //       1. Программа должна считывать число c клавиатуры.
 //       2. Программа должна использовать команду System.out.println() или System.out.print().
 //       3. Если число отрицательное и четное, вывести "отрицательное четное число".
 //       4. Если число отрицательное и нечетное, вывести "отрицательное нечетное число".
 ///       5. Если число равно 0, вывести "ноль".
 //       6. Если число положительное и четное, вывести "положительное четное число".
 //       7. Если число положительное и нечетное, вывести "положительное нечетное число".