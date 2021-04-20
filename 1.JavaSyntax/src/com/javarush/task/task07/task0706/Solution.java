package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        int[] array = new int[15];

        for (int i = 0; i < 15; i++){
            Scanner sc = new Scanner(System.in);
            array[i] = sc.nextInt();
        }
        int sumNechet = 0;
        for (int j = 1; j < array.length; j+=2){
            sumNechet+= array[j];
        }

        int sumChet = 0;
        for (int k = 0; k < array.length; k+=2){
            sumChet+= array[k];
        }

        if (sumChet > sumNechet){
            System.out.println("В домах с четными номерами проживает больше жителей.");
        }
        else
            System.out.println("В домах с нечетными номерами проживает больше жителей.");


    }
}
