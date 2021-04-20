package com.javarush.task.task04.task0441;


/* 
Как-то средненько
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
            System.out.println(a);
        }

        else if (a == b && b != c){
            System.out.println(a);
        }

        else if (a == c && b != a){
            System.out.println(a);
        }

        else if (b == c && c != a){
            System.out.println(b);
        }

        else if (a > b && a < c){
            System.out.println(a);
        }

        else if (a < b && a > c){
            System.out.println(a);
        }

        else if (a > c && c > b){
            System.out.println(c);
        }

        else if (a < b && b < c){
            System.out.println(b);
        }

        else if (a > b && b > c){
            System.out.println(b);
        }
        else if (a < b && c > a){
            System.out.println(c);
        }



    }
}
