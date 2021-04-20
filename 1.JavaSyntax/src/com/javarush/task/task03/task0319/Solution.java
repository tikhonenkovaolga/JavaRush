package com.javarush.task.task03.task0319;

/* 
Предсказание на будущее
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        String scount1 = reader.readLine();
        int count1 = Integer.parseInt(scount1);
        String scount2 = reader.readLine();
        int count2 = Integer.parseInt(scount2);
        System.out.println(name + " получает " + count1 + " через " + count2 + " лет.");

    }
}
