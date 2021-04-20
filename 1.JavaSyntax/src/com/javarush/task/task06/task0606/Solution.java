package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());

        int i;
        while (number != 0) {
            i = number % 10;
            if (i%2 == 0) {
                even++;
            }
            else{
                odd++;
            }
            number /= 10;
        }
        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
