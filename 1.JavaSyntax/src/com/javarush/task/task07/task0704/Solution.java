package com.javarush.task.task07.task0704;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Переверни массив
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        int[] array = new int[10];

        for (int i = 0; i < 10; i++){
            Scanner sc = new Scanner(System.in);
            array[i] = sc.nextInt();
        }

        for (int j = array.length-1; j >= 0; j--){
            System.out.println(array[j]);
        }
    }
}

