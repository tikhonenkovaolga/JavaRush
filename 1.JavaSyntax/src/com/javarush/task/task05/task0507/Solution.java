package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String snumber = reader.readLine();
        int number = Integer.parseInt(snumber);
        int a = 0;

        if (number == -1){
          return;
       }

        double s = 0;
        int i = 0;

//

        while (true) {

            if (number != -1) {
                a = a + number;
                number = Integer.parseInt(reader.readLine());
                i++;
            }

            if (number == -1) {
                s =1.0 * a / i;
                System.out.println((float)s);
                break;
            }


        }
    }
    }


